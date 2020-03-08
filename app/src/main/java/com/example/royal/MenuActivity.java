package com.example.royal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.royal.Adapter.PageAdapter;
import com.example.royal.Model.TModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabs;
    private ViewPager viewPager;
    private TabItem hot, cold, sweet;
    private PagerAdapter pagerAdapter;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    public int total;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        tabs = findViewById(R.id.tabs);
        hot = findViewById(R.id.hot);
        cold = findViewById(R.id.cold);
        viewPager = findViewById(R.id.viewpager);
        pagerAdapter = new PageAdapter(getSupportFragmentManager(), tabs.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        nav();
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    pagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 1) {
                    pagerAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 2) {
                    pagerAdapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs));
    }
    public void nav() {
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbarr);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_save);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //    public void lood(){
//        SharedPreferences sharedPreferences =getSharedPreferences("share",MODE_PRIVATE) ;
//        total=sharedPreferences.getInt("sum",0);
//
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        SharedPreferences sharedPreferencess = getSharedPreferences("shares", MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedPreferencess.edit();
        SharedPreferences sharedPreferencesc = getSharedPreferences("sharec", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferencesc.edit();
        SharedPreferences sharedPreferences = getSharedPreferences("share", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (item.getItemId()) {
            case R.id.save_note:
                Intent result = getIntent();
                boolean point=result.getBooleanExtra("point",false);
                int num = result.getIntExtra("index", 0);
                int table = result.getIntExtra("Table", 0);
                if (table == 20&& !point) {
                    total = sharedPreferences.getInt("sum", 0) + sharedPreferencesc.getInt("sum", 0)
                            + sharedPreferencess.getInt("sum", 0);
                    Toast.makeText(getApplicationContext(), total + "Book" + num, Toast.LENGTH_SHORT).show();
                    editor.clear();
                    editor.commit();
                    editor2.clear();
                    editor2.commit();
                    editor3.clear();
                    editor3.commit();
                    if (total > 0)
                        CreuatOffer(num+"", total);
                    startActivity(new Intent(getApplicationContext(), Main.class));
                }
                else if(table==20&&point) {
                    total = sharedPreferences.getInt("sum", 0) + sharedPreferencesc.getInt("sum", 0)
                            + sharedPreferencess.getInt("sum", 0);
                    Toast.makeText(getApplicationContext(), total + "Book" + num, Toast.LENGTH_SHORT).show();
                    editor.clear();
                    editor.commit();
                    editor2.clear();
                    editor2.commit();
                    editor3.clear();
                    editor3.commit();
                    if (total > 0)
                        CreuatOffer(num+"p", total);
                    startActivity(new Intent(getApplicationContext(), Main.class));
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void CreuatOffer(String  num, int pri) {
        CollectionReference reference = FirebaseFirestore.getInstance()
                .collection("Tabels");
        reference.add(new TModel(num, pri)).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                Toast.makeText(getApplicationContext(), "Tabel Started", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
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
        return false;
    }
}
