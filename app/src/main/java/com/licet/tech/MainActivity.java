package com.licet.tech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.licet.tech.fragment.AdmissionFragment;
import com.licet.tech.fragment.ContactFragment;
import com.licet.tech.fragment.GalleryFragment;
import com.licet.tech.fragment.HomeFragment;
import com.licet.tech.fragment.ParentFragment;
import com.licet.tech.fragment.TransportFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragment homeFragment = new HomeFragment();
        CallFragment(homeFragment);
    }


    public void CallFragment(Fragment fragment) {
        FragmentTransaction transactionss = getSupportFragmentManager().beginTransaction();
        transactionss.add(R.id.frame, fragment);
        transactionss.addToBackStack("NewFragment");
        transactionss.commit();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            HomeFragment homeFragment = new HomeFragment();
            CallFragment(homeFragment);
        }
        if (id == R.id.nav_gallary) {
            GalleryFragment galleryFragment = new GalleryFragment();
            CallFragment(galleryFragment);
        }
        if (id == R.id.nav_admission) {
            AdmissionFragment admissionFragment = new AdmissionFragment();
            CallFragment(admissionFragment);
        }
        if (id == R.id.nav_contact) {
            ContactFragment contactFragment = new ContactFragment();
            CallFragment(contactFragment);
        }
        if (id == R.id.nav_transport) {
            TransportFragment transportFragment = new TransportFragment();
            CallFragment(transportFragment);
        }
        if (id == R.id.nav_parent_login) {
            ParentFragment parentFragment = new ParentFragment();
            CallFragment(parentFragment);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}