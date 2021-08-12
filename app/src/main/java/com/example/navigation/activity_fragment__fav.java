//package com.example.navigation;
//
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.EditText;
//import android.widget.ImageButton;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//*
// * A simple {@link Fragment} subclass.
// * Use the {@link activity_fragment__fav#newInstance} factory method to
// * create an instance of this fragment.
//
//
//public class activity_fragment__fav extends Fragment {
//    RecyclerView recyclerView;
//   RecyclerView_Fav_Adapter recyclerView_fav_adapter;
//    List<Hero_info_table> hero_info_tableList=new ArrayList<>();
//    List<Hero_info_table> hero_info_tableList_copy=new ArrayList<>();
//    Hero_info_table hero_info_table;
//    private static final String TAG = "activity_fragment__fav";
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public activity_fragment__fav() {
//        super(R.layout.fragment_activity_fav);
//        // Required empty public constructor
//    }
//
//*
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment fragment_activity_fav.
//
//
//    // TODO: Rename and change types and number of parameters
//    public static activity_fragment__fav newInstance(String param1, String param2) {
//        activity_fragment__fav fragment = new activity_fragment__fav();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view= inflater.inflate(R.layout.fragment_activity_fav, container, false);
//        recyclerView=view.findViewById(R.id.recycler_view);
//        Thread thread=new Thread(new Runnable() {
//            @Override
//            public void run() {
//               hero_info_tableList= TableRoomDatabase.getInstance(view.getContext()).hero_info_dao().get_hero();
//                    recyclerView_fav_adapter=new RecyclerView_Fav_Adapter(hero_info_tableList);
//                    recyclerView.setAdapter(recyclerView_fav_adapter);
//                EditText editText=(EditText)view.findViewById(R.id.editTextTextPersonName);
//                ImageButton b_search,b_cancel;
//                b_search=(ImageButton)view.findViewById(R.id.search);
//                b_cancel=(ImageButton)view.findViewById(R.id.cancel);
//                if(editText.getText().equals("")) {
//                    b_search.setVisibility(View.VISIBLE);
//                    b_cancel.setVisibility(View.GONE);
//                }
//                b_search.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        editText.setFocusableInTouchMode(true);
//                        editText.requestFocus();
//                        editText.setText(" ");
//                        InputMethodManager imm = (InputMethodManager)getContext(). getSystemService(getContext().INPUT_METHOD_SERVICE);
//                        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
//                        b_cancel.setVisibility(View.VISIBLE);
//                        b_search.setVisibility(View.GONE);
//                    }
//                });
//                b_cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        editText.setText("");
//                        editText.clearFocus();
//                        b_search.setVisibility(View.VISIBLE);
//                        b_cancel.setVisibility(View.GONE);
//                    }
//                });
//                editText.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {
//                        if(!(editText.getText().equals(""))) {
//                            b_search.setVisibility(View.GONE);
//                            b_cancel.setVisibility(View.VISIBLE);
//                        }
//                        if (s.toString().trim().isEmpty()) {
//                            editText.clearFocus();
//                            recyclerView_fav_adapter=new RecyclerView_Fav_Adapter(hero_info_tableList);
//                            recyclerView.setAdapter(recyclerView_fav_adapter);
//                        }
//
//                        if (!s.toString().trim().isEmpty()) {
//                            filter_search(s.toString().toLowerCase().trim());
//                            recyclerView_fav_adapter=new RecyclerView_Fav_Adapter(hero_info_tableList_copy);
//                            recyclerView.setAdapter(recyclerView_fav_adapter);
//
//                        }
//
//                    }
//
//                    private void filter_search(String trim) {
//                        if(hero_info_tableList_copy!=null)
//                            hero_info_tableList_copy.clear();
//                        for (int i = 0; i < hero_info_tableList.size(); i++)
//                        {
//                            if(hero_info_tableList.get(i).getHero_name().toLowerCase().contains(trim))
//                            {
//                                hero_info_tableList_copy.add(hero_info_tableList.get(i));
//                            }
//                        }
//                    }
//
//
//                });
//                }
//        });
//        thread.start();
//
//        return view;
//    }
//}
