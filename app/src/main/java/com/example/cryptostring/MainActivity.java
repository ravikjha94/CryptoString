package com.example.cryptostring;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
      Button encryptionBtn,decryption_Btn;
      public static boolean startActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        encryptionBtn=findViewById(R.id.encrypt_Btn);
        decryption_Btn=findViewById(R.id.decrypt_Btn);
        encryptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(false);
            }
        });
        decryption_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              openActivity(true);
            }
        });
    }

    private void openActivity(boolean isEncrypt) {
        Intent intent=new Intent(MainActivity.this,EncryptDecrypt.class);
        intent.putExtra("isStarted",isEncrypt);
        startActivity(intent);
    }
}
