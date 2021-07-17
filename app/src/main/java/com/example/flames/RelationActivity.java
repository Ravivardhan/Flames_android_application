package com.example.flames;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class RelationActivity extends AppCompatActivity {
    TextView relate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relation);
        String sessionId = getIntent().getStringExtra("EXTRA_SESSION_ID");

        relate=findViewById(R.id.relation);
        relate.setText(sessionId);
    }
}