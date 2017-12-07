package com.wiatec.boblive.common.result;

public enum  EnumResult {

    SUCCESS(200, "Successfully"),
    ERROR_UNAUTH(401, "Unauthorized"),
    ERROR_NO_FOUND(404, "Resource not exists"),
    ERROR_SERVER_EXCEPTION(500, "Server exception"),

    ERROR_WRONG_PARAM_FORMAT(6001, "Request param format incorrect"),
    ERROR_USERNAME_EXISTS(6002, "Username already exists"),
    ERROR_USERNAME_NOT_EXISTS(6003, "Username not exists"),
    ERROR_USERNAME_PASSWORD_NO_MATCH(6004, "Username and password not match"),
    ERROR_SSN_EXISTS(6005, "SSN is exists"),
    ERROR_EMAIL_EXISTS(6006, "Email is exists"),
    ERROR_DEVICE_ALREADY_REGISTER(6007, "This device has been registered"),
    ERROR_DEVICE_NO_REGISTER(6008, "This device is not registered"),
    ERROR_DEVICE_USING(6009, "The device is using"),
    ERROR_KEY_INCORRECT(6010, "The key is incorrect"),
    ERROR_KEY_DEACTIVATE(6011, "The key is not activate"),
    ERROR_KEY_ALREADY_USE(6012, "the key is already use"),
    ERROR_OUT_EXPIRATION(6013, "Out expiration date"),
    ERROR_TOKEN_NOT_EXISTS(6014, "Token not exists"),
    ERROR_TOKEN_INCORRECT(6015, "token is incorrect"),

    ERROR_USERNAME_EMPTY(6016, "username is empty"),
    ERROR_USERNAME_FORMAT(6017, "username format error"),
    ERROR_PASSWORD_FORMAT(6018, "password format error"),
    ERROR_PASSWORD_EMPTY(6019, "password is empty"),
    ERROR_EXECUTE_FAIL(6020, "execute failure"),

    ERROR_CREATE_FAILURE(6021, "create failure"),
    ERROR_UPDATE_FAILURE(6022, "update failure"),

    ;

    private int code;
    private String message;

    EnumResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
