package com.threepounds.caseproject.security;

import com.threepounds.caseproject.security.auth.JwtAuthenticationResponse;
import com.threepounds.caseproject.security.auth.SignUpRequest;
import com.threepounds.caseproject.security.auth.SigninRequest;
import com.threepounds.caseproject.security.auth.PasswordResetRequest;


public interface AuthenticationService {
  JwtAuthenticationResponse signup(SignUpRequest request);

  JwtAuthenticationResponse signin(SigninRequest request);
  
  JwtAuthenticationResponse passwordreset(PasswordResetRequest request);
}
