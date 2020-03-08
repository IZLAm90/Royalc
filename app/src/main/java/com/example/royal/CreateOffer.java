package com.example.royal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.royal.Model.EventModel;
import com.example.royal.Model.OfferModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateOffer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    AppBarLayout Appbar;
    CollapsingToolbarLayout CoolToolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    TextView name,date;
    Button image;
    EditText desc;
    @BindView(R.id.im)
    LinearLayout im;
    private Uri mImageUri;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
  //  private StorageReference mStorageRef=FirebaseStorage.getInstance().getReference("EventImges");
  private StorageTask mUploadTask;
  private String up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_offer);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        ButterKnife.bind(this);
        Appbar = (AppBarLayout)findViewById(R.id.appBar);
        CoolToolbar = (CollapsingToolbarLayout)findViewById(R.id.collaps);
       // CoolToolbar.setContentScrimColor(R.color.colorPrimary);
        nav();
        inti();
        findViewById(R.id.Offerdone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreuatOffer();
                clear();
            }
        });
      im.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              openFileChooser();
          }
      });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Toast.makeText(getApplicationContext(),"uri"+mImageUri,Toast.LENGTH_LONG).show();

        }
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    public void uploadimage(){
        StorageReference fileReference = storageRef.child(System.currentTimeMillis()
                + "." + getFileExtension(mImageUri));
        mUploadTask = fileReference.putFile(mImageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        up=taskSnapshot.getUploadSessionUri().getPath();
                        Toast.makeText(getApplicationContext(), "Upload successful"+taskSnapshot.getUploadSessionUri() , Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    }
                });
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }
    public void clear(){
        name.setText("");
        desc.setText("");
        date.setText("");

    }





    private void CreuatOffer() {
        if (validateForm()) {
            uploadimage();
            CollectionReference reference = FirebaseFirestore.getInstance()
                    .collection("Offers");
            reference.add(new OfferModel(name.getText().toString() ,up,date.getText().toString(), desc.getText().toString())).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(getApplicationContext(), "Notes added", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }




    public void uplood(Uri image){
        StorageReference imagesRef = storageRef.child("images");
        Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));

        StorageReference riversRef = storageRef.child("images/rivers.jpg");

        riversRef.putFile(file)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                  //      Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });

    }




    public void inti() {
        name = findViewById(R.id.Offername);
        desc = findViewById(R.id.descr);
        date = findViewById(R.id.Offerddd);
    }
    private boolean validateForm() {
        boolean valid = true;

        String nam = name.getText().toString();
        if (TextUtils.isEmpty(nam)) {
            name.setError("Required.");
            valid = false;
        } else {
            name.setError(null);
        }

        String dae = date.getText().toString();
        if (TextUtils.isEmpty(dae)) {
            date.setError("Required.");
            valid = false;
        } else {
            date.setError(null);
        }
        String descc = desc.getText().toString();
        if (TextUtils.isEmpty(descc)) {
            desc.setError("Required.");
            valid = false;
        } else {
            desc.setError(null);
        }

        return valid;
    }
    public void nav() {

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbarr);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
