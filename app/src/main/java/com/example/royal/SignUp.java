package com.example.royal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    @BindView(R.id.Name) EditText name;
    @BindView(R.id.Email) EditText email;
    @BindView(R.id.Pass) EditText pass;
    @BindView(R.id.phone) EditText phone;
    @BindView(R.id.pirfire) EditText pir1;
    @BindView(R.id.pirsac) EditText pir2;
    @BindView(R.id.pirth) EditText pir3;
  //  @BindView(R.id.doon) EditText Signup;
   // @BindView(R.id.signup) EditText Signup2;
    ImageButton Signup;
    Button Signup2;
//    private static final Pattern PASSWORD_PATTERN =
//            Pattern.compile("^" +
//                    //"(?=.*[0-9])" +         //at least 1 digit
//                    //"(?=.*[a-z])" +         //at least 1 lower case letter
//                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
//                    "(?=.*[a-zA-Z])" +      //any letter
//                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
//                    "(?=\\S+$)" +           //no white spaces
//                    ".{4,}" +               //at least 4 characters
//                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        ButterKnife.bind(this);
        declaration();
        Signup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(email.getText().toString(),pass.getText().toString());
            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(email.getText().toString(),pass.getText().toString());
            }
        });
        findViewById(R.id.moveto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SingIn.class));
            }
        });

    }
    public void declaration() {
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        Signup=findViewById(R.id.doon);
        Signup2=findViewById(R.id.signup);
    }
    public void db(String name,String email,String pass,String phone,String pire){

        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("email", email);
        user.put("pass", pass);
        user.put("phone", phone);
        user.put("paireth", pire);


// Add a new document with a generated ID
        db.collection("users").document(mAuth.getUid())
                .set(user)
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
    private void createAccount(final String email, final String password) {
        Log.d("Account create", "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "doooooooooooooooooooooooooooooon", Toast.LENGTH_LONG).show();
                            db(name.getText().toString(),email,password,phone.getText().toString(),pir1.toString()+pir2.toString()+pir3.toString());
                            startActivity(new Intent(getApplicationContext(), Main.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("failure email", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        // [END create_user_with_email]
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
        String passwordd = pass.getText().toString().trim();
        if (TextUtils.isEmpty(passwordd)) {
            pass.setError("Required.");
            valid = false;
        }
        else if(passwordd.length() < 8){
            pass.setError("Enter password moer than this");
            valid = false;
        }
        else {
            pass.setError(null);
        }
        String phoe =phone.getText().toString().trim();
        if (TextUtils.isEmpty(phoe)) {
            phone.setError("Required");
            valid = false;
        } else {
            phone.setError(null);
        }

        return valid;
    }
        @Override
    public void onStart() {
        super.onStart();
        // Check auth on Activity start
        if (mAuth.getCurrentUser() != null) {
          startActivity(new Intent(getApplicationContext(), Main.class));
        }

    }
}
