package com.threepounds.caseproject.security;
import com.threepounds.caseproject.controller.mapper.UserMapper;
import com.threepounds.caseproject.data.entity.Role;
import com.threepounds.caseproject.data.entity.User;
import com.threepounds.caseproject.data.entity.ValidationCode;
import com.threepounds.caseproject.data.repository.ConfirmRepository;
import com.threepounds.caseproject.data.repository.UserRepository;
import com.threepounds.caseproject.data.repository.ValidationCodeRepository;
import com.threepounds.caseproject.exceptions.EmailCheckException;
import com.threepounds.caseproject.exceptions.NotFoundException;
import com.threepounds.caseproject.security.auth.*;
import com.threepounds.caseproject.service.RoleService;

import java.util.*;

import com.threepounds.caseproject.service.ValidationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final ValidationCodeService validationCodeService;

  private final ConfirmRepository confirmRepository;
  private final UserMapper userMapper;
  private final RoleService roleService;

  @Override
  public JwtAuthenticationResponse signup(SignUpRequest request) {
    User user = userMapper.userDtoToEntity(request);
    user.setUserActive(true);
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    Role userRole = roleService.getByName("ROLE_USER").orElseThrow(() -> new NotFoundException("Role not found"));
    List<Role> roles = new ArrayList<>();
    roles.add(userRole);
    user.setRoles(roles);
    Optional<User> emailEntry = userRepository.findByEmail(user.getEmail());
    if (emailEntry.isPresent()) {
      throw new EmailCheckException("This email is already exist");
    }
    userRepository.save(user);
    var jwt = jwtService.generateToken(user.getUsername());
    Random random = new Random();
    int code = random.nextInt(9000) + 1000;
    ValidationCode validationCode = new ValidationCode();
    String randomCode = Integer.toString(code);
    validationCode.setUser_id(user.getId());
    validationCode.setOtp(randomCode);
    validationCode.setUsed(false);
    validationCodeService.create(validationCode);
    OtpRequest otpRequest = new OtpRequest();
    otpRequest.setUser_id(user.getId());
    return JwtAuthenticationResponse.builder().otp(randomCode).token(jwt).build();
  }

  @Override
  public String confirm(ConfirmRequest request) {

    Optional<ValidationCode> validationCode = validationCodeService.getCode(request.getOtp());

    if (validationCode.get().isUsed() == true) {
      return "Bu kod daha önce kullanılmıştır.";
    } else {
      if (validationCode.get().getOtp().equals(request.getOtp()) && validationCode.get().getUser_id().equals(request.getUser_id())) {
        validationCode.get().setUsed(true);
        validationCodeService.create(validationCode.get());
        return "Kullanıcı sisteme kaydedilmiştir";
      } else if (!validationCode.get().getOtp().equals(request.getOtp()) && validationCode.get().getUser_id().equals(request.getUser_id())){
        return "Bu kod sistemde kayıtlı değildir.";
      }
      else if (validationCode.get().getOtp().equals(request.getOtp()) && !validationCode.get().getUser_id().equals(request.getUser_id())){
        return "Kullanıcı id'si doğru girilmedi";
      }
    }
return  "";
  }




  @Override
  public JwtAuthenticationResponse signin(SigninRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
    String jwt = jwtService.generateToken(user.getEmail());
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
