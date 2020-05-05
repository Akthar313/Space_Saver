package com.wepeepzcreation.spacesaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class ShoppingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Shopping");
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
            case R.id.flipkart:
                startActivity(new Intent(ShoppingActivity.this , FlipkartActivity.class));
                break;

            case R.id.amazon:
                startActivity(new Intent(ShoppingActivity.this , AmazonActivity.class));
                break;

            case R.id.myntra:
                startActivity(new Intent(ShoppingActivity.this , MyntraActivity.class));
                break;

            case R.id.snapdeal:
                startActivity(new Intent(ShoppingActivity.this , SnapdealActivity.class));
                break;

            case R.id.ajio:
                startActivity(new Intent(ShoppingActivity.this , AjioActivity.class));
                break;

            default:
                break;
        }

    }
}
