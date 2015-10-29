package org.p4p.backpocketdriver.driverlog.pojo.storage;

import android.content.Context;
import android.content.SharedPreferences;

import org.p4p.backpocketdriver.driverlog.models.User;

/**
 * Created by Dheeraj on 1/07/15.
 */
public class UserDetailsStorage {

    public static final String SP_NAME = "userDetails";

    SharedPreferences userLocalDatabase;

    public UserDetailsStorage(Context context) {
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putString("userName", user.getUserName());
        userLocalDatabaseEditor.putString("password", user.getPassword());
        userLocalDatabaseEditor.commit();
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.putBoolean("loggedIn", loggedIn);
        userLocalDatabaseEditor.commit();
    }

    public void clearUserData() {
        SharedPreferences.Editor userLocalDatabaseEditor = userLocalDatabase.edit();
        userLocalDatabaseEditor.clear();
        userLocalDatabaseEditor.commit();
    }

    public User getLoggedInUser() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }

        String username = userLocalDatabase.getString("userName", "");
        String password = userLocalDatabase.getString("password", "");

        User user = new User(username, password);
        return user;
    }
}
