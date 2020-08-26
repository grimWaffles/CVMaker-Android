package com.example.cse464_1610329_midtermproject.view;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cse464_1610329_midtermproject.R;
import com.example.cse464_1610329_midtermproject.database.LocalDatabaseHelper;
import com.example.cse464_1610329_midtermproject.model.User;

public class UserInformation extends AppCompatActivity {

    EditText et_name,et_email,et_phone,et_social1,et_social2,et_summary;
    Button btn_continue;

    LocalDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        db=new LocalDatabaseHelper(this);

        Toolbar toolbar=findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.setTitle("User Information");

        et_name=findViewById(R.id.et_name);et_email=findViewById(R.id.et_email);et_phone=findViewById(R.id.et_phone);
        et_social1=findViewById(R.id.et_social1);et_social2=findViewById(R.id.et_social2);
        et_summary=findViewById(R.id.et_summary);

        btn_continue=findViewById(R.id.btn_continue);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String u_name=et_name.getText().toString();
                String u_email=et_email.getText().toString();
                String phone=et_phone.getText().toString();
                String social1=et_social1.getText().toString();
                String social2=et_social2.getText().toString();
                String summary=et_summary.getText().toString();

                if(!u_name.isEmpty() || !u_email.isEmpty() || !phone.isEmpty() || !summary.isEmpty()){

                    User user=new User(u_name,u_email,phone,social1,social2,summary);

                    db.insertUser(user);

                    Intent resIntent=new Intent();
                    resIntent.putExtra("information","success");
                    setResult(Activity.RESULT_OK,resIntent);
                    finish();

                }
                else{
                    Toast.makeText(UserInformation.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}