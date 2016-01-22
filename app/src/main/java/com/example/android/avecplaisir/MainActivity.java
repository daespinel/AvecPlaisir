package com.example.android.avecplaisir;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageSwitcher imageSwitcher;
    ImageButton btnNext;
    ImageButton btnBack;

    // Array of Image IDs to Show In ImageSwitcher
    int imageIds[]={R.drawable.receta,R.drawable.baby,R.drawable.etapes};
    int messageCount=imageIds.length;
    // to keep current Index of ImageID array
    int currentIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // get The references
        btnNext=(ImageButton)findViewById(R.id.button_next);
        btnBack=(ImageButton)findViewById(R.id.button_back);
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);

        // Set the ViewFactory of the ImageSwitcher that will create ImageView object when asked
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            public View makeView() {
                // TODO Auto-generated method stub

                // Create a new ImageView set it's properties
                ImageView imageView = new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
                return imageView;
            }
        });

        imageSwitcher.setImageResource(imageIds[currentIndex]);

        // Declare the animations and initialize them
        Animation inLeft = AnimationUtils.loadAnimation(this,R.anim.in_from_left);
        Animation outRight = AnimationUtils.loadAnimation(this, R.anim.out_to_right);
        Animation inRight = AnimationUtils.loadAnimation(this,R.anim.in_from_right);
        Animation outLeft = AnimationUtils.loadAnimation(this, R.anim.out_to_left);

        // set the animation type to imageSwitcher
        imageSwitcher.setInAnimation(inLeft);
        imageSwitcher.setOutAnimation(outRight);

        //imageSwitcher.setInAnimation(inRight);
        //imageSwitcher.setOutAnimation(outLeft);


        // ClickListener for NEXT button
        // When clicked on Button ImageSwitcher will switch between Images
        // The current Image will go OUT and next Image  will come in with specified animation
        btnNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                currentIndex++;
                // If index reaches maximum reset it
                if(currentIndex==messageCount)
                    currentIndex=0;
                imageSwitcher.setImageResource(imageIds[currentIndex]);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                // If index reaches maximum reset it
                if(currentIndex==0)
                    currentIndex=messageCount-1;
                else
                    currentIndex--;
                imageSwitcher.setImageResource(imageIds[currentIndex]);
            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_accueil) {
            // Handle the camera action
        } else if (id == R.id.nav_chercher) {
            item.setChecked(true);

        } else if (id == R.id.nav_cuisine) {
            item.setChecked(true);

        } else if (id == R.id.nav_entrees) {
            item.setChecked(true);

        }else if (id == R.id.nav_plats) {
            item.setChecked(true);

        }else if (id == R.id.nav_desserts) {
            item.setChecked(true);

        }else if (id == R.id.nav_boissons) {
            item.setChecked(true);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void pasarActividad2(View v) {
        Intent act = new Intent(this,Main2Activity.class);

        startActivity(act);
    }
}
