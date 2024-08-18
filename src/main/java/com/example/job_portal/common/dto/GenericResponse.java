package com.example.job_portal.common.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GenericResponse<T> {

    private boolean isSuccess = true;

    private MessageDTO message;

    private List<ErrorDTO> errors;

    private T data;

}
