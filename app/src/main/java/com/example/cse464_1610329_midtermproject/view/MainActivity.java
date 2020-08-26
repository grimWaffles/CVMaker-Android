package com.example.cse464_1610329_midtermproject.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.cse464_1610329_midtermproject.R;
import com.example.cse464_1610329_midtermproject.RecyclerViewAdapter;
import com.example.cse464_1610329_midtermproject.database.LocalDatabaseHelper;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int STORAGE_CODE=0001;

    RecyclerView rv;
    RecyclerViewAdapter adapter;
    LocalDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=new LocalDatabaseHelper(this);
        db.deleteTables();

        Log.d("Tables","Tables Deleted");

        DrawerLayout drawerLayout=findViewById(R.id.drawer_main);
        NavigationView navigationView=findViewById(R.id.nav_main);
        Toolbar toolbar=findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        this.setTitle("EasyCV");

        rv=findViewById(R.id.recylerview_main);
        adapter=new RecyclerViewAdapter(db.getCVList());
        LinearLayoutManager llm=new LinearLayoutManager(this);

        rv.setAdapter(adapter);
        rv.setLayoutManager(llm);

        populateRecyclerView();

        FloatingActionButton fab=findViewById(R.id.fab_main);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        toggle.syncState();

        drawerLayout.addDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ChooseTemplate.class);
                startActivity(intent);
            }
        });

        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
            String[] permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE};
            requestPermissions(permissions,STORAGE_CODE);
        }
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawerLayout=findViewById(R.id.drawer_main);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(menuItem.getItemId()==R.id.my_cv){
            Toast.makeText(this, "Functions not added yet", Toast.LENGTH_SHORT).show();
        }
        else if(menuItem.getItemId()==R.id.app_settings){
            Toast.makeText(this, "Functions not added yet", Toast.LENGTH_SHORT).show();
        }
        return true;
    }


    public  void populateRecyclerView(){
        adapter.notifyDataSetChanged();
    }
}