package org.p4p.backpocketdriver.driverlog.server;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;
import org.p4p.backpocketdriver.driverlog.interfaces.GetUserCallback;
import org.p4p.backpocketdriver.driverlog.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Dheeraj on 4/07/15.
 */
public class UserRequests extends Server {
    private ProgressDialog progressDialog;

    public UserRequests(Context context, String message) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle(message + "...");
        progressDialog.setMessage("Please wait...");
    }

    public void registerUserDataInBackground(User user, GetUserCallback userCallback) {
        progressDialog.show();
        new RegisterUserDataAsyncTask(user, userCallback).execute();

    }

    public void getUserDataInBackground(User user, GetUserCallback userCallback) {
        progressDialog.show();
        new AuthenticateUserDataAsyncTask(user, userCallback).execute();

    }

    public String addUser(User user){
        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("firstName", user.getFirstName());
            jsonObject.put("lastName", user.getLastName());
            jsonObject.put("password", user.getPassword());
            jsonObject.put("userName", user.getUserName());

        }catch(JSONException e){

        }

        return HTTPPostMethod(SERVER_ADDRESS + USER_URL, jsonObject);

    }

    public User getUser(String userName){
        String response = HTTPGetMethod(SERVER_ADDRESS + USER_URL + userName);

        if(response == null){
            return null;
        }

        try{
            JSONObject jsonResponse = new JSONObject(response);
            String uName = jsonResponse.getString("userName");
            String pass = jsonResponse.getString("password");
            User user = new User(uName, pass);
            return user;
        }catch (JSONException e){

        }
        return null;
    }

    public List<User> getAllUsers(){

        String allUsers =  HTTPGetMethod(SERVER_ADDRESS + USER_URL);
        String users = allUsers.substring(1, allUsers.length()-1);
        List<String> userList = Arrays.asList(users.split("\\s*,\\s*"));
        List<User> returnedUsers = new ArrayList<User>();
        for (String s: userList){
            try{
                JSONObject newUser = new JSONObject(s);
                String firstName = newUser.getString("firstName");
                String lastName = newUser.getString("lastName");
                String uName = newUser.getString("userName");
                String pass = newUser.getString("password");
                User user = new User(firstName, lastName, uName, pass);
                returnedUsers.add(user);
            }catch (JSONException e){

            }

        }
        return returnedUsers;
    }

    public String deleteUser(String userName){

        return HTTPDeleteMethod(userName);
    }


    public class RegisterUserDataAsyncTask extends AsyncTask<Void, Void, Void> {
        User user;
        GetUserCallback userCallback;

        public RegisterUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            addUser(user);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            userCallback.done(null);

        }

    }

    public class AuthenticateUserDataAsyncTask extends AsyncTask<Void, Void, Boolean>{
        User user;
        GetUserCallback userCallback;

        public AuthenticateUserDataAsyncTask(User user, GetUserCallback userCallback) {
            this.user = user;
            this.userCallback = userCallback;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            User theUser = getUser(user.getUserName());

            if(theUser == null){
                return false;
            }

            if (this.user.getPassword().equals(theUser.getPassword())){
                //correct password
                return true;
            }
            else{
                //incorrect password
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            userCallback.done(result);

        }

    }
}
