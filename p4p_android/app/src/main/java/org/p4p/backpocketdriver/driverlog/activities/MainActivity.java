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
import org.p4p.backpocketdriver.driverlog.pojo.storage.UserDetailsStorage;
import org.p4p.backpocketdriver.driverlog.server.UserRequests;




public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button loginButton;
    EditText enterUserName, enterPassword;
    UserDetailsStorage userDetailsStorage;
    private boolean checkInternetConnectivity = false;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        checkInternetConnectivity = haveNetworkConnection();

        if(checkInternetConnectivity == false){
            showErrorMessage("Please make sure you have an active internet connection in order to proceed");
        }


        loginButton = (Button) findViewById(R.id.login_button_name);
        enterUserName = (EditText) findViewById(R.id.login_username);
        enterPassword = (EditText) findViewById(R.id.login_password);

        loginButton.setOnClickListener(this);

        userDetailsStorage = new UserDetailsStorage(this);
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.login_button_name:
                String userName = enterUserName.getText().toString();
                String password = enterPassword.getText().toString();

                if(userName.isEmpty() || password.isEmpty()){
                    showErrorMessage("Please enter the username and password");
                    return;
                }

                user = new User(userName, password);
                authenticateUser(user);
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }


    public void registerUser(View view) {
        Intent intent  = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);

    }

    public void authenticateUser(User user){
        UserRequests serverRequest = new UserRequests(this, "Authenticating");

        checkInternetConnectivity = haveNetworkConnection();

        if(checkInternetConnectivity == false){
            showErrorMessage("Please make sure you have an active internet connection");
            return;
        }

        serverRequest.getUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(Boolean returnedResult) {
                if (returnedResult == false) {
                    showErrorMessage("Incorrect username or password");
                } else {
                    showSuccessfulLogin();
                }
            }
        });
    }

    public void showErrorMessage(String message) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        dialogBuilder.setMessage(message);
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    public void showSuccessfulLogin(){
        userDetailsStorage.storeUserData(user);
        userDetailsStorage.setUserLoggedIn(true);
        Toast.makeText(getApplicationContext(), "Successfully Logged In as " + userDetailsStorage.getLoggedInUser().getUserName(),
                Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, DriverMenuActivity.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
