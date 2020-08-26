package com.example.cse464_1610329_midtermproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.cse464_1610329_midtermproject.model.CV;
import com.example.cse464_1610329_midtermproject.model.Experience;
import com.example.cse464_1610329_midtermproject.model.User;

import java.util.ArrayList;
import java.util.List;

public class LocalDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="cv_database";
    private static final int DB_VERSION=1;

    public LocalDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_create_user_table="CREATE TABLE user(id INTEGER primary key autoincrement,name text,phone text,email text,social1 text,social2 text,summary text)";
        String sql_create_user_experience="create table experience(id INTEGER primary key autoincrement,label text,company text,title text,highlight1 text,highlight2 text,highlight3 text,startDate text,endDate text)";
        String sql_create_cv_table="CREATE TABLE cv(id INTEGER primary key autoincrement,name text,path text,variant text)";

        db.execSQL(sql_create_user_table);
        db.execSQL(sql_create_user_experience);
        db.execSQL(sql_create_cv_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //nothing todo
    }

    public void insertUser(User user){

        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put("name",user.getName());
        contentValues.put("email",user.getEmail());
        contentValues.put("phone",user.getPhoneNumber());
        contentValues.put("social1",user.getSocial1());
        contentValues.put("social2",user.getSocial2());
        contentValues.put("summary",user.getSummary());

        db.insert("user",null,contentValues);

        db.close();
    }

    public void insertExperience(ArrayList<Experience> experiences){

        SQLiteDatabase db=this.getWritableDatabase();

        for(Experience e:experiences){

            ContentValues contentValues=new ContentValues();

            contentValues.put("label",e.getLabel());
            contentValues.put("company",e.getCompany_name());
            contentValues.put("title",e.getTitle());
            contentValues.put("highlight1",e.getHighlight1());
            contentValues.put("highlight2",e.getHighlight2());
            contentValues.put("highlight3",e.getHighlight3());
            contentValues.put("startDate",e.getStart_date());
            contentValues.put("endDate",e.getEnd_date());

            db.insert("experience",null,contentValues);
        }

        db.close();
    }

    public User getUserInformation(){

        SQLiteDatabase db=this.getReadableDatabase();

        User user1=new User();

        Cursor cursor=db.query("user",new String[] {"name","email","phone","social1","social2","summary"},null,null,null,null,null);

        if(cursor.moveToFirst()){
            user1=new User(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)
                    ,cursor.getString(4),cursor.getString(5));
        }

        return user1;
    }

    public ArrayList<Experience> getUserExperiences(){

        SQLiteDatabase db=this.getReadableDatabase();

        ArrayList<Experience> user_exp=new ArrayList<>();

        Cursor cursor=db.query("experience",new String[] {"label","company","title","highlight1","highlight2","highlight3","startDate","endDate"},null,null,null,null,"label DESC");

        if(cursor.moveToFirst()){
            do{
                Experience e=new Experience(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                        cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),
                        cursor.getString(7));

                user_exp.add(e);

            }while(cursor.moveToNext());
        }

        return user_exp;
    }

    public void insertCV(CV cv){

        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();

        contentValues.put("name",cv.getName());
        contentValues.put("path",cv.getPath());
        contentValues.put("variant",cv.getVariant());

        db.insert("cv",null,contentValues);
    }


    public ArrayList<CV> getCVList(){

        SQLiteDatabase db=this.getReadableDatabase();

        ArrayList<CV> user_cv=new ArrayList<>();

        Cursor cursor=db.query("cv",new String[] {"name","path","variant"},null,null,null,null,null);

        if(cursor.moveToFirst()){
            do{
                CV e=new CV(cursor.getString(0),cursor.getString(1),cursor.getString(2));

                user_cv.add(e);

            }while(cursor.moveToNext());
        }

        return user_cv;
    }

    public void deleteTables(){

        SQLiteDatabase db=this.getWritableDatabase();

        db.delete("user",null, null);
        db.delete("experience",null, null);
    }
}
