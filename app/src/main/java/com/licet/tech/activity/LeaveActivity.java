package com.licet.tech.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.licet.tech.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LeaveActivity extends AppCompatActivity {
    @BindView(R.id.l_noofdays)
    Spinner nodays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);
        ButterKnife.bind(this);
        String[] days={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};

        nodays.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,days));
    }
}