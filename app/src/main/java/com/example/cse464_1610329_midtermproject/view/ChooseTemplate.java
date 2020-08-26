package com.example.cse464_1610329_midtermproject.view;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.cse464_1610329_midtermproject.R;

public class ChooseTemplate extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_template);

        Toolbar toolbar=findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button btnVar1=findViewById(R.id.btn_var1);
        Button btnVar2=findViewById(R.id.btn_var2);

        this.setTitle("Choose Template");

        intent=new Intent(this, AddInformation.class);

        btnVar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("variant","variant1");
                startActivity(intent);
            }
        });

        btnVar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("variant","variant2");
                startActivity(intent);
            }
        });
    }
}