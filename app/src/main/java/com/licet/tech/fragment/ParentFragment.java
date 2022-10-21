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
import androidx.fragment.app.FragmentTransaction;


import com.licet.tech.MainActivity;
import com.licet.tech.R;
import com.licet.tech.activity.AttendenceActivity;
import com.licet.tech.activity.FeesActivity;
import com.licet.tech.activity.LeaveActivity;
import com.licet.tech.activity.ParentFeedbackActivity;
import com.licet.tech.activity.ParentLogin;
import com.licet.tech.activity.ProfileActivity;
import com.licet.tech.activity.ResultActivity;
import com.licet.tech.utils.MyPreference;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ParentFragment extends Fragment {
    View view;
    MyPreference myPreference;

    @BindView(R.id.parent_attendence)
    ImageView parent_attendence;
    @BindView(R.id.parent_profile)
    ImageView parent_profile;
    @BindView(R.id.message)
    ImageView message;
    @BindView(R.id.leaveform)
    ImageView leaveform;
    @BindView(R.id.parent_feestructure)
    ImageView parent_feestructure;
    @BindView(R.id.parent_result)
    ImageView parent_result;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the view for the fragment based on layout XML
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("push", MODE_PRIVATE);
        view = inflater.inflate(R.layout.activity_parent_home, container, false);
        ButterKnife.bind(this, view);
        parent_attendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AttendenceActivity.class);
                startActivity(intent);
            }
        });
        parent_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ParentFeedbackActivity.class);
                startActivity(intent);
            }
        });
        leaveform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LeaveActivity.class);
                startActivity(intent);
            }
        });

        parent_feestructure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeesActivity.class);
                startActivity(intent);
            }
        });
        parent_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ResultActivity.class);
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

                    Intent intent=new Intent(getActivity(),MainActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                    return true;
                }
                return false;
            }
        });
    }
    public void CallFragment(Fragment fragment) {
        FragmentTransaction transactionss = getActivity().getSupportFragmentManager().beginTransaction();
        transactionss.add(R.id.frame, fragment);
        transactionss.addToBackStack("NewFragment");
        transactionss.commit();
    }
}

