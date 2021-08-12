package com.example.navigation;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    /*public static List<String> list_slug=new ArrayList<>();
    public static List<Integer> list_intelligence=new ArrayList<>();
    public static List<Integer> list_strength=new ArrayList<>();
    public static List<Integer> list_speed=new ArrayList<>();
    public static List<Integer> list_durablity=new ArrayList<>();
    public static List<Integer> list_power=new ArrayList<>();
    public static List<Integer> list_combat=new ArrayList<>();
    public static List<String> list_gender=new ArrayList<>();
    public static List<String> list_race=new ArrayList<>();
    public static List<String> list_height=new ArrayList<>();*/
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
                    }
                }
            }

            @Override
            public void onFailure(Call<List<SuperHero>> call, Throwable t) {

            }

        });
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                list = TableRoomDatabase.getInstance(getApplicationContext()).hero_info_dao().get_hero();
                Log.d(TAG, list.toString());
                Log.d(TAG, "size" + String.valueOf(list.size()));
            }
        });
        thread.start();
        ImageView imageView = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView3);
        TextView textView=(TextView)findViewById(R.id.textView);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down);
        Animation animation1=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fading);
        imageView.setAnimation(animation1);
        imageView2.setAnimation(animation1);
        imageView.setAnimation(animation);
        imageView2.setAnimation(animation2);
        textView.setAnimation(animation1);
        ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setMax(100);
        Timer timer=new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

               progressBar.setProgress(a);
               a+=10;
               if(progressBar.getProgress()>=100) {
                   Intent intent = new Intent(Home_Screen.this, MainActivity.class);
                startActivity(intent);
                timer.cancel();
               }
            }
        },0,1000);

    }
}

