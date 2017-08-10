package com.example.project.exemplo.atividades;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.project.exemplo.R;

/**
 * Created by Casa on 17/11/2016.
 */

public class SobreActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sobre);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sobre");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
