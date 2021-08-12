package com.example.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.RecyclerView_ViewHolder> {
   List<String> name;
   List<String>image_url;
   List<Integer>id;
   List<Hero_info_table> list=new ArrayList<>();
   public static int pos;
    private static final String TAG = "RecyclerView_Adapter";
Context context;
    public RecyclerView_Adapter(List<String> name, List<Integer> id, List<String> image_url) {
        this.name = name;
        this.id = id;
        this.image_url = image_url;
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView_ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_hero_card,parent,false);
        RecyclerView_ViewHolder recyclerView_viewHolder=new RecyclerView_ViewHolder(view);
        return recyclerView_viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView_ViewHolder holder, int position) {
        holder.t_name.setText(name.get(position));
        holder.t_id.setText(String.valueOf(id.get(position)));
        context=holder.itemView.getContext();
        Picasso.get().load(image_url.get(position)).resize(300,232).into(holder.image_hero);
        int c=0,d=0,dw=0;
        for (int i=0;i<Home_Screen.list.size();i++)
        {
            if(name.get(position).equals(Home_Screen.list.get(i).getHero_name()))
               c=1;
        }
            if(c==1) {
                holder.imageButton.setVisibility(View.GONE);
                holder.imageButton_fav.setVisibility(View.VISIBLE);
            }
            if(c==0) {
                holder.imageButton.setVisibility(View.VISIBLE);
                holder.imageButton_fav.setVisibility(View.GONE);
            }
            for (int j=0;j<Home_Screen.Fav_button.size();j++)
            {
                if(name.get(position).equals(Home_Screen.Fav_button.get(j)))
                    d=1;
            }
            if(d==1) {
                holder.imageButton.setVisibility(View.GONE);
                holder.imageButton_fav.setVisibility(View.VISIBLE);
            }
            if(d==0){
        for (int j=0;j<Home_Screen.Un_Fav_button.size();j++)
        {
            if(name.get(position).equals(Home_Screen.Un_Fav_button.get(j)))
                dw=1;
        }
        if(dw==1) {
            holder.imageButton_fav.setVisibility(View.GONE);
            holder.imageButton.setVisibility(View.VISIBLE);
        }}
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imageButton.setVisibility(View.GONE);
                holder.imageButton_fav.setVisibility(View.VISIBLE);
               Home_Screen. Fav_button.add(name.get(position));
                Toast.makeText(v.getContext(),"You liked \t"+holder.t_name.getText(),Toast.LENGTH_SHORT).show();
                Hero_info_table hero_info_table=new Hero_info_table(id.get(position),name.get(position),image_url.get(position));
                Load_Table load_table=new Load_Table();
                load_table.execute(hero_info_table);
            }
        });
            holder.imageButton_fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.imageButton_fav.setVisibility(View.GONE);
                    holder.imageButton.setVisibility(View.VISIBLE);
                    Home_Screen.Un_Fav_button.add(name.get(position));
                    Thread thread=new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Hero_info_table hero_info_table=TableRoomDatabase.getInstance(v.getContext()).hero_info_dao().findByName(name.get(position));
                            Log.d(TAG,hero_info_table.toString());
                            TableRoomDatabase.getInstance(v.getContext()).hero_info_dao().delete_hero(hero_info_table);
                        }
                    });
                    thread.start();
                }
            });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class RecyclerView_ViewHolder extends RecyclerView.ViewHolder {
        TextView t_id,t_name;
        ImageView image_hero;
        ImageButton imageButton,imageButton_fav;
        public RecyclerView_ViewHolder(View itemView) {
            super(itemView);
            t_id=itemView.findViewById(R.id.hero_id);
            t_name=itemView.findViewById(R.id.hero_name);
            image_hero=itemView.findViewById(R.id.imageView);
            imageButton=itemView.findViewById(R.id.imageButton2);
            imageButton_fav=itemView.findViewById(R.id.imageButton2_fav);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pos=getAdapterPosition();
                    //Toast.makeText(v.getContext(), "Hi I am "+list_name.get(getAdapterPosition()),Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(v.getContext(),hero_stats.class);
                    intent.putExtra("name",t_name.getText());
                    ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) itemView.getContext(),image_hero, "get_details_image");
                    itemView.getContext().startActivity(intent,optionsCompat.toBundle());
                }
            });
        }
    }
    class Load_Table extends AsyncTask<Hero_info_table,Void,Void>
    {

        @Override
        protected Void doInBackground(Hero_info_table... hero_info_tables) {
            TableRoomDatabase.getInstance(context).hero_info_dao().insert_hero(hero_info_tables[0]);
            return null;
        }
    }

}
