package com.example.navigation;

import android.app.ProgressDialog;
import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Home_Screen extends AppCompatActivity {
    List_details list_details;
    int a=10;
    public static List<String> list_name = new ArrayList<>();
    public static List<String> list_img_url = new ArrayList<>();
    public static List<Integer> list_id = new ArrayList<>();
    public static List<For_display> list_all=new ArrayList<>();
    public static List<String> male_name = new ArrayList<>();
    public static List<Integer> male_list_id = new ArrayList<>();
    public static List<String> male_list_img_url = new ArrayList<>();
    public static List<String> female_name = new ArrayList<>();
    public static List<Integer> female_list_id = new ArrayList<>();
    public static List<String> female_list_img_url = new ArrayList<>();
    public static List<Hero_info_table> list = new ArrayList<>();
    public static List<String> Fav_button = new ArrayList<>();
    public static List<String> Un_Fav_button = new ArrayList<>();
    private static final String TAG = "Home_Screen";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(list_all!=null)
            list_all.clear();
        if(list_name!=null)
            list_name.clear();
        if(list_img_url!=null)
            list_img_url.clear();
        if(list_id!=null)
            list_id.clear();
        if(male_name!=null)
            male_name.clear();
        if(male_list_id!=null)
            male_list_id.clear();
        if(male_list_img_url!=null)
            male_list_img_url.clear();
        if(female_name!=null)
            female_name.clear();
        if(female_list_id!=null)
            female_list_id.clear();
        if(list!=null)
            list.clear();
        if(Fav_button!=null)
            Fav_button.clear();
        if(Un_Fav_button!=null)
            Un_Fav_button.clear();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#0b1f6e"));
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView3);
        TextView textView=(TextView)findViewById(R.id.textView);
        TextView textView1=(TextView)findViewById(R.id.textView2);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down);
        Animation animation1=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fading);
        imageView.setAnimation(animation1);
        imageView.setAnimation(animation);
        imageView2.setAnimation(animation1);
        imageView2.setAnimation(animation2);
        textView.setAnimation(animation1);
        textView1.setAnimation(animation1);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                list = TableRoomDatabase.getInstance(getApplicationContext()).hero_info_dao().get_hero();
                Log.d(TAG, list.toString());
                Log.d(TAG, "size" + String.valueOf(list.size()));
            }
        });
        thread.start();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
        boolean chk_state;
        @Override
        public void run() {
        do{
            ConnectivityManager connectivityManager=(ConnectivityManager)getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            chk_state=networkInfo!=null&& networkInfo.isConnectedOrConnecting();
            Log.d(TAG, String.valueOf(chk_state));
            if(chk_state==true) {
                Load_task load_task = new Load_task();
                load_task.execute();
            }
        }while(chk_state==false);
    }
},2000);

    }
    class Load_task extends AsyncTask<Void,Integer,Void>
    {
        int a=10;
        @Override
        protected Void doInBackground(Void... voids) {

            Retrofit retrofit=new Retrofit.Builder().baseUrl("https://akabab.github.io/superhero-api/api/").addConverterFactory(GsonConverterFactory.create()).build();
            JsonInterface jsonInterface=retrofit.create(JsonInterface.class);
            Call<List<SuperHero>> call=jsonInterface.getSuperHero();
            call.enqueue(new Callback<List<SuperHero>>() {
                @Override
                public void onResponse(Call<List<SuperHero>> call, Response<List<SuperHero>> response) {
                    if(!response.isSuccessful())
                        Toast.makeText(getApplicationContext(),"Response failed="+response,Toast.LENGTH_LONG).show();
                    List<SuperHero> superHeroList=response.body();
                    for(SuperHero superHero:superHeroList)
                    {
                        For_display for_display=new For_display(superHero.getSlug(),superHero.getAppearance().getGender(),superHero.getAppearance().getRace(),superHero.getAppearance().getEyeColor(),
                                superHero.getAppearance().getHairColor(),superHero.getBiography().getFullName(),superHero.getBiography().getAlterEgos(),superHero.getBiography().getPlaceOfBirth(),superHero.getBiography().getFirstAppearance(),superHero.getBiography().getPublisher()
                                ,superHero.getBiography().getAlignment(),superHero.getWork().getOccupation(),superHero.getWork().getBase(),superHero.getConnections().getGroupAffiliation(),superHero.getConnections().getRelatives(),superHero.getBiography().getAliases(),superHero.getAppearance().getHeight(),superHero.getAppearance().getWeight(),superHero.getPowerstats().getIntelligence(),superHero.getPowerstats().getStrength(),superHero.getPowerstats().getSpeed(),superHero.getPowerstats().getDurability(),superHero.getPowerstats().getPower(),superHero.getPowerstats().getCombat());
                        list_all.add(for_display);
                        list_name.add(superHero.getName());
                        list_id.add(superHero.getId());
                        list_img_url.add(superHero.getImages().getLg());
                        if(superHero.getAppearance().getGender().toLowerCase().equals("male")){
                            male_name.add(superHero.getName());
                            male_list_id.add(superHero.getId());
                            male_list_img_url.add(superHero.getImages().getLg());
                        }
                        if(superHero.getAppearance().getGender().toLowerCase().equals("female")){
                            female_name.add(superHero.getName());
                            female_list_id.add(superHero.getId());
                            female_list_img_url.add(superHero.getImages().getLg());
                            a+=10;
                        publishProgress(a);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<SuperHero>> call, Throwable t) {

                }

            });
                return null;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressBar);
            progressBar.setMax(a);
            setProgress(values[0]);
            progressBar.setProgress(values[0]);
            if(values[0]>=progressBar.getMax())
            {
                Intent intent = new Intent(Home_Screen.this, MainActivity.class);
                startActivity(intent);
            }
            Log.d(TAG, String.valueOf(values[0]) +String.valueOf(a));
            super.onProgressUpdate(values);
        }

    }
}

