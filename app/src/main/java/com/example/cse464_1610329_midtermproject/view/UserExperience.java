package com.example.cse464_1610329_midtermproject.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cse464_1610329_midtermproject.R;
import com.example.cse464_1610329_midtermproject.database.LocalDatabaseHelper;
import com.example.cse464_1610329_midtermproject.model.Experience;

import java.util.ArrayList;

public class UserExperience extends AppCompatActivity {

    Spinner mLabel;

    EditText sDate,eDate,companyName,title,highlight1,highlight2,highlight3;
    Button addExp,btn_continue;

    ArrayList<Experience> user_exp; LocalDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_experience);

        db=new LocalDatabaseHelper(this);

        Toolbar toolbar=findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.setTitle("User Experience");

        mLabel =findViewById(R.id.spinner_label);

        sDate=findViewById(R.id.et_start_date); eDate=findViewById(R.id.et_end_date);
        companyName=findViewById(R.id.et_company); title=findViewById(R.id.et_title);
        highlight1=findViewById(R.id.et_highlights1); highlight2=findViewById(R.id.et_highlights2); highlight3=findViewById(R.id.et_highlights3);

        addExp=findViewById(R.id.btn_addExp);
        btn_continue=findViewById(R.id.btn_continue);

        user_exp=new ArrayList<>();

        addExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get label, company name,title and array_list of experiences and populate the object

                String label=mLabel.getSelectedItem().toString();
                String c_name=companyName.getText().toString();
                String jTitle=title.getText().toString();
                String h1=highlight1.getText().toString();
                String h2=highlight2.getText().toString();
                String h3=highlight3.getText().toString();

                String startD=sDate.getText().toString();
                String endD=eDate.getText().toString();

                if(!c_name.isEmpty() || !jTitle.isEmpty() || !startD.isEmpty() || !endD.isEmpty()){

                    //make object and add to list
                    Experience exp=new Experience(label,c_name,jTitle,h1,h2,h3,startD,endD);
                    user_exp.add(exp);

                    companyName.setText("");title.setText("");
                    highlight1.setText(""); highlight2.setText(""); highlight3.setText("");
                    sDate.setText(""); eDate.setText("");
                    Toast.makeText(UserExperience.this, "Experience added under "+label, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(UserExperience.this, "Fields are empty!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pass it to the intent with result code

                if(user_exp.size()==0){
                    Toast.makeText(UserExperience.this, "No Experience added", Toast.LENGTH_LONG).show();
                }

                else{

                    db.insertExperience(user_exp);

                    Log.d("Added to db",Integer.toString(user_exp.size())+ " values");

                    Intent intent=new Intent();
                    intent.putExtra("experience","success");

                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
            }
        });
    }
}