package atln72.hope.util;

import atln72.hope.model.UserAppEntity;

public class UserContext {
    // This is a singleton class that holds the current user
    public static UserAppEntity currentUser;

    public static void setCurrentUser(UserAppEntity user) {
        currentUser = user;
    }
}