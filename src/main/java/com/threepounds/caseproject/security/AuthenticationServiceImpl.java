package com.threepounds.caseproject.security;

import com.threepounds.caseproject.controller.mapper.RoleMapper;
import com.threepounds.caseproject.controller.mapper.UserMapper;
import com.threepounds.caseproject.data.entity.Permission;
import com.threepounds.caseproject.data.entity.Role;
import com.threepounds.caseproject.data.entity.User;
import com.threepounds.caseproject.data.repository.UserRepository;
import com.threepounds.caseproject.security.auth.JwtAuthenticationResponse;
import com.threepounds.caseproject.security.auth.SignUpRequest;
import com.threepounds.caseproject.security.auth.SigninRequest;
import com.threepounds.caseproject.service.PermissionService;
import com.threepounds.caseproject.service.RoleService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

  private final UserMapper userMapper;
  private final RoleService roleService;
  @Override
  public JwtAuthenticationResponse signup(SignUpRequest request) {
    User user = userMapper.userDtoToEntity(request);
    user.setUserActive(true);
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    Role userRole = roleService.getByName("ROLE_USER").orElseThrow(()-> new RuntimeException());
    List<Role> roles = new ArrayList<>();
    roles.add(userRole);
    user.setRoles(roles);


    userRepository.save(user);
    var jwt = jwtService.generateToken(user.getUsername());
    return JwtAuthenticationResponse.builder().token(jwt).build();
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
}
