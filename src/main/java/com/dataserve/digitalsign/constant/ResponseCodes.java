package com.dataserve.digitalsign.constant;


public enum ResponseCodes {

    INTERNAL_SERVER_ERROR("001", "Internal Server Error", "Internal Server Error");


    private final String code;
    private final String key;
    private final String message;


    private ResponseCodes(String code, String key, String message) {
        this.code = code;
        this.key = key;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }
}
