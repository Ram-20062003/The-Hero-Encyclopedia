package com.example.navigation;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation.ui.favorites.Fav_fragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_Fav_Adapter extends RecyclerView.Adapter<RecyclerView_Fav_Adapter.RecyclerView_Fav_ViewHolder>{
    List<Hero_info_table> list=new ArrayList<>();
    public static List<Hero_info_table> tables;
    Hero_info_table hero_info_table;
    int r=0;
    Context context;
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
        holder.imageButton_fav.setVisibility(View.VISIBLE);
        holder.imageButton_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete_fav delete_fav=new Delete_fav();
                delete_fav.execute(list.get(position).getHero_name());
                list.remove(position);
                notifyDataSetChanged();
                notifyItemRangeChanged(position,list.size());
                if(list.size()==0)
                {Fav_fragment.recyclerView.setVisibility(View.INVISIBLE);
                Fav_fragment.textView.setText("TIME TO ADD NEW FAVORITES!!!!!!");
                    Fav_fragment.textView.setVisibility(View.VISIBLE);
                    Fav_fragment.imageView.setVisibility(View.VISIBLE);
                    Toast.makeText(v.getContext(), "IT IS TIME TO ADD NEW FAVORITES",Toast.LENGTH_SHORT).show();
                }
            }
        });
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

    class Delete_fav extends AsyncTask<String,Void,Void>
    {
        @Override
        protected Void doInBackground(String... strings) {
            Hero_info_table hero_info_table=TableRoomDatabase.getInstance(context).hero_info_dao().findByName(strings[0]);
            Log.d(TAG,hero_info_table.toString());
            TableRoomDatabase.getInstance(context).hero_info_dao().delete_hero(hero_info_table);
            return null;
        }
    }

}
