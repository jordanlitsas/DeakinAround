package com.example.deakinaround;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;;
import android.os.Bundle;

import com.example.deakinaround.UserEntry.ChangePasswordActivity;
import com.example.deakinaround.R;

public class PasswordRecoveryActivity extends AppCompatActivity {

    Button sendCodeBtn;
    TextView checkInboxPrompt;
    EditText codeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        checkInboxPrompt = (TextView) findViewById(R.id.checkInboxForCodePrompt);

        sendCodeBtn = (Button) findViewById(R.id.sendCodeBtn);
        sendCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleCodeEmail();
            }
        });

        codeInput = (EditText) findViewById(R.id.codeInput);
        codeInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() == 6){
                    //TODO: validate the input code is correct
                    boolean correctCode = true;
                    if (correctCode){
                        Intent intent = new Intent(getApplicationContext(), ChangePasswordActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

            }
        });



    }

    private void handleCodeEmail() {
        boolean success = true;
        //TODO: handle the cration of a 6 digit code, email to user's inbox, 5 minute timeout.

        if (success){
            checkInboxPrompt.setText("Check your Deakin email inbox for your 6 digit code.");
        }
    }
}