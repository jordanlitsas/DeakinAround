package com.example.deakinaround.UserEntry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.example.deakinaround.InputValidator.InputValidator;
import com.example.deakinaround.R;

public class ChangePasswordActivity extends AppCompatActivity {

    Button resetPasswordBtn;
    EditText password, passwordConfirmation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        password = (EditText) findViewById(R.id.passwordInput);
        passwordConfirmation = (EditText) findViewById(R.id.passwordConfirmationInput);
        resetPasswordBtn = (Button) findViewById(R.id.resetPasswordBtn);
        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlePasswordReset();
            }
        });

    }

    private void handlePasswordReset() {
        InputValidator inputValidator = new InputValidator();
        if (!inputValidator.validatePasswordComplexity(password.getText().toString())){
            String errorMessage = "Your password must contain one digit, lower and uppercase letters, one special character like ! @ # & ( ), and be between 8 and 20 characters.";
            Toast toast = Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG);
            toast.show();
        } else {
            //TODO: API call for changing password
            boolean success = true;
            if (success){
                finish();
            }
        }

    }


}