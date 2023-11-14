package com.threepounds.caseproject.security.auth;

import java.util.UUID;

public class OtpRequest {
    private UUID userId;
    private String otp;
    private boolean isUsed;
}
