package org.p4p.backpocketdriver.driverlog.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.p4p.backpocketdriver.driverlog.R;
import org.p4p.backpocketdriver.driverlog.interfaces.GetUserCallback;
import org.p4p.backpocketdriver.driverlog.models.User;
import org.p4p.backpocketdriver.driverlog.server.UserRequests;


public class RegisterUserActivity extends ActionBarActivity implements View.OnClickListener{

    Button registerButton;
    EditText firstName, lastName, userName, password, confirmPassword;
    private boolean checkInternetConnectivity = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_register_user);

        firstName = (EditText) findViewById(R.id.register_first_name);
        lastName = (EditText) findViewById(R.id.register_last_name);
        password = (EditText) findViewById(R.id.register_password);
        confirmPassword = (EditText) findViewById(R.id.register_password_again);
        userName = (EditText) findViewById(R.id.register_user_name);
        registerButton = (Button) findViewById(R.id.register_user_button);


        registerButton.setOnClickListener(this);
    }

    public boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.register_user_button:
                String fName = firstName.getText().toString();
                String lName = lastName.getText().toString();
                String pass = password.getText().toString();
                String confirmPass = confirmPassword.getText().toString();
                String uName = userName.getText().toString();

                int result = checkFields(fName, lName, uName, pass, confirmPass);
                //missing fields
                if (result == 0){
                    showErrorMessage("Please enter a username");
                }//passwords dont match
                else if (result == - 1){
                    showErrorMessage("Passwords don't match");
                }else if(result == 1){
                    showErrorMessage("Please enter the first name and last name");
                }
                else if(result == 2){
                    showErrorMessage("Please enter a password");
                }

                //correct
                else{
                    User user = new User(fName, lName, uName, pass);
                    registerUser(user);
                }


                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    public int checkFields(String firstName, String lastName, String uName, String password, String confirmPassword) {


        if(firstName.isEmpty() || lastName.isEmpty()){
            return 1;
        }

        else if (uName.isEmpty()){
            return 0;
        }

        else if(password.isEmpty() || confirmPassword.isEmpty()) {
            return 2;
        }
        //Checking to see the password fields
        int status = -1;
        if (confirmPassword != null && password != null)
        {
            if (password.equals(confirmPassword))
            {
                status = 3;
            }
        }
        return status;

    }

    public void registerUser(User user){
        checkInternetConnectivity = haveNetworkConnection();

        if(checkInternetConnectivity == false){
            showErrorMessage("Please make sure you have an active internet connection");
            return;
        }
        boolean yo;
        UserRequests serverRequest = new UserRequests(this, "Registering");
        serverRequest.registerUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(Boolean returnedUser) {
                Toast.makeText(getApplicationContext(), "Successfully Registered",
                        Toast.LENGTH_LONG).show();
                Intent loginIntent = new Intent(RegisterUserActivity.this, MainActivity.class);
                startActivity(loginIntent);

            }
        });

    }

    public void showErrorMessage(String errorMessage) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(RegisterUserActivity.this);
        dialogBuilder.setMessage(errorMessage);
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }






}
