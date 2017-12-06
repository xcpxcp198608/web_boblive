package com.wiatec.boblive.common.result;

public enum  EnumResult {

    SUCCESS(200, "successfully"),

    ERROR_SYSTEM(1001, "system exception"),

    ERROR_PARAM(3001, "request param format error"),

    ERROR_AUTHORIZATION_UNDEFINED(4000, "authorization undefined, sign in again"),

    ERROR_USERNAME_EMPTY(4001, "username is empty"),
    ERROR_USERNAME_EXISTS(4001, "username is exists"),
    ERROR_USERNAME_NOT_EXISTS(4002, "username not exists"),
    ERROR_USERNAME_FORMAT(4002, "username format error"),
    ERROR_PASSWORD_FORMAT(4002, "password format error"),
    ERROR_USERNAME_PASSWORD_NO_MATCH(4100, "username and password not match"),
    ERROR_PASSWORD_EMPTY(4100, "password is empty"),
    ERROR_TOKEN_INCORRECT(4100, "token is incorrect"),
    ERROR_SSN_EXISTS(4003, "ssn is exists"),
    ERROR_EMAIL_EXISTS(4004, "email is exists"),
    ERROR_MAC_EXISTS(4005, "mac is exists"),
    ERROR_DEVICE_ALREADY_REGISTER(4006, "this device has been registered"),
    ERROR_DEVICE_NO_REGISTER(4007, "this device is not registered"),
    ERROR_KEY_INCORRECT(4008, "the key is incorrect"),
    ERROR_KEY_DEACTIVATE(4009, "the key is not activate"),
    ERROR_KEY_ALREADY_USE(4009, "the key is already use"),
    ERROR_OUT_EXPIRATION(40010, "out expiration date"),
    ERROR_MAC_USING(40011, "the device is using"),
    ERROR_RESOURCE_NOT_EXIST(40011, "the resource not exists"),
    ERROR_EXECUTE_FAIL(40011, "execute failure"),


    ERROR_SERVER_SQL(5003, "server sql exception"),
    ERROR_CREATE_FAILURE(5003, "create failure"),
    ERROR_UPDATE_FAILURE(5003, "update failure"),

    ERROR_AUTHORIZE(9001, "authorize communication exception"),

    ERROR_VOUCHER_VALIDATE_FAIL(11001, "validate fail"),
    ERROR_VOUCHER_PAY_FAIL(11002, "pay fail"),
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
