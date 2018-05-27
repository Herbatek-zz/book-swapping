package com.piotrek.bookswapping;

import com.piotrek.bookswapping.dto.UserDto;
import com.piotrek.bookswapping.entities.User;

public class UserTestFactory {

    private static final String FIRST_NAME = "Jan";
    private static final String LAST_NAME = "Kowalski";
    private static final String USERNAME = "Janko";
    private static final String EMAIL = "userForSave@email.com";
    private static final String PASSWORD = "randomPassword";

    public static User createUser() {
        return new User(FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, EMAIL);
    }

    public static UserDto createDtoUser() {
        UserDto userDto = new UserDto();
        userDto.setFirstName(FIRST_NAME);
        userDto.setLastName(LAST_NAME);
        userDto.setUsername(USERNAME);
        userDto.setEmail(EMAIL);
        return userDto;
    }

    public static String getFirstName() {
        return FIRST_NAME;
    }

    public static String getLastName() {
        return LAST_NAME;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getEMAIL() {
        return EMAIL;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }
}
