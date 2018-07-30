package com.example.abhishek.androidmanifesterweb;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText text;
    RatingBar ratingBar;
    WebView webView;
    ProgressBar progressBar;
    boolean doubleBackToExitPressedOnce = false;
    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String rev;
    Float rat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final int PERMISSION_REQUEST_CODE = 1;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            if ((checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) || (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED)) {

                Log.d("Permission", "Permission denied to SEND_SMS - requesting it");
                String[] permissions = {Manifest.permission.SEND_SMS, Manifest.permission.CALL_PHONE};

                requestPermissions(permissions, PERMISSION_REQUEST_CODE);

            }
        }


        text=findViewById(R.id.editText);
        ratingBar=findViewById(R.id.ratingBar);
        sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        webView = findViewById(R.id.webvw);
        progressBar = findViewById(R.id.progress);
        progressBar.setMax(100);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new Callback());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Contact via:");
                builder.setPositiveButton("Email", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Sms", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, Main3Activity.class);
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
//            final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
//            builder.setMessage("Are you sure you want to exit?");
//            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                 MainActivity.super.onBackPressed();
//
//                }
//            });
//            builder.setNegativeButton("No",null);
//            builder.show();

            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            webView.goBack();

            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            toolbar.setTitle("AndroidManifester");
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {

        int id = item.getItemId();
        webView.setVisibility(View.VISIBLE);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(progress);
                setTitle("Loading...");
                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                    setTitle(item.getTitle());
                }
            }

        });



        if (id == R.id.nav_facebook) {
            webView.loadUrl("https://www.facebook.com/pg/androidmanifester/reviews/");
        } else if (id == R.id.nav_linkedin) {
            webView.loadUrl("https://www.linkedin.com/recs/give?senderId=androidranjith");
        } else if (id == R.id.nav_google) {
            webView.loadUrl("https://g.co/kgs/HAQDhq");
        } else if (id == R.id.nav_justdial) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.justdial.com/Chennai/Android-Manifester-Near-Mari-Hotel-Saidapet/044PXX44-XX44-170105133148-D9F4_BZDET?tab=writereview")));
        } else if (id == R.id.nav_quora) {
            webView.loadUrl("http://qr.ae/TUpgLo");
        } else if (id == R.id.nav_urbanpro) {
            webView.loadUrl("https://www.urbanpro.com/providerRecommendation/fillRecommendation?branchId=613640&fromProfile=fromProfile");
        } else if (id == R.id.nav_website) {
            webView.loadUrl("http://www.androidmanifester.com/");
        } else if (id == R.id.nav_share) {
            try {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                String sAux = "Let me recommend you this application,\n\n";
                sAux = sAux + "https://play.google.com/store/apps/details?id=com.example.abhishek.androidmanifesterweb;";
                i.putExtra(Intent.EXTRA_TEXT, sAux);
                startActivity(Intent.createChooser(i, "Choose one"));
            } catch (Exception e) {
                e.toString();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void calme(MenuItem item) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        String temp = "tel:" + "8148580586";
        intent.setData(Uri.parse(temp));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(intent);
    }

    public void review(View view) {
        rev=text.getText().toString();
        rat=ratingBar.getRating();
        editor.putString("id1",rev);
        editor.putFloat("id2",rat);
        editor.commit();

        Toast.makeText(this, rev+" "+rat, Toast.LENGTH_SHORT).show();
    }
}


 class Callback extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

}
