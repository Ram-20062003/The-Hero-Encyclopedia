package com.example.navigation;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_Fav_Adapter extends RecyclerView.Adapter<RecyclerView_Fav_Adapter.RecyclerView_Fav_ViewHolder>{
    List<Hero_info_table> list=new ArrayList<>();
    public static List<Hero_info_table> tables;
    Hero_info_table hero_info_table;
    int r=0;
   public static List<String> n_remove=new ArrayList<>();
    private static final String TAG = "RecyclerView_Fav_Adapter";
    public RecyclerView_Fav_Adapter(List<Hero_info_table> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView_Fav_ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_hero_card,parent,false);
        RecyclerView_Fav_ViewHolder recyclerView_fav_viewHolder=new RecyclerView_Fav_ViewHolder(view);
        return recyclerView_fav_viewHolder;
    }

    @Override
    public void onBindViewHolder( RecyclerView_Fav_ViewHolder holder, int position) {

        holder.text_id.setText(String.valueOf(list.get(position).getId()));
        holder.text_name.setText(list.get(position).getHero_name());
        Picasso.get().load(list.get(position).getImage_url()).resize(300,232).into(holder.image_fav);
        holder.imageButton.setVisibility(View.INVISIBLE);
        holder.imageButton_fav.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RecyclerView_Fav_ViewHolder extends RecyclerView.ViewHolder{
        TextView text_id,text_name;
        ImageView image_fav;
        ImageButton imageButton,imageButton_fav;
        public RecyclerView_Fav_ViewHolder( View itemView) {
            super(itemView);
            text_id=itemView.findViewById(R.id.hero_id);
            text_name=itemView.findViewById(R.id.hero_name);
            image_fav=itemView.findViewById(R.id.imageView);
            imageButton=itemView.findViewById(R.id.imageButton2);
            imageButton_fav=itemView.findViewById(R.id.imageButton2_fav);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(v.getContext(),hero_stats.class);
                    intent.putExtra("name",text_name.getText());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }




}
