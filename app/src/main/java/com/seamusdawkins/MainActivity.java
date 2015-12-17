package com.seamusdawkins;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.seamusdawkins.fragments.FirstFragment;
import com.seamusdawkins.fragments.FourthFragment;
import com.seamusdawkins.fragments.SecondFragment;
import com.seamusdawkins.fragments.ThridFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;

    private ActionBarDrawerToggle drawerToggle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();

        // Lookup navigation view
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Inflate the header view at runtime
        // View headerLayout = nvDrawer.inflateHeaderView(R.layout.nav_header);
        // We can now look up items within the header if needed
        // ImageView ivHeaderPhoto = headerLayout.findViewById(R.id.);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        setupDrawerContent(nvDrawer);

        drawerToggle = setupDrawerToggle();

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.setDrawerListener(drawerToggle);



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        if (savedInstanceState == null) {
            FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
            tx.replace(R.id.flContent, new FirstFragment());
            tx.commit();
        }
    }

    public void setToolbar() {
        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);

                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the planet to show based on
        // position
        Fragment fragment = null;

        Class fragmentClass;

        switch (menuItem.getItemId()) {
            case R.id.nav_first_fragment:
                fragmentClass = FirstFragment.class;
                break;
            case R.id.nav_second_fragment:
                fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_third_fragment:
                fragmentClass = ThridFragment.class;
                break;
            case R.id.nav_fourth_fragment:
                fragmentClass = FourthFragment.class;
                break;
            default:
                fragmentClass = FirstFragment.class;
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item, update the title, and close the drawer

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.open, R.string.close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
