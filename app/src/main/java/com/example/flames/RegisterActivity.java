package com.example.flames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    FirebaseAuth mauth;
    Button register;
    EditText username,password,confirmpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mauth=FirebaseAuth.getInstance();

        register=findViewById(R.id.register);
        username=findViewById(R.id.registeremail);
        password=findViewById(R.id.registerpassword);
        confirmpassword=findViewById(R.id.registerconfpassword);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname=username.getText().toString();
                String psd=password.getText().toString();
                String conf_psd=confirmpassword.getText().toString();
                if (!(uname==null && psd==null)){

                  mauth.createUserWithEmailAndPassword(uname,psd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful()){
                          Intent i=new Intent(RegisterActivity.this,FlamesActivity.class);

                          startActivity(i);
                      }

                          else{
                              Toast.makeText(RegisterActivity.this, "register error", Toast.LENGTH_SHORT).show();
                          }
                      }
                  });
                }


                else{
                    username.setError("enter valid details");
                    username.requestFocus();
                }

            }
        });
    }
}