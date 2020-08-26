package com.example.cse464_1610329_midtermproject.view;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cse464_1610329_midtermproject.PDFMaker;
import com.example.cse464_1610329_midtermproject.R;
import com.example.cse464_1610329_midtermproject.database.LocalDatabaseHelper;
import com.example.cse464_1610329_midtermproject.model.CV;

public class AddInformation extends AppCompatActivity {

    Button btn_user_info,btn_user_exp,btn_create;

    EditText et_save_loc; String variant;

    Intent intent;

    Boolean gotInformation=false;Boolean gotExperience=false;

    LocalDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_information);

        Toolbar toolbar=findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.setTitle("Add Information");

        db=new LocalDatabaseHelper(this);

        variant=getIntent().getStringExtra("variant");

        btn_user_info=findViewById(R.id.btn_user_info); btn_user_exp=findViewById(R.id.btn_user_exp); btn_create=findViewById(R.id.btn_create);
        et_save_loc=findViewById(R.id.et_save_loc);

        et_save_loc.setText(Environment.getExternalStorageDirectory()+"/"+"mFileName"+".pdf");

        btn_user_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(AddInformation.this,UserInformation.class);
                startActivityForResult(intent,301);
            }
        });

        btn_user_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(AddInformation.this,UserExperience.class);
                startActivityForResult(intent,302);
            }
        });

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gotInformation && gotExperience){
                    if(variant.equals("variant1")){

                        PDFMaker maker=new PDFMaker(db.getUserInformation(),db.getUserExperiences(),variant,et_save_loc.getText().toString());
                        maker.makeVariant1();
                        Toast.makeText(AddInformation.this, "PDF complete", Toast.LENGTH_SHORT).show();

                        String cvName=getCVname(et_save_loc.getText().toString());
                        String path=et_save_loc.getText().toString();

                        CV cv=new CV(cvName,path,variant);

                        db.insertCV(cv);
                    }
                    else if(variant.equals("variant2")){

                        PDFMaker maker=new PDFMaker(db.getUserInformation(),db.getUserExperiences(),variant,et_save_loc.getText().toString());
                        maker.makeVariant2();
                        Toast.makeText(AddInformation.this, "PDF complete", Toast.LENGTH_SHORT).show();

                        String cvName=getCVname(et_save_loc.getText().toString());
                        String path=et_save_loc.getText().toString();

                        CV cv=new CV(cvName,path,variant);

                        db.insertCV(cv);
                    }
                }
                else{
                    Toast.makeText(AddInformation.this, "Information not entered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==301){
            if(resultCode== Activity.RESULT_OK){
                gotInformation=true;
            }
            else {
                Toast.makeText(this, "Information not added", Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode==302){
            if(resultCode== Activity.RESULT_OK){
                gotExperience=true;
            }
            else{
                Toast.makeText(this, "Experience not added", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getCVname(String path){

        String name="";

        for(int i=path.length();i==0;i--){
            Character c=path.charAt(i);

            if (c.equals("/")){
                name=path.substring(i,path.length()-1);
            }
        }
        return name;
    }
}