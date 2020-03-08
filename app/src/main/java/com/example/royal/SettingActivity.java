package com.example.royal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SettingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    AppBarLayout Appbar;
    CollapsingToolbarLayout CoolToolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    EditText email,name,number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        nav();
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.Email);
        name=findViewById(R.id.Name);
        number=findViewById(R.id.Number);

        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db(name.getText().toString(),email.getText().toString(),number.getText().toString());
                email.setText("");
                name.setText("");
                number.setText("");

            }
        });
    }

    private boolean validateForm() {
        boolean valid = true;
        String namee = name.getText().toString().trim();
        if (TextUtils.isEmpty(namee)) {
            name.setError("Required");
            valid = false;
        } else {
            name.setError(null);
        }
        String emaill = email.getText().toString().trim();
        if (TextUtils.isEmpty(emaill)) {
            email.setError("Required.");
            valid = false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emaill).matches()){
            email.setError("Enter Valid Email");
            valid = false;
        }
        else {
            email.setError(null);
        }

        String numb = number.getText().toString().trim();
        if (TextUtils.isEmpty(numb)) {
            number.setError("Required.");
            valid = false;
        }  else {
            number.setError(null);
        }
        return valid;
    }
    public void db(String name,String email,String phone){
        if (!validateForm()){
            return;
        }
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("email", email);
        user.put("phone", phone);


// Add a new document with a generated ID
        db.collection("users").document(mAuth.getUid())
                .update(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Log.d("documentReference", "DocumentSnapshot added with ID: " );

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("documentReference", "Error adding document", e);
                    }
                });


    }

    public void nav() {

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbarr);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar();
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.drawerOpen,R.string.drawerClose);

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
