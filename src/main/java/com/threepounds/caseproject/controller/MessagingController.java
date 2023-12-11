package com.threepounds.caseproject.controller;

import co.elastic.clients.util.DateTime;
import com.threepounds.caseproject.controller.dto.MessagingDto;
import com.threepounds.caseproject.controller.mapper.MessagingMapper;
import com.threepounds.caseproject.controller.mapper.UserMapper;
import com.threepounds.caseproject.controller.resource.FeaturesResource;
import com.threepounds.caseproject.controller.resource.MessagingResource;
import com.threepounds.caseproject.controller.resource.PermissionResource;
import com.threepounds.caseproject.controller.response.ResponseModel;
import com.threepounds.caseproject.data.entity.Features;
import com.threepounds.caseproject.data.entity.Messaging;
import com.threepounds.caseproject.data.entity.User;
import com.threepounds.caseproject.exceptions.NotFoundException;
import com.threepounds.caseproject.service.MessagingService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/message")

public class MessagingController {
    private final MessagingService messagingService;
    private final MessagingMapper messagingMapper;
    private final UserMapper userMapper;

    public MessagingController(MessagingService messagingService, MessagingMapper messagingMapper, UserMapper userMapper) {
        this.messagingService = messagingService;
        this.messagingMapper = messagingMapper;
        this.userMapper = userMapper;
    }


    @PostMapping("")
    public ResponseModel<MessagingResource> createMessage(@RequestBody MessagingDto messagingDto,Principal principal){
        Optional<User> user=messagingService.getUserId(principal);
        Messaging messaging=messagingMapper.messagingDtoToEntity(messagingDto);
        try {
            messagingService.getReceiveMessage(messaging.getReceiverId())
                    .orElseThrow(()->new NotFoundException("User not found"));
        }catch (Exception e){
            e.getMessage();
        }

        messaging.setEnabled(true);
        messaging.setSenderId(user.get().getId());
        messagingService.save(messaging);
        MessagingResource messagingResource=messagingMapper.ResourceToMessagingDto(messaging);

        return new ResponseModel<>(HttpStatus.OK.value(),messagingResource,null);
    }
    @DeleteMapping("/{id}")
    public ResponseModel<MessagingResource> deleteMessage(@PathVariable UUID id) {
        Messaging messaging=messagingService.getById(id)
                .orElseThrow(()->new NotFoundException("Message Not Found"));
        messaging.setEnabled(false);
        messagingService.save(messaging);
        MessagingResource messagingResource=messagingMapper.ResourceToMessagingDto(messaging);
    return new ResponseModel<>(HttpStatus.OK.value(),messagingResource,null);
    }
    @GetMapping("/page")
    public ResponseModel<List<MessagingResource>> listByPage(@RequestParam int pageNumber, @RequestParam int pageSize) {

        Page<Messaging> messagingPage = messagingService.listByPage(pageNumber, pageSize);

        List<MessagingResource> messagingResources= messagingMapper.messagingToResourceList(
                messagingPage.stream().filter((s)->s.getEnabled().equals(true)).collect(Collectors.toList()));


        return new ResponseModel<>(HttpStatus.OK.value(), messagingResources, null,
                (int) messagingPage.getTotalElements(), messagingPage.getTotalPages());
    }

}
