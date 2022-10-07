package com.licet.tech.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import com.licet.tech.R;
import com.licet.tech.activity.AttendenceActivity;
import com.licet.tech.utils.MyPreference;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ParentFragment extends Fragment {
    View view;
    MyPreference myPreference;

    @BindView(R.id.parent_attendence)
    ImageView parent_attendence;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the view for the fragment based on layout XML
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("push", MODE_PRIVATE);
        view = inflater.inflate(R.layout.activity_parent_home, container, false);
        ButterKnife.bind(this, view);
        parent_attendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), AttendenceActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });
    }
}

