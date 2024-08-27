package com.dataserve.digitalsign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.io.Serializable;

@Setter
@Getter
public class RequestToSign implements Serializable {

    @JsonProperty("Rid")
    @NotNull(message = "Rid must not be null")
    private String Rid;

    @JsonProperty("Cid")
    @NotNull(message = "Cid must not be null")
    private String Cid;

    @JsonProperty("Email")
    @NotNull(message = "Email must not be null")
    private String Email;

    @JsonProperty("FileBytes")
//    @NotNull(message = "FileBytes must not be null")
    private String  FileBytes;

    @JsonProperty("PrivateKeyPassword")
    @NotNull(message = "PrivateKeyPassword must not be null")
    private String PrivateKeyPassword;

    //Optional
    @JsonProperty("EncryptedKey")
    @Nullable
    private String EncryptedKey;

    //Optional
    @JsonProperty("EncryptedPassword")
    @Nullable
    private String EncryptedPassword;


}
