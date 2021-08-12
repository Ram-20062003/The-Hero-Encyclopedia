package com.example.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Fav_display extends AppCompatActivity {
    public static String name_fav;
    private static final String TAG = "Fav_display";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
         name_fav=intent.getStringExtra("name_fav");
        /* Hero_info_table hero_info_table=new Hero_info_table(name_fav,"hello");
         Load_Table load_table=new Load_Table();
         load_table.execute(hero_info_table);*/
         Thread thread=new Thread(new Runnable() {
             @Override
             public void run() {
                 List<Hero_info_table> list=TableRoomDatabase.getInstance(getApplicationContext()).hero_info_dao().get_hero();
                 Log.d(TAG,list.toString());
             }
         });
         thread.start();
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
               Hero_info_table hero_info_table1=TableRoomDatabase.getInstance(getApplicationContext()).hero_info_dao().findByName("Abe Sapien");
               Log.d(TAG,hero_info_table1.toString());
            }
        });
        thread1.start();
    }

   /* class Load_Table extends AsyncTask<Hero_info_table,Void,Void>
    {

        @Override
        protected Void doInBackground(Hero_info_table... hero_info_tables) {
            TableRoomDatabase.getInstance(getApplicationContext()).hero_info_dao().insert_hero(hero_info_tables[0]);
            return null;
        }
    }*/
}
