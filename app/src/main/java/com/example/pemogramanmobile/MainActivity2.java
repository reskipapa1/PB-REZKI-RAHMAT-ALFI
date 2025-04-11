package com.example.pemogramanmobile;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pemogramanmobile.Model.UserDetails;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {
    Button btnSignUp;
    TextInputEditText usernameSignUp, passwordSignUp, nimPengguna, emailPengguna;
    FirebaseAuth mAuth;
    private static final String TAG = "MainActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSignUp = findViewById(R.id.btnSignUp);
        usernameSignUp = findViewById(R.id.usernameSignUp);
        emailPengguna = findViewById(R.id.emailPengguna);
        passwordSignUp = findViewById(R.id.passwordSignUp);
        nimPengguna = findViewById(R.id.nimPengguna);

        btnSignUp.setOnClickListener(view -> {
            String username, email, password, NIM;

            username = String.valueOf(usernameSignUp.getText());
            email = String.valueOf(emailPengguna.getText());
            password = String.valueOf(passwordSignUp.getText());
            NIM = String.valueOf(nimPengguna.getText());

            if (TextUtils.isEmpty(username)){
                Toast.makeText(MainActivity2.this, "Enter username", Toast.LENGTH_LONG).show();
                usernameSignUp.requestFocus();
            } else if (TextUtils.isEmpty(email)){
                Toast.makeText(MainActivity2.this, "Enter email", Toast.LENGTH_LONG).show();
                emailPengguna.requestFocus();
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(MainActivity2.this, "Enter password", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(NIM)) {
                Toast.makeText(MainActivity2.this, "Enter NIM", Toast.LENGTH_LONG).show();
            } else {
                // Register user tanpa verifikasi email
                registerUser(username, email, password, NIM);

            }
        });
    }

    private void registerUser(String username, String email, String password, String NIM) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity2.this, task -> {
            if (task.isSuccessful()) {
                FirebaseUser fUser = auth.getCurrentUser();
                String uid = fUser.getUid();

                UserDetails userDetails = new UserDetails(uid, username, email, password, NIM);

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                reference.child(uid).setValue(userDetails).addOnCompleteListener(task1 -> {
                    if (task1.isSuccessful()){
                        Toast.makeText(MainActivity2.this, "Account created successfully!", Toast.LENGTH_LONG).show();

                        // Langsung pindah ke Home tanpa verifikasi email
                        Intent intent = new Intent(MainActivity2.this, MainFragment.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity2.this, "Account registration failed", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Register: Error");
                    }
                });
            } else {
                try {
                    throw task.getException();
                } catch (FirebaseAuthUserCollisionException e) {
                    emailPengguna.setError("Email is already registered");
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                    Toast.makeText(MainActivity2.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
