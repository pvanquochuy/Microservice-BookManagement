package com.example.job_portal.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Message DTO
 */
@Data
@Builder
@AllArgsConstructor
public class MessageDTO {
    private String messageCode;

    private String messageDetail;

    private String[] replaceWords;
}
