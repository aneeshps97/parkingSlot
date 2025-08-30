package com.example.parkingslot.constants;

import javax.swing.plaf.PanelUI;

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
    public static final int SUCCESSFULLY_ADDED_USER_TO_PARKING_AREA = 109;
    public static final int UNABLE_TO_ADD_USER_TO_PARKING_AREA = 110;
    public static final int SUCCESSFULLY_ASSIGNED_SLOT_TO_USER = 111;
    public static final int ASSIGNING_SLOT_TO_USER_FAILED = 112;
    public static final int UNABLE_TO_FIND_PARKING_SLOT = 113;
    public static final int USER_FOUND = 114;
    public static final int UNABLE_TO_FIND_BOOKING = 115;
    public static final int FOUND_BOOKING = 116;
    public static final int SUCCESSFULLY_RELEASED_ASSIGNED_SLOTS = 117;
    public static final int UNABLE_TO_RELEASE_SLOT = 118;
    public static final int UNABLE_TO_FIND_FREE_SLOT = 119;
    public static final int FOUND_FREE_SLOT = 120;
    public static final int UNABLE_TO_BOOK_SLOT = 121;
    public static final int BOOKED_FREE_SLOT = 122;
    public static final int UNABLE_TO_UPDATE_PARKING_AREA_NAME = 113;
    public static final int SUCCESSFULLY_CHANGED_NAME_OF_PARKING_AREA = 114;
    public static final int PARKING_SLOT_DELETED_SUCCESSFULLY = 115;
    public static final int PARKING_SLOT_UPDATED_SUCCESSFULLY = 116;
}
