package atln72.hope.util;

import atln72.hope.model.UserAppEntity;

public class UserContext {
    // This is a singleton class that holds the current user
    public static UserAppEntity currentUser;
    public static String token;

    public static void setCurrentUser(UserAppEntity user) {
        currentUser = user;
    }

    public static UserAppEntity getCurrentUser() {
        return currentUser;
    }

    public static void setToken(String token) {
        UserContext.token = token;
    }

    public static String getToken() {
        return token;
    }
}