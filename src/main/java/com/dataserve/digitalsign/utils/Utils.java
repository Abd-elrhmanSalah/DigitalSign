package com.dataserve.digitalsign.utils;

import com.dataserve.digitalsign.constant.ResponseCodes;
import com.dataserve.digitalsign.dto.RequestToSign;
import com.dataserve.digitalsign.exception.StatusResponse;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    public static boolean isNotEmpty(Object obj) {
        return obj != null;
    }

    public static StatusResponse internalServerError(String message) {

        StatusResponse error = new StatusResponse(ResponseCodes.INTERNAL_SERVER_ERROR.getCode(),
                ResponseCodes.INTERNAL_SERVER_ERROR.getKey(), message);
        return buildErrorMessage(error);
    }

    public static StatusResponse buildErrorMessage(StatusResponse response) {

        response.setMessage(response.getMessage());
        return response;
    }

}
