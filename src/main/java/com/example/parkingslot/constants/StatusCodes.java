package com.example.parkingslot.constants;

public final class StatusCodes {
    private StatusCodes(){};
    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    public static final int GENERAL_EXCEPTION = 99;
    public static final int USER_CREATED_SUCCESSFULLY = 100;
    public static final int SIGNUP_FAILED = 101;
    public static final int EMAIL_ALREADY_EXISTS = 102;
    public static final int USER_NOT_FOUND = 103;
    public static final int WRONG_PASSWORD = 104;
    public static final int ERROR_CREATING_PARKING_AREA = 105;
    public static final int PARKING_AREA_CREATED_SUCCESSFULLY = 106;
    public static final int UNABLE_TO_FIND_PARKING_AREA = 107;
    public static final int SUCCESSFULLY_ADDED_SLOTS_TO_PARKING_AREA = 108;
}
