package com.example.cryptostring;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Objects;

public class EncryptDecrypt extends AppCompatActivity {
    public Toolbar toolbar;
    public boolean value;
    public EditText editText;
    public Button submitBtn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt_decrypt);
        editText = findViewById(R.id.ed_Text);
        submitBtn = findViewById(R.id.submit_Button);
        textView = findViewById(R.id.text);
        Intent intent = getIntent();
        value = intent.getBooleanExtra("isStarted", false);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        showScreen();

    }

    public void showScreen() {
        if (!value) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Crypto String   Encryption");

        } else {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Crypto String   Decryption");

        }
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!value) {
                    encrypt();

                } else {
                    decrypt();
                }
            }
        });

    }

    public void decrypt() {
        String encMsg = editText.getText().toString();
        textView.setText(decryption(encMsg));
    }

    public String decryption(String encMsg) {
        String decrypted = "";
        int j = 0;
        for (int i = 0; i < encMsg.length(); i++) {
            int ascii = (int) encMsg.charAt(i);
            if ((ascii >= 97 && ascii <= 122) || ascii==32) {
                if (i != 0) {
                    decrypted += repeatChar(encMsg, i, j);
                }
                decrypted += encMsg.charAt(i);
                j = i + 1;
            }

            if (i + 1 == encMsg.length()) {
                decrypted += repeatChar(encMsg, i + 1, j);
            }
        }
        return decrypted;
    }

    public String repeatChar(String encMsg, int i, int j) {
        String decrypted = "";
        int loop = Integer.parseInt(encMsg.substring(j, i)) - 1;
        while (loop != 0) {
            decrypted += encMsg.charAt(j - 1);
            loop--;
        }
        return decrypted;
    }

    public void encrypt() {
        String msg = editText.getText().toString();
        textView.setText(encryption(msg));
    }

    public String encryption(String msg) {
        String encrypted = ""+msg.charAt(0);
        int lastCharCount = 1;
        for (int i = 1; i < msg.length(); i++) {
            if(msg.charAt(i) == msg.charAt(i-1))
                lastCharCount++;
            else {
                encrypted += lastCharCount;
                lastCharCount=1;
                encrypted += msg.charAt(i);
            }
        }
        encrypted += lastCharCount;
        return encrypted;
    }


}
