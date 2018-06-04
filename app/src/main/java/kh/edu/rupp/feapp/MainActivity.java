package kh.edu.rupp.feapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        Toolbar tlbMain = findViewById(R.id.tlb_main);
        setSupportActionBar(tlbMain);
        getSupportActionBar().setTitle("Faculty of Engineering");

        // Create actionbar drawer toggle
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, tlbMain, R.string.drawer_open_desc, R.string.drawer_close_desc);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        //displayContactFragment();
        displayEventsFragment();

    }


    private void displayContactFragment() {
        ContactFragment contactFragment = new ContactFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lyt_fragment_container, contactFragment);
        fragmentTransaction.commit();
    }

    private void displayEventsFragment() {
        EventsFragment eventsFragment = new EventsFragment();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lyt_fragment_container, eventsFragment);
        fragmentTransaction.commit();
    }

}




















