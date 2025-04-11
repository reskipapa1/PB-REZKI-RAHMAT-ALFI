package com.example.pemogramanmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeFragment extends Fragment {

    private FirebaseAuth mAuth;
    private TextView tvTitle, tvDescription;
    private Button btnCatatanKeuangan, btnMemoHarian;

    public HomeFragment() {
        // Konstruktor kosong
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home, container, false);

        // Inisialisasi Firebase
        mAuth = FirebaseAuth.getInstance();

        // Hubungkan komponen
        tvTitle = view.findViewById(R.id.tvTitle);
        tvDescription = view.findViewById(R.id.tvDescription);
        btnCatatanKeuangan = view.findViewById(R.id.btnCatatanKeuangan);
        btnMemoHarian = view.findViewById(R.id.btnMemoHarian);

        // Ambil data user
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            String username = (email != null && email.contains("@")) ? email.split("@")[0] : "User";
            tvTitle.setText("Halo, " + username + "!");
            tvDescription.setText("Email kamu: " + email);
        } else {
            tvTitle.setText("Halo, Pengguna!");
            tvDescription.setText("Silakan login terlebih dahulu.");
        }

        // Navigasi ke Catatan Keuangan
        btnCatatanKeuangan.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), catatan_keuangan.class));
        });

        // Navigasi ke Memo Harian
        btnMemoHarian.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), Memo.class));
        });

        return view;
    }
}
