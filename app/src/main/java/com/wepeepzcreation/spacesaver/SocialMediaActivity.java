package com.wepeepzcreation.spacesaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class SocialMediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Social Media");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {

        int id = view.getId();
        switch (id){
            case R.id.facebook:
                startActivity(new Intent(SocialMediaActivity.this , FacebookActivity.class));
                break;

            case R.id.instagram:
                startActivity(new Intent(SocialMediaActivity.this , InstagramActivity.class));
                break;

            case R.id.snapchat:
                startActivity(new Intent(SocialMediaActivity.this , SnapchatActivity.class));
                break;

            case R.id.youtube:
                startActivity(new Intent(SocialMediaActivity.this , YoutubeActivity.class));
                break;

            case R.id.twitter:
                startActivity(new Intent(SocialMediaActivity.this , TwitterActivity.class));
                break;

            case R.id.gmail:
                startActivity(new Intent(SocialMediaActivity.this , GmailActivity.class));
                break;

            case R.id.linkedIn:
                startActivity(new Intent(SocialMediaActivity.this , LinkedInActivity.class));
                break;

            default:
                break;
        }

    }

}
