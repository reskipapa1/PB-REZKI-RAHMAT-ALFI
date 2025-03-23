package com.example.pemogramanmobile;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends AppCompatActivity {
    private TextView tvEmail, tvUsername;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Edge to Edge Layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvEmail = findViewById(R.id.tvEmail);
        tvUsername = findViewById(R.id.tvUsername);
        mAuth = FirebaseAuth.getInstance();

        // Ambil data user dari Firebase Authentication
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            tvEmail.setText("Email: " + user.getEmail());

            // Ambil username dari email (sebelum @)
            String email = user.getEmail();
            if (email != null && email.contains("@")) {
                String username = email.split("@")[0]; // Ambil bagian sebelum '@'
                tvUsername.setText("Username: " + username);
            }
        }
    }
}
