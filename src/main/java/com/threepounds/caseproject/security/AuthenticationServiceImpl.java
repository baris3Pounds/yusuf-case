package com.threepounds.caseproject.security;
import com.threepounds.caseproject.controller.mapper.UserMapper;
import com.threepounds.caseproject.data.entity.Role;
import com.threepounds.caseproject.data.entity.User;
import com.threepounds.caseproject.data.entity.ValidationCode;
import com.threepounds.caseproject.data.repository.UserRepository;
import com.threepounds.caseproject.exceptions.EmailCheckException;
import com.threepounds.caseproject.exceptions.NotFoundException;
import com.threepounds.caseproject.security.auth.*;
import com.threepounds.caseproject.service.RoleService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import com.threepounds.caseproject.service.ValidationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final ValidationCodeService validationCodeService;


  private final UserMapper userMapper;
  private final RoleService roleService;
  @Override
  public JwtAuthenticationResponse signup(SignUpRequest request) {
    User user = userMapper.userDtoToEntity(request);
    user.setActive(true);
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    Role userRole = roleService.getByName("ROLE_USER").orElseThrow(()-> new NotFoundException("Role not found"));
    List<Role> roles = new ArrayList<>();
    roles.add(userRole);
    user.setRoles(roles);
    Optional<User> emailEntry = userRepository.findByEmail(user.getEmail());
    if(emailEntry.isPresent()){
    throw new EmailCheckException("This email is already exist");
      }
    userRepository.save(user);
    var jwt = jwtService.generateToken(user.getUsername());
//    Random random=new Random();
//    int code=random.nextInt(9000)+1000;
//    ValidationCode validationCode=new ValidationCode();
//    validationCode.setCode(code);
//    validationCode.setActive(true);
//    validationCodeService.create(validationCode);

    return JwtAuthenticationResponse.builder().token(jwt).build();
  }

  @Override
  public JwtAuthenticationResponse signin(SigninRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
    String jwt = jwtService.generateToken(user.getEmail());
//    ValidationCode validationCode= validationCodeService.getCode(3955).orElseThrow(()->new NotFoundException("Invalid code"));
//    validationCode.setActive(false);
//    validationCode.setId(validationCode.getId());
//    validationCodeService.create(validationCode);
    return JwtAuthenticationResponse.builder().token(jwt).build();
  }

  @Override
  public JwtAuthenticationResponse passwordreset(PasswordResetRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
    user.setPassword(passwordEncoder.encode(request.getNew_password()));
    String jwt = jwtService.generateToken(user.getEmail());
    return JwtAuthenticationResponse.builder().token(jwt).build();
  }



}
