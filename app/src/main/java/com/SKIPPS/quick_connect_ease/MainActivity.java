package com.SKIPPS.quick_connect_ease;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.example.secureserver.R;
import com.example.secureserver.ui.Download_File.Download_File_Fragment;
import com.example.secureserver.ui.HTTP.HTTP_Fragment;
import com.example.secureserver.ui.Power_Management.Power_Mgmt_Fragment;
import com.example.secureserver.ui.SSH.SSHFragment;
import com.example.secureserver.ui.Upload_File.Upload_File_Fragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.secureserver.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_power_mgmt,R.id.nav_upload_file, R.id.nav_ssh, R.id.nav_download_file,R.id.nav_http)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_power_mgmt) {
                navController.navigate(R.id.nav_power_mgmt);
            } else if (id == R.id.nav_http) {
                navController.navigate(R.id.nav_http);
            } else if (id == R.id.nav_ssh) {
                navController.navigate(R.id.nav_ssh);
            } else if (id == R.id.nav_upload_file) {
                navController.navigate(R.id.nav_upload_file);
            } else if (id == R.id.nav_download_file) {
                navController.navigate(R.id.nav_download_file);
            }
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_manual) {
            Intent intent = new Intent(MainActivity.this, ManualActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_about_us) {
            Intent intent = new Intent(MainActivity.this, About_us_Activtiy.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_contact_us) {
            Intent intent = new Intent(MainActivity.this, Contact_us_Activity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.menu_settings) {
            Intent intent = new Intent(MainActivity.this, ConfigActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.menu_logout) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}