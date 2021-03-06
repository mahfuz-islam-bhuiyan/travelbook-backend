package org.travelbook.backend.utils;

public class Messages {

    public static class Error {
        public static final String INVALID_INPUT = "Invalid input";
        public static final String USER_CREATE_DUPLICATE_EMAIL = "Provided email is already used by another account. Try a different one.";
        public static final String ERROR_AUTH_AFTER_SIGN_UP = "Error authenticating user after sign up";


    }

    public static class Success {
        public static final String USER_CREATE_SUCCESSFUL = "User created successfully";
        public static final String USER_STATUS_CREATE_SUCCESSFUL = "Status created successfully";
        public static final String USER_STATUS_UPDATE_SUCCESSFUL = "Status updated successfully";

    }

}
