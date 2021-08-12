package com.example.navigation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Delete;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class hero_stats extends AppCompatActivity {
    TextView t_details;
    ImageView image;
    Bitmap bitmap1=null;
    String output="",url="",path="",check="";
    List<For_display>superhero=new ArrayList<>();
    ProgressBar progressBar;
    private static final String TAG = "hero_stats";
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
       // t_details=(TextView)findViewById(R.id.hero_details);
        image=(ImageView)findViewById(R.id.hero_profile);
        //progressBar=(ProgressBar)findViewById(R.id.progressbar);
        TextView t_slug=(TextView)findViewById(R.id.slug);
        TextView t_intel=(TextView)findViewById(R.id.intelligence);
        TextView t_strength=(TextView)findViewById(R.id.strength) ;
        TextView t_durablity=(TextView)findViewById(R.id.durablity);
        TextView t_power=(TextView) findViewById(R.id.power);
        TextView t_combat=(TextView) findViewById(R.id.combat);
        TextView t_gender=(TextView)findViewById(R.id.gender);
        TextView t_race=(TextView)findViewById(R.id.race);
        TextView t_height=(TextView)findViewById(R.id.height);
        TextView t_ecolor=(TextView)findViewById(R.id.eye_color);
        TextView t_weight=(TextView)findViewById(R.id.weight) ;
        TextView t_hcolor=(TextView)findViewById(R.id.hair_color);
        TextView t_full_name=(TextView)findViewById(R.id.full_name);
        TextView t_alter_ego=(TextView)findViewById(R.id.alter_ego);
        TextView t_aliases=(TextView)findViewById(R.id.aliases);
        TextView t_appear=(TextView)findViewById(R.id.first);
        TextView t_publisher=(TextView)findViewById(R.id.publisher);
        TextView t_align=(TextView)findViewById(R.id.align);
        TextView t_occupation=(TextView)findViewById(R.id.occupation);
        TextView t_base=(TextView)findViewById(R.id.base);
        TextView t_grp=(TextView)findViewById(R.id.grp_affiliations);
        TextView t_relative=(TextView)findViewById(R.id.relatives);
        TextView t_pob=(TextView)findViewById(R.id.pob);
        TextView t_name=(TextView)findViewById(R.id.title_hero);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#f59505"));
        }
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_hero);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.parseColor("#f59505"));
        Intent intent=getIntent();
        String chk=intent.getStringExtra("name");
        t_name.setText(chk);
        Toast.makeText(getApplicationContext(),chk,Toast.LENGTH_SHORT).show();
                for (int i=0;i<Home_Screen.list_all.size();i++) {
                    if (Home_Screen.list_name.get(i).equals(chk)) {
                        url=Home_Screen.list_img_url.get(i);
                       //Picasso.get().load(superHero.getImages().getLg()).resize(276, 249).into(image);
                        check="ram";
                        Picasso.get().load(url).resize(250,250).into(new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                path= MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"IMG_" + System.currentTimeMillis(),null);
                                bitmap1=bitmap;
                                image.setImageBitmap(bitmap);

                            }

                            @Override
                            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                                Log.d(TAG,e.getMessage());
                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                            }
                        });

                            t_slug.setText(Home_Screen.list_all.get(i).getSlug());
                            output += "SLUG :-" + "\t" + Home_Screen.list_all.get(i).getSlug() + "\n \n";
                            output += "POWERSTATS:-" + "\n";
                            t_intel.setText(String.valueOf(Home_Screen.list_all.get(i).getIntelligence()));
                            output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Intelligence- \t" + String.valueOf(Home_Screen.list_all.get(i).getIntelligence()) + "\n";
                            t_strength.setText(String.valueOf(Home_Screen.list_all.get(i).getStrength()));
                            output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Strength- \t" + Home_Screen.list_all.get(i).getStrength() + "\n";
                            t_durablity.setText(String.valueOf(Home_Screen.list_all.get(i).getDurablity()));
                            output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Durablity- \t" + Home_Screen.list_all.get(i).getDurablity() + "\n";
                            t_power.setText(String.valueOf(Home_Screen.list_all.get(i).getPower()));
                            output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Power- \t" + Home_Screen.list_all.get(i).getPower() + "\n";
                            t_combat.setText(String.valueOf(Home_Screen.list_all.get(i).getCombat()));
                            output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Combat- \t" + Home_Screen.list_all.get(i).getCombat() + "\n";
                            output += "\n";
                            output += "APPEARANCE:-" + "\n";
                            t_gender.setText(Home_Screen.list_all.get(i).getGender());
                            output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Gender- \t" + Home_Screen.list_all.get(i).getGender() + "\n";
                            t_race.setText(Home_Screen.list_all.get(i).getRace());
                            output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Race- \t" + Home_Screen.list_all.get(i).getRace() + "\n";
                                t_height.setText(Home_Screen.list_all.get(i).getHeight().get(0)+"inches"+"\n"+Home_Screen.list_all.get(i).getHeight().get(1));
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Height- \t" + Home_Screen.list_all.get(i).getHeight().get(0)+"inches"+"\n"+Home_Screen.list_all.get(i).getHeight().get(1)+"\n";

                                t_weight.setText(Home_Screen.list_all.get(i).getWeight().get(0)+"inches \n"+Home_Screen.list_all.get(i).getWeight().get(1));
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Weight- \t" +Home_Screen.list_all.get(i).getWeight().get(0) + ",\t" + Home_Screen.list_all.get(i).getWeight().get(1) + "\n";

                                t_ecolor.setText(Home_Screen.list_all.get(i).getEye_color());
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Eye-color- \t" + Home_Screen.list_all.get(i).getEye_color() + "\n";

                                    t_hcolor.setText(Home_Screen.list_all.get(i).getHair_color());
                                    output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Hair-Color- \t" +Home_Screen.list_all.get(i).getHair_color() + "\n";
                                output += "\n";
                            output += "BIOGRAPHY :-" + "\n";
                                t_full_name.setText(Home_Screen.list_all.get(i).getFullname());
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Full name- \t" +Home_Screen.list_all.get(i).getFullname() + "\n";
                                t_alter_ego.setText(Home_Screen.list_all.get(i).getAlteregos());
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Alter-Egos- \t" +Home_Screen.list_all.get(i).getAlteregos() + "\n";
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Aliases-";
                                for (int j = 0; j < Home_Screen.list_all.get(i).getAliases().size(); j++) {
                                    t_aliases.setText(Home_Screen.list_all.get(i).getAliases().get(j)+"\n");
                                    output += " \t" + Home_Screen.list_all.get(i).getAliases().get(j) + ",";
                                }
                                t_pob.setText(Home_Screen.list_all.get(i).getPob());
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Place of birth- \t" + Home_Screen.list_all.get(i).getPob() + "\n";
                                    t_appear.setText(Home_Screen.list_all.get(i).getFirst_app());
                                    output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "First Appearance- \t" + Home_Screen.list_all.get(i).getFirst_app() + "\n";

                                    t_publisher.setText(Home_Screen.list_all.get(i).getPublisher());
                                    output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Publisher- \t" +Home_Screen.list_all.get(i).getPublisher() + "\n";

                                    t_align.setText(Home_Screen.list_all.get(i).getAlign());
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Alignment- \t" + Home_Screen.list_all.get(i).getAlign() + "\n";
                                output += "\n";
                            t_occupation.setText(Home_Screen.list_all.get(i).getOccupation());
                            output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Occupation- \t" +Home_Screen.list_all.get(i).getOccupation()+ "\n ";

                                t_base.setText(Home_Screen.list_all.get(i).getBase());
                                output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Base- \t" + Home_Screen.list_all.get(i).getBase() + "\n \n";

                            output += "CONNECTIONS :-" + "\n";
                            t_grp.setText(Home_Screen.list_all.get(i).getGroup_aff());
                            output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Group Affiliations- \t" +Home_Screen.list_all.get(i).getGroup_aff() + "\n ";
                            t_relative.setText(Home_Screen.list_all.get(i).getRel());
                        output += "\t \t \t \t \t \t \t \t \t \t \t \t " + "Relatives- \t" + Home_Screen.list_all.get(i).getRel() + "\n \n";

                }
            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=new MenuInflater(this);
        menuInflater.inflate(R.menu.share,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                output+="\n \n"+"image-url="+url;
                Uri uri=Uri.parse(path);
                Toast.makeText(getApplicationContext(), String.valueOf(uri) ,Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("image/jpeg");
                //intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,output);
                intent.putExtra(Intent.EXTRA_STREAM,uri);
                startActivity(Intent.createChooser(intent,"Share"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
