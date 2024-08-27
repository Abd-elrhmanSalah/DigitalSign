package com.dataserve.digitalsign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SignResponse {

    @JsonProperty("Completed")
    private Boolean Completed;
    @JsonProperty("Message")
    private String Message;
    @JsonProperty("SignedFileBytes")
    private String SignedFileBytes;
}
