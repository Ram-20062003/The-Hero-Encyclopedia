package com.example.navigation.ui.favorites;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation.Hero_info_table;
import com.example.navigation.Home_Screen;
import com.example.navigation.R;
import com.example.navigation.RecyclerView_Fav_Adapter;
import com.example.navigation.TableRoomDatabase;
import com.example.navigation.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Fav_fragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView_Fav_Adapter recyclerView_fav_adapter;
    List<Hero_info_table> hero_info_tableList=new ArrayList<>();
    List<Hero_info_table> hero_info_tableList_copy=new ArrayList<>();
    Hero_info_table hero_info_table;
    private static final String TAG = "Fav_fragment";
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_favorite,container,false);
        recyclerView=view.findViewById(R.id.recycler_view);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                hero_info_tableList= TableRoomDatabase.getInstance(view.getContext()).hero_info_dao().get_hero();
                recyclerView_fav_adapter=new RecyclerView_Fav_Adapter(hero_info_tableList);
                recyclerView.setAdapter(recyclerView_fav_adapter);
                EditText editText=(EditText)view.findViewById(R.id.editTextTextPersonName);
                ImageButton b_search,b_cancel;
                b_search=(ImageButton)view.findViewById(R.id.search);
                b_cancel=(ImageButton)view.findViewById(R.id.cancel);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Home_Screen.list = TableRoomDatabase.getInstance(getContext()).hero_info_dao().get_hero();
                        Log.d(TAG, Home_Screen.list.toString());
                        Log.d(TAG, "size" + String.valueOf(Home_Screen.list.size()));
                    }
                });
                thread.start();
                if(editText.getText().equals("")) {
                    b_search.setVisibility(View.VISIBLE);
                    b_cancel.setVisibility(View.GONE);
                }
                b_search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editText.setFocusableInTouchMode(true);
                        editText.requestFocus();
                        editText.setText(" ");
                        InputMethodManager imm = (InputMethodManager)getContext(). getSystemService(getContext().INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                        b_cancel.setVisibility(View.VISIBLE);
                        b_search.setVisibility(View.GONE);
                    }
                });
                b_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editText.setText("");
                        editText.clearFocus();
                        b_search.setVisibility(View.VISIBLE);
                        b_cancel.setVisibility(View.GONE);
                    }
                });
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(!(editText.getText().equals(""))) {
                            b_search.setVisibility(View.GONE);
                            b_cancel.setVisibility(View.VISIBLE);
                        }
                        if (s.toString().trim().isEmpty()) {
                            editText.clearFocus();
                            recyclerView_fav_adapter=new RecyclerView_Fav_Adapter(hero_info_tableList);
                            recyclerView.setAdapter(recyclerView_fav_adapter);
                        }

                        if (!s.toString().trim().isEmpty()) {

                            filter_search(s.toString().toLowerCase().trim());
                            recyclerView_fav_adapter=new RecyclerView_Fav_Adapter(hero_info_tableList_copy);
                            recyclerView.setAdapter(recyclerView_fav_adapter);

                        }

                    }

                    private void filter_search(String trim) {
                        if(hero_info_tableList_copy!=null)
                            hero_info_tableList_copy.clear();
                        for (int i = 0; i < hero_info_tableList.size(); i++)
                        {
                            if(hero_info_tableList.get(i).getHero_name().toLowerCase().contains(trim))
                            {
                                hero_info_tableList_copy.add(hero_info_tableList.get(i));
                            }
                        }
                    }


                });
            }
        });
        thread.start();
        return view;
    }
}
