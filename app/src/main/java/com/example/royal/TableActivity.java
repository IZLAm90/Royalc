package com.example.royal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.royal.Adapter.TableAdapter;
import com.example.royal.Model.TableItems;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class TableActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    AppBarLayout Appbar;
    CollapsingToolbarLayout CoolToolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<TableItems> tableItems;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        CoolToolbar = (CollapsingToolbarLayout)findViewById(R.id.collaps);
        CoolToolbar.setContentScrimColor(R.color.colorgreen);
        nav();
        CreateTabls();
        Intent result=getIntent();
        i=result.getIntExtra("profile",0);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.back, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                startActivity(new Intent(getApplicationContext(),Main.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void CreateTabls(){
        mRecyclerView=findViewById(R.id.recyclerviewt);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new GridLayoutManager(getApplicationContext(),4);
        tableItems=new ArrayList<>();
        for (int i=1;i<=65;i++){
            tableItems.add(new TableItems("" +i));
        }
        adapter=new TableAdapter(getApplicationContext(),tableItems,i);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    public void nav(){
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbarr);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

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
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.facebook.com/"));
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Contact us Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.about:
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                Toast.makeText(getApplicationContext(), "About us Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                FirebaseAuth auth=FirebaseAuth.getInstance();
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
