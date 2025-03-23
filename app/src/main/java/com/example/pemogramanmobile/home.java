package com.example.pemogramanmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class home extends AppCompatActivity {
    private TextView tvTitle, tvDescription;
    private FirebaseAuth mAuth;
    private Button btnGoToProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Handling insets untuk menghindari overlap dengan status bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Hubungkan TextView dan Button dengan ID dari XML
        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        btnGoToProfile = findViewById(R.id.btnGoToProfile);

        // Dapatkan instance FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        // Ambil data user dari Firebase Authentication
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            String username = email != null && email.contains("@") ? email.split("@")[0] : "User";

            // Set teks di TextView secara dinamis
            tvTitle.setText("Halo, " + username + "!");
            tvDescription.setText("Email kamu: " + email);
        } else {
            tvTitle.setText("Halo, Pengguna!");
            tvDescription.setText("Silakan login terlebih dahulu.");
        }

        // Pindah ke halaman Profile saat tombol diklik
        btnGoToProfile.setOnClickListener(view -> {
            Intent intent = new Intent(home.this, Profile.class);
            startActivity(intent);
        });
    }
}
