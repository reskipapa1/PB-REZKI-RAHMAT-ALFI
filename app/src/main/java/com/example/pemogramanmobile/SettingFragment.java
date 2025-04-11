package com.example.pemogramanmobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class SettingFragment extends Fragment {

    public SettingFragment() {
        // Diperlukan konstruktor kosong
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Gunakan layout lama activity_setting.xml
        return inflater.inflate(R.layout.activity_setting, container, false);
    }
}
