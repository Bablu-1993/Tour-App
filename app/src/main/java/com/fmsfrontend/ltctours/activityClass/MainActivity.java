package com.fmsfrontend.ltctours.activityClass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.fmsfrontend.ltctours.R;
import com.fmsfrontend.ltctours.adapterClass.NavigationAdapter;
import com.fmsfrontend.ltctours.fragment.AboutUsFragment;
import com.fmsfrontend.ltctours.fragment.HomeFragment;
import com.fmsfrontend.ltctours.fragment.ReviewsFragment;
import com.fmsfrontend.ltctours.prefrence.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    TextView txtTitle;
    SharedPrefManager  SharedPrefManager;
    FrameLayout flFragment;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView =findViewById(R.id.nvView);
        flFragment=findViewById(R.id.flFragment);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        SharedPrefManager = new SharedPrefManager(this);
        // SharedPrefManager.getInstance(this).getUser();
        SharedPrefManager.setLoggedIn(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        toolbar = findViewById(R.id.toolbar);
        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("Big Holidays ");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        bottomNavigationView.setSelectedItemId(R.id.tab_home);
        bottomNavigationView.setItemIconTintList(null);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                //item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id){
                    case R.id.nav_home:
                        replaceFragment(new HomeFragment());break;
                    case R.id.nav_review:
                        replaceFragment(new ReviewsFragment()); break;
                    case R.id. nav_about:
                       replaceFragment(new AboutUsFragment());break;
                    case R.id.nav_Share:
                        Intent intentShare = new Intent(Intent.ACTION_SEND);
                        intentShare.setType("text/plain");
                        intentShare.putExtra(Intent.EXTRA_SUBJECT, "Share link here");
                        startActivity(Intent.createChooser(intentShare, "choose one"));break;
                    case R.id.nav_Logout:
                        androidx.appcompat.app.AlertDialog.Builder alertDialog = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);
                        alertDialog.setMessage(" Are you sure?\n Do you want to Sign Out?");
                        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                SharedPrefManager.logoutSession();

                                finish();
                                dialogInterface.dismiss();
                            }
                        });
                        alertDialog.show();
                        break;

                }
                return false;
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.tab_home:
                            Fragment homeFragment = new HomeFragment();
                            loadFragment(homeFragment);
                            break;
                        case R.id.about:
                            Fragment aboutFragment = new AboutUsFragment();
                            loadFragment(aboutFragment);
                            break;
                        case R.id.review:
                            Fragment reviewFragment = new ReviewsFragment();
                            loadFragment(reviewFragment);
                            break;
                    }
                    return true;
                }
            };
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment,fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
    public void popBackStackTillEntry(int entryIndex) {

        if (getSupportFragmentManager() == null) {
            return;
        }
        if (getSupportFragmentManager().getBackStackEntryCount() <= entryIndex) {
            return;
        }
        FragmentManager.BackStackEntry entry = getSupportFragmentManager().getBackStackEntryAt(
                entryIndex);
        if (entry != null) {
            getSupportFragmentManager().popBackStackImmediate(entry.getId(),
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            popBackStackTillEntry(0);
            moveTaskToBack(true);
            System.exit(0);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        loadFragment(new HomeFragment());
        MenuItem homeItem = bottomNavigationView.getMenu().getItem(0);
        bottomNavigationView.setSelectedItemId(homeItem.getItemId());
        Toast.makeText(this, "Please click again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}