package com.example.deakinaround.UserEntry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.deakinaround.PasswordRecoveryActivity;
import com.example.deakinaround.R;
import com.example.deakinaround.UserRegistrationActivity;

public class MainActivity extends AppCompatActivity {

    Button loginBtn, registerBtn, resetPasswordBtn;
    EditText emailInput;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        title = (TextView) findViewById(R.id.title);
        emailInput = (EditText) findViewById(R.id.emailInput);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        registerBtn = (Button) findViewById(R.id.registerBtn);
        resetPasswordBtn = (Button) findViewById(R.id.resetPasswordBtn);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UserRegistrationActivity.class);
                startActivity(intent);
            }
        });

        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PasswordRecoveryActivity.class);
                startActivity(intent);
                //TODO: fix double up of MainActivity in back stack when user transitions from resetting password in PasswordRecoveryActivity --> MainActivity
            }
        });
    }

    private void handleLogin() {}


}