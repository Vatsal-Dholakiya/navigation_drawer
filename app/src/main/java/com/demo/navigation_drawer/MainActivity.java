package com.demo.navigation_drawer;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.demo.navigation_drawer.ui.gallery.GalleryFragment;
import com.demo.navigation_drawer.ui.home.HomeFragment;
import com.demo.navigation_drawer.ui.slideshow.SlideshowFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    //declare variables
    NavigationView navigationView;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // assign ids of drawer and navigationview
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // set custom toggle menu to open and close drawer
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        //set menu icon
        drawerToggle.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        drawerToggle.setDrawerIndicatorEnabled(false);
        drawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        //set default fragment which will open first
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                    new HomeFragment()).commit();
        }

        //set navigation menu item click event
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                                new HomeFragment()).commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_gallery:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                                new GalleryFragment()).commit();
                        drawer.closeDrawers();
                        break;
                    case R.id.nav_slideshow:
                        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                                new SlideshowFragment()).commit();
                        drawer.closeDrawers();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }
}