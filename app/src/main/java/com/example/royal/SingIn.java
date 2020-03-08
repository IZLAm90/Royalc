package com.example.royal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingIn extends AppCompatActivity {
//vision.official2030@gmail.com
    //Mm20501900
    //vyDRUqinXDNlGYJBCcYQsr6WKVp2
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference mDatabase;
    @BindView(R.id.Pass)
    EditText pass;
    @BindView(R.id.Email)
    EditText email;
    @BindView(R.id.ok)
    ImageButton siging;
    @BindView(R.id.Signin2)
    Button Signin2;
    @BindView(R.id.Signiup2)
    Button Signiup2;
    @BindView(R.id.remember)
    CheckBox remember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();
        mDatabase = database.getInstance().getReference();
        SharedPreferences sharedPreferencess = getSharedPreferences("save me", MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedPreferencess.edit();
        siging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(email.getText().toString(), pass.getText().toString());
                if(remember.isChecked()){
                    editor3.putString("email",email.getText().toString());
                    editor3.putString("pass",pass.getText().toString());
                    editor3.commit();
                }
            }
        });
        Signin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(email.getText().toString(), pass.getText().toString());
            }
        });
        Signiup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));

            }
        });
    }
    private void signIn(String email, String password) {
        Log.d("Singin", "signIn:" + email);
        if (!validateForm()) {
            return;
        }
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Authentication doooooon.",
                                    Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), Main.class));
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Singin", "signInWithEmail:success");
                            // finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Singin", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });
        // [END sign_in_with_email]
    }
    private boolean validateForm() {
        boolean valid = true;
        String emaill = email.getText().toString();
        if (TextUtils.isEmpty(emaill)) {
            email.setError("Required.");
            valid = false;
        } else {
            email.setError(null);
        }
        String passwordd = pass.getText().toString();
        if (TextUtils.isEmpty(passwordd)) {
            pass.setError("Required.");
            valid = false;
        } else {
            pass.setError(null);
        }
        return valid;
    }
    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sharedPreferencess = getSharedPreferences("save me", MODE_PRIVATE);
        sharedPreferencess.getAll();
        if ( sharedPreferencess.getAll().isEmpty()){
            return;
        }
        else {
            startActivity(new Intent(getApplicationContext(), Main.class));

        }
//        // Check auth on Activity start
//        if (mAuth.getCurrentUser() != null) {
//          startActivity(new Intent(getApplicationContext(), Main.class));
//
//        }
//        else {
//
//            startActivity(new Intent(getApplicationContext(), SignUp.class));
//
//        }
    }
//    private String usernameFromEmail(String email) {
//        if (email.contains("@")) {
//            return email.split("@")[0];
//        } else {
//            return email;
//        }
//    }
    // [START basic_write]

}
