package com.wepeepzcreation.spacesaver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

//    private ImageButton socialMedia, shopping, google, games, blog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar  = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("");

        //Logo in toolbar
//        getSupportActionBar().setLogo(R.drawable.menu);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);


//        socialMedia = findViewById(R.id.socialMedia);
//        shopping = findViewById(R.id.shopping);
//        google = findViewById(R.id.google);
//        games = findViewById(R.id.game);
//        blog = findViewById(R.id.blog);


        firebaseAuth = FirebaseAuth.getInstance();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);




    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Exit App");
            dialog.setIcon(R.drawable.logo_round);
            dialog.setMessage("Do you really want to quit this app?");
            dialog.setPositiveButton("EXIT ME", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dialog.setCancelable(false);
            dialog.setNegativeButton("STAY HERE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.nav_home:
                break;

            case R.id.nav_profile:
                Toast.makeText(this,"Yet To Do",Toast.LENGTH_LONG).show();
                //TODO: Profile activity here
                break;

            case R.id.nav_logout:

                AlertDialog.Builder dialog = new AlertDialog.Builder(HomeActivity.this);
                dialog.setIcon(R.drawable.logo_round);
                dialog.setCancelable(false);
                dialog.setTitle("Logout");
                dialog.setMessage("Are you sure ?");
                dialog.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    }
                });

                dialog.setNegativeButton("Stay here", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

                break;

            case R.id.nav_contact:
                Toast.makeText(this,"Yet To Do",Toast.LENGTH_LONG).show();
                //Todo: Contact us method
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;

    }

    public void onClick(View view) {

        int id = view.getId();
        switch (id){
            case R.id.socialMedia:
                startActivity(new Intent(HomeActivity.this , SocialMediaActivity.class));
                //do ur code
                break;

            case R.id.shopping:
                startActivity(new Intent(HomeActivity.this , ShoppingActivity.class));
                //do ur code
                break;

            case R.id.google:
                startActivity(new Intent(HomeActivity.this , GoogleActivity.class));
                //do ur code
                break;

            case R.id.game:
                startActivity(new Intent(HomeActivity.this , GameActivity.class));
                //do ur code
                break;

            case R.id.blog:
                startActivity(new Intent(HomeActivity.this , BlogActivity.class));
                //do ur code
                break;

            default:
                break;
        }

    }
}
