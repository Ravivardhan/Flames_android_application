package com.example.flames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class FlamesActivity extends AppCompatActivity {
    Button flames;
    EditText name1, name2;
    TextView logout;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flames);

        flames = findViewById(R.id.flames);
        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        logout=findViewById(R.id.logout);
        mauth=FirebaseAuth.getInstance();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mauth.signOut();
                Intent i=new Intent(FlamesActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });


        flames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name_1 = name1.getText().toString();
                String name_2 = name2.getText().toString();


                //flames

                String name;
                String fname;
                
                 name = name_1;
                String flm="flames";
                 fname = name_2;
                int l = name.length();
                int gl = fname.length();
                int num = 0, tl = 0;
            
                char[] n = name.toCharArray();
                char[] gn = fname.toCharArray();
                for (int i = 0; i < l; i++) {
                    for (int j = 0; j < gl; j++) {
                        if (n[i] == gn[j]) {
                            n[i] = '*';
                            gn[j] = '*';
                            break;
                        }
                    }
                }
                String tname = new String(n);
                tname = tname + (new String(gn));
                tname = tname.replace("*", "");
                tl = tname.length();


                for (int s = 6; s >= 2; s--) {
                    if (tl > s)
                        num = tl - s;
                    else
                        num = tl;
                    while (num > s) {
                        num = num - s;
                    }
                    flm = flm.substring(num) + (flm.substring(0, num - 1));
                }

                String relation;
                switch (flm.charAt(0)) {
                    case 'f':
                        relation = "Friends";
                        break;
                    case 'l':
                        relation = "Congrats Mawa Bro \n Lovers";
                        break;
                    case 'a':
                        relation = "Affection";
                        break;
                    case 'm':
                        relation = "Party Mawa Bro... \n Marriage";
                        break;
                    case 'e':
                        relation = "Enemies";
                        break;
                    case 's':
                        relation = "Brothers and Sisters";
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + flm.charAt(0));
                }
                Intent i=new Intent(FlamesActivity.this,
                        RelationActivity.class);
                i.putExtra("EXTRA_SESSION_ID", relation);
                //Intent is used to switch from one activity to another.

                startActivity(i);

            }
        });


    }
}