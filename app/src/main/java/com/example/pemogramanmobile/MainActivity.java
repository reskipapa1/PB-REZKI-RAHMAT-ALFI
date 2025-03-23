package com.example.pemogramanmobile;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextInputEditText emailPengguna, password;
    CheckBox ingatSaya;
    Button btnLogin;
    TextView lupaPassword, signUp;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        emailPengguna = findViewById(R.id.emailPengguna);
        password = findViewById(R.id.password);
        ingatSaya = findViewById(R.id.ingatSaya);
        btnLogin = findViewById(R.id.btnLogin);
        lupaPassword = findViewById(R.id.lupaPassword);
        signUp = findViewById(R.id.signUp);

        // Tombol login
        btnLogin.setOnClickListener(view -> {
            String email = emailPengguna.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                emailPengguna.setError("Masukkan email");
                return;
            }
            if (TextUtils.isEmpty(pass)) {
                password.setError("Masukkan password");
                return;
            }

            loginUser(email, pass);
        });

        // Pindah ke halaman daftar jika belum punya akun
        signUp.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        });
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            // Langsung pindah ke Home tanpa cek verifikasi email
                            Intent intent = new Intent(MainActivity.this, home.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Login gagal. Periksa email dan password!", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
