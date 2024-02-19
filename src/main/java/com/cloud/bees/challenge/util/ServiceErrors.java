package com.cloud.bees.challenge.util;


public class ServiceErrors {

    private ServiceErrors() {
        //Do Nothing - Added to Solve implicit public constructor Issue on SonarLint
    }

    public static final String ENTITY_NOT_FOUND = "Entity Not Found";

    public static final String ID = "With Given Id";
    public static final String EMAIL = "With Given Email";
    public static final String USER = "User";
    public static final String TRAIN = "Train";
    public static final String SECTION = "Section";
    public static final String SEAT = "SEAT";
    public static final String USER_ENTITY_NOT_FOUND_BY_ID = USER + ENTITY_NOT_FOUND + ID;
    public static final String USER_ENTITY_NOT_FOUND_BY_EMAIL = USER + ENTITY_NOT_FOUND + EMAIL;
    public static final String TRAIN_ENTITY_NOT_FOUND_BY_ID = TRAIN + ENTITY_NOT_FOUND + ID;
    public static final String SECTION_ENTITY_NOT_FOUND_BY_ID = SECTION + ENTITY_NOT_FOUND + ID;
    public static final String SEAT_ENTITY_NOT_FOUND_BY_ID = SEAT + ENTITY_NOT_FOUND + ID;
    public static final String TRAIN_NOT_AVAILABLE_FOR_GIVEN_LOCATION =
            "There is no Available" + TRAIN + "for given from and to location";
    public static final String SECTION_NOT_AVAILABLE_FOR_GIVEN_TRAIN_NO_OR_ID =
            "There is no Available" + SECTION + "for given train id or train no";
    public static final String SEAT_NOT_AVAILABLE = "There is no available or occupied seats on the train";
    public static final String SEAT_NOT_AVAILABLE_BY_SECTION = "There is no available in given section on the train";
    public static final String PREFERRED_SEAT_NOT_AVAILABLE = "Given Preferred Seat is not Available at the moment";
    public static final String TICKET_RECEIPT_NOT_FOUND = "Ticket Receipts Not Found for a given confirmation code or ticket no";
}
