package com.wiatec.boblive.common.result;

/**
 * @author patrick
 */

public enum EnumResultCZ {

    SUCCESS(200, "Úspěšně"),
    ERROR_UNAUTH(401, "Neoprávněný"),
    ERROR_NO_FOUND(404, "Zdroj neexistuje"),
    ERROR_SERVER_EXCEPTION(500, "Vnitřní chyba serveru"),

    ERROR_WRONG_PARAM_FORMAT(6001, "Formát požadavku je nesprávný"),
    ERROR_USERNAME_EXISTS(6002, "Uživatelské jméno již existuje"),
    ERROR_USERNAME_NOT_EXISTS(6003, "Uživatelské jméno neexistuje"),
    ERROR_USERNAME_PASSWORD_NO_MATCH(6004, "Uživatelské jméno a heslo neodpovídají"),
    ERROR_SSN_EXISTS(6005, "SSN is exists"),
    ERROR_EMAIL_EXISTS(6006, "Email is exists"),
    ERROR_DEVICE_ALREADY_REGISTER(6007, "This device has been registered"),
    ERROR_DEVICE_NO_REGISTER(6008, "This device is not registered"),
    ERROR_DEVICE_USING(6009, "The device is using"),
    ERROR_KEY_INCORRECT(6010, "Kód je nesprávný"),
    ERROR_KEY_DEACTIVATE(6011, "Kód není aktivován"),
    ERROR_KEY_ALREADY_USE(6012, "Kód je již použit"),
    ERROR_OUT_EXPIRATION(6013, "Datum vypršení platnosti již uplynula"),
    ERROR_TOKEN_NOT_EXISTS(6014, "Token neexistuje"),
    ERROR_TOKEN_INCORRECT(6015, "Token je nesprávný"),

    ERROR_USERNAME_EMPTY(6016, "Uživatelské jméno je prázdné"),
    ERROR_USERNAME_FORMAT(6017, "Chyba formátu uživatelského jména"),
    ERROR_PASSWORD_FORMAT(6018, "Chyba formátu hesla"),
    ERROR_PASSWORD_EMPTY(6019, "Heslo je prázdné"),
    ERROR_EXECUTE_FAIL(6020, "selhání provádění"),

    ERROR_CREATE_FAILURE(6021, "create16 failure"),
    ERROR_UPDATE_FAILURE(6022, "update failure"),
    ERROR_VALIDATE_FAILURE(6023, "selhání ověření"),

    ;

    private int code;
    private String message;

    EnumResultCZ(int code, String message) {
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
