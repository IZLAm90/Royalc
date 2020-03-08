package com.example.royal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;

public class Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    AppBarLayout Appbar;
    CollapsingToolbarLayout CoolToolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    private FirebaseAuth mAuth;
    String id = mAuth.getUid().toString();
    String iq = "vyDRUqinXDNlGYJBCcYQsr6WKVp2";
    @BindView(R.id.admin)
    MenuItem admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        nav();
        Appbar = (AppBarLayout) findViewById(R.id.appBar);
        CoolToolbar = (CollapsingToolbarLayout) findViewById(R.id.collaps);
        CoolToolbar.setContentScrimColor(R.color.colorPrimary);
        findViewById(R.id.offer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OfferActivity.class));
            }
        });

        findViewById(R.id.menuu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });
        findViewById(R.id.event).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EventActivity.class));

            }
        });
        findViewById(R.id.table).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), TableActivity.class));

            }
        });
        findViewById(R.id.royal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RoyalAcivity.class));

            }
        });
        findViewById(R.id.rate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RateActivity.class));

            }
        });
        findViewById(R.id.desidend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DesignActivity.class));

            }
        });
        findViewById(R.id.face).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/"));
                startActivity(intent);

            }
        });
        findViewById(R.id.insta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/"));
                startActivity(intent);
            }
        });
        findViewById(R.id.royalll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));

            }
        });
        mAuth = FirebaseAuth.getInstance();

    }

    public void nav() {

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbarr);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if (iq.equals(id)) {
            admin.setVisible(true);
        me(menuItem.getItemId());
        }


//        switch (menuItem.getItemId()) {
//            case R.id.admin:
//                startActivity(new Intent(getApplicationContext(), AdminActivty.class));
//                Toast.makeText(getApplicationContext(), "Book Selected", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.settings:
//                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
//                Toast.makeText(getApplicationContext(), "Book Selected", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.offer:
//                startActivity(new Intent(getApplicationContext(), OfferActivity.class));
//                Toast.makeText(getApplicationContext(), "Book Selected", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.home:
//                startActivity(new Intent(getApplicationContext(), Main.class));
//                Toast.makeText(getApplicationContext(), "Book Selected", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.event:
//                startActivity(new Intent(getApplicationContext(), EventActivity.class));
//                Toast.makeText(getApplicationContext(), "Book Selected", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.profile:
//                startActivity(new Intent(getApplicationContext(), Profile.class));
//
//                Toast.makeText(getApplicationContext(), "Profile Selected", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.contact:
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://www.facebook.com/"));
//                startActivity(intent);
//                Toast.makeText(getApplicationContext(), "Contact us Selected", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.about:
//                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
//                Toast.makeText(getApplicationContext(), "About us Selected", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.logout:
//                FirebaseAuth auth = FirebaseAuth.getInstance();
//                auth.getUid();
//                Toast.makeText(getApplicationContext(), "Goood by baby", Toast.LENGTH_SHORT).show();
//                auth.signOut();
//                finish();
//                break;
//            case R.id.location:
//                startActivity(new Intent(getApplicationContext(), LocationActivity.class));
//
//                break;
//            default:
//                break;
//        }
        return false;
    }

    public void me(int menuItem){
        switch (menuItem) {
            case R.id.admin:

                startActivity(new Intent(getApplicationContext(), AdminActivty.class));
                Toast.makeText(getApplicationContext(), "Book Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                Toast.makeText(getApplicationContext(), "Book Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.offer:
                startActivity(new Intent(getApplicationContext(), OfferActivity.class));
                Toast.makeText(getApplicationContext(), "Book Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home:
                startActivity(new Intent(getApplicationContext(), Main.class));
                Toast.makeText(getApplicationContext(), "Book Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.event:
                startActivity(new Intent(getApplicationContext(), EventActivity.class));
                Toast.makeText(getApplicationContext(), "Book Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile:
                startActivity(new Intent(getApplicationContext(), Profile.class));

                Toast.makeText(getApplicationContext(), "Profile Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.contact:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/"));
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Contact us Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                Toast.makeText(getApplicationContext(), "About us Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                FirebaseAuth auth = FirebaseAuth.getInstance();
                auth.getUid();
                Toast.makeText(getApplicationContext(), "Goood by baby", Toast.LENGTH_SHORT).show();
                auth.signOut();
                finish();
                break;
            case R.id.location:
                startActivity(new Intent(getApplicationContext(), LocationActivity.class));

                break;
            default:
                break;
        }

    }

}
