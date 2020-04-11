package kh.com.ilost.activities;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import kh.com.ilost.R;
import kh.com.ilost.fragments.FragmentHome;
import kh.com.ilost.fragments.FragmentMessage;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener {


    BottomNavigationView bottomNavigationView;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        onHomeClick();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_home) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_layout, new FragmentHome()).commit();
        } else if (itemId == R.id.navigation_message) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_layout, new FragmentMessage()).commit();
        }
        return true;
    }


    private void onHomeClick() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_layout, new FragmentHome()).commit();
    }

}
