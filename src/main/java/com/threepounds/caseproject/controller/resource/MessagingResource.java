package com.threepounds.caseproject.controller.resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.ZonedDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagingResource {
    private UUID id;
    private String text;
    private Boolean enabled;
    private UUID receiverId;
    private UUID senderId;
    private ZonedDateTime messageTimeStamp;
}
