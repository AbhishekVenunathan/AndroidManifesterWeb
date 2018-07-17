package com.example.abhishek.androidmanifesterweb;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Contact via:");
                builder.setPositiveButton("Email", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Sms", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(MainActivity.this,Main3Activity.class);
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Are you sure you want to exit?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                 MainActivity.super.onBackPressed();

                }
            });
            builder.setNegativeButton("No",null);
            builder.show();
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
        webView=findViewById(R.id.webvw);
        int id = item.getItemId();

        if (id == R.id.nav_facebook) {
            Toast.makeText(this, "Loading Facebook", Toast.LENGTH_SHORT).show();
            webView.setWebViewClient(new Callback());
            webView.loadUrl("https://www.facebook.com/pg/androidmanifester/reviews/");

        }
        else if (id == R.id.nav_linkedin) {
            Toast.makeText(this, "Loading Linkedin", Toast.LENGTH_SHORT).show();
            webView.setWebViewClient(new Callback());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("https://www.linkedin.com/recs/give?senderId=androidranjith");

        }
        else if (id == R.id.nav_google) {
            Toast.makeText(this, "Loading Google", Toast.LENGTH_SHORT).show();
            webView.setWebViewClient(new Callback());
            webView.loadUrl("https://g.co/kgs/HAQDhq");
        }
        else if (id == R.id.nav_justdial) {
            Toast.makeText(this, "Loading Justdial", Toast.LENGTH_SHORT).show();
            webView.setWebViewClient(new Callback());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("https://www.justdial.com/Chennai/Android-Manifester-Near-Mari-Hotel-Saidapet/044PXX44-XX44-170105133148-D9F4_BZDET?tab=writereview");
        }
        else if (id == R.id.nav_quora) {
            Toast.makeText(this, "Loading Quora", Toast.LENGTH_SHORT).show();
            webView.setWebViewClient(new Callback());
            webView.loadUrl("http://qr.ae/TUpgLo");
        }
        else if (id == R.id.nav_urbanpro) {
            Toast.makeText(this, "Loading Urbanpro", Toast.LENGTH_SHORT).show();
            webView.loadUrl("https://www.urbanpro.com/providerRecommendation/fillRecommendation?branchId=613640&fromProfile=fromProfile");
        }

        else if (id == R.id.nav_website) {
            Toast.makeText(this, "Loading Website", Toast.LENGTH_SHORT).show();
            webView.loadUrl("http://www.androidmanifester.com/");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
 class Callback extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return (false);
    }

}