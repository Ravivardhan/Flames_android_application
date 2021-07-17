package com.example.flames;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth mauth;
    TextView su;
    EditText username,password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        su=findViewById(R.id.signup);
        login=findViewById(R.id.login);
        username=findViewById(R.id.loginusername);
        password=findViewById(R.id.loginpassword);

        mauth=FirebaseAuth.getInstance();
        su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();

                if(user!=null && pass!=null){
                    mauth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent i=new Intent(LoginActivity.this,FlamesActivity.class);
                                startActivity(i);
                        }
                            else{
                                Toast.makeText(LoginActivity.this, "login error", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }
                else{
                    Toast.makeText(LoginActivity.this, "invalid email/password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}