package com.example.pemogramanmobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Memo extends AppCompatActivity {

    private EditText etMemo;
    private Button btnSimpanMemo;
    private TextView tvHasilMemo;
    private StringBuilder semuaMemo = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        etMemo = findViewById(R.id.etMemo);
        btnSimpanMemo = findViewById(R.id.btnSimpanMemo);
        tvHasilMemo = findViewById(R.id.tvHasilMemo);

        btnSimpanMemo.setOnClickListener(v -> {
            String memo = etMemo.getText().toString().trim();

            if (memo.isEmpty()) {
                Toast.makeText(this, "Memo tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            semuaMemo.append("• ").append(memo).append("\n");
            tvHasilMemo.setText(semuaMemo.toString());
            etMemo.setText("");
        });
    }
}
