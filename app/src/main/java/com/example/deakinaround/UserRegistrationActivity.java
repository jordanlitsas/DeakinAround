package com.example.deakinaround;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.os.Bundle;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.deakinaround.InputValidator.InputValidator;

import org.json.JSONException;
import org.json.JSONObject;


public class UserRegistrationActivity extends AppCompatActivity {

    EditText firstName, lastName, email, password, passwordConfirmation;
    Button registerBtn;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);


        firstName = (EditText) findViewById(R.id.firstNameInput);
        lastName = (EditText) findViewById(R.id.lastNameInput);
        email = (EditText) findViewById(R.id.emailInput);
        password = (EditText) findViewById(R.id.passwordInput);
        passwordConfirmation = (EditText) findViewById(R.id.passwordConfirmationInput);
        title = (TextView) findViewById(R.id.title);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RegisterUserTask(getApplicationContext()).execute();
            }
        });
    }

    private class RegisterUserTask extends AsyncTask<Void, Void, Void>{

        Context callingClassContext;
        JSONObject registerResponse;
        VolleyError ve;


        public RegisterUserTask(Context callingClassContext){
            this.callingClassContext = callingClassContext;
        }
        private void setRegisterResponse(JSONObject registerResponse){
            this.registerResponse = registerResponse;
        }


        @Override
        protected Void doInBackground(Void... voids) {

            //validate the user email does not exist in the database already
            //validate input
            boolean validUserInput = validateUserInput();
            if (validUserInput){
                String url = "https://young-headland-30552.herokuapp.com/api/user";

                JSONObject body = new JSONObject();
                try {
                    body.put("firstName", firstName.getText().toString());
                    body.put("lastName", lastName.getText().toString());
                    body.put("email", email.getText().toString());
                    body.put("password", password.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, body,
                        response -> {
                            setRegisterResponse(response);
                            Log.d(TAG, "onResponse: " + response);
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: " + error.getMessage());
                        ve = error;

                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(callingClassContext);
                requestQueue.add(jsonObjectRequest);
            }

            return null;

        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            //validate input
            boolean validUserInput = validateUserInput();
            if (validUserInput){
                Log.d("---- on post error", ve.getMessage());
                Log.d(TAG, "onPostExecute: " + registerResponse.toString());

                try {
                    if (registerResponse.getInt("status") == 200 && validUserInput){
                        title.setText("success");
                    } else {
                        title.setText(registerResponse.toString());

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "onPostExecute: " + ve.getMessage());
                }
            }



        }
    }



    private boolean validateUserInput(){
        //validate user input
        InputValidator inputValidator = new InputValidator();
        String errorMessage = "";

        if (!inputValidator.validateLength(2, firstName.getText().toString())){
            errorMessage += "First name must have two or more characters.\n";
        }

        if (!inputValidator.validateLength(1, lastName.getText().toString())){
            errorMessage += "Last name cannot be empty.\n";
        }

        if (!inputValidator.validateDeakinEmail(email.getText().toString())){
            errorMessage += "Please use your Deakin email.\n";
        } else if (!inputValidator.validateLength(15, email.getText().toString())){
            errorMessage += "Please double check your email.\n";
        }

        if (!inputValidator.validatePasswordComplexity(password.getText().toString())){
            errorMessage += "Passwords must contain one digit, one upper case letter, one lower case letter and one special symbol (“@#$%”).\n";
        }

        if (!inputValidator.validateStringMatch(password.getText().toString(), passwordConfirmation.getText().toString())){
            errorMessage += "Your passwords do not match.\n";
        }

        if (!errorMessage.equals("")){
            //remove trailing line break
            errorMessage = errorMessage.substring(0, errorMessage.length()-2);
            inputErrorToast(errorMessage);
            return false;
        } else {
            return true;
        }
    }

    private void inputErrorToast(String errorMessage){
        Toast toast = Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG);
        toast.show();
    }
}