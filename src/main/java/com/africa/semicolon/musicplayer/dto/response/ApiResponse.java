package com.africa.semicolon.musicplayer.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {
    private boolean isSuccessful;
    private Object data;
    private int result;

}
