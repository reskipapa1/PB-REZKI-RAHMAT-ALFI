package com.example.pemogramanmobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class catatan_keuangan extends AppCompatActivity {

    private EditText etJudul, etJumlah;
    private Button btnSimpan;
    private TextView tvHasil;
    private StringBuilder hasilData = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan_keuangan);

        etJudul = findViewById(R.id.etJudul);
        etJumlah = findViewById(R.id.etJumlah);
        btnSimpan = findViewById(R.id.btnSimpan);
        tvHasil = findViewById(R.id.tvHasil);

        btnSimpan.setOnClickListener(v -> {
            String judul = etJudul.getText().toString().trim();
            String jumlah = etJumlah.getText().toString().trim();

            if (judul.isEmpty() || jumlah.isEmpty()) {
                Toast.makeText(this, "Lengkapi semua data", Toast.LENGTH_SHORT).show();
                return;
            }

            hasilData.append("• ").append(judul).append(" - Rp").append(jumlah).append("\n");
            tvHasil.setText(hasilData.toString());

            etJudul.setText("");
            etJumlah.setText("");
        });
    }
}
