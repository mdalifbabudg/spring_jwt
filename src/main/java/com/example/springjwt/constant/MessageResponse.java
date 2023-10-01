package com.example.springjwt.constant;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResponse {
    private Long id;
    private String message;

    public MessageResponse(String message){
        this.message = message;
    }

    public MessageResponse(Long id, String message){
        this.id = id;
        this.message = message;
    }
}
