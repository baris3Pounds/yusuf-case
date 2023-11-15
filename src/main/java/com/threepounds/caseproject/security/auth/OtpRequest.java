package com.threepounds.caseproject.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OtpRequest {
    private UUID id;
    private UUID user_id;
    private String otp;
    private boolean isUsed;
}
