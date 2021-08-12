package com.example.navigation.ui.slideshow;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.navigation.Home_Screen;
import com.example.navigation.List_details;
import com.example.navigation.R;
import com.example.navigation.RecyclerView_Adapter;
import com.example.navigation.databinding.FragmentSlideshowBinding;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {
    RecyclerView recyclerView;
    List<String> list_name, list_image;
    List<Integer> list_id;
    List<String> name = new ArrayList<>();
    List<String> image_url = new ArrayList<>();
    List<Integer> id = new ArrayList<>();
    List<List_details> list_filter = new ArrayList<>();
    String c = "";
    List_details list_details;
    EditText editText;
    RecyclerView_Adapter recyclerview_adapter;

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       /* binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.editTextTextPersonName;
        textView.setText("slideshow");
        return root;*/
        View view= inflater.inflate(R.layout.fragment_slideshow, container, false);
        recyclerView=view.findViewById(R.id.recycler_view);
        recyclerview_adapter = new RecyclerView_Adapter(Home_Screen.list_name,Home_Screen.list_id,Home_Screen.list_img_url);
        recyclerView.setAdapter(recyclerview_adapter);
        editText = (EditText) view.findViewById(R.id.editTextTextPersonName);
        ImageButton b_search,b_cancel;
        b_search=(ImageButton)view.findViewById(R.id.search);
        b_cancel=(ImageButton)view.findViewById(R.id.cancel);

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
                    recyclerview_adapter = new RecyclerView_Adapter(Home_Screen.list_name, Home_Screen.list_id, Home_Screen.list_img_url);
                    recyclerView.setAdapter(recyclerview_adapter);
                }

                if (!s.toString().trim().isEmpty()) {
                    if (name != null)
                        name.clear();
                    if (id != null)
                        id.clear();
                    if (image_url != null)
                        image_url.clear();
                    filter_search(s.toString().toLowerCase().trim());
                    recyclerview_adapter = new RecyclerView_Adapter(name, id, image_url);
                    recyclerView.setAdapter(recyclerview_adapter);
                }
            }
        });
        if(editText.getText().equals("")) {
            b_search.setVisibility(View.VISIBLE);
            b_cancel.setVisibility(View.GONE);
        }
        return view;
    }
    private void filter_search(String trim) {
        //  Toast.makeText(MainActivity.this, trim, Toast.LENGTH_SHORT).show();

        if (name != null)
            name.clear();
        if (id != null)
            id.clear();
        if (image_url != null)
            image_url.clear();
        for (int i = 0; i < Home_Screen.list_name.size(); i++) {

            if (Home_Screen.list_name.get(i).toLowerCase().contains(trim)||Home_Screen.list_id.get(i).toString().contains(trim)) {

                name.add(Home_Screen.list_name.get(i));
                id.add(Home_Screen.list_id.get(i));
                image_url.add(Home_Screen.list_img_url.get(i));
            }
        }

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}