package com.example.project.exemplo.atividades;



import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.project.exemplo.R;
import com.example.project.exemplo.util.CGuideWS;

import java.io.File;
import java.util.List;


public class GraduacaoActivity extends AppCompatActivity {



    private Button btnAbrirCurso;
    private Button btnAbrirAutorizacao;
    private Button btnAbrirManual;
    private Button btnAbrirDirigente;
    private Button btnAbrirSobre;
    private Button btnAbrirRegimento;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_graduacao);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Cimatec Guide");
        setSupportActionBar(toolbar);

        btnAbrirCurso = (Button)findViewById(R.id.btnAbrirCurso);
        btnAbrirCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GraduacaoActivity.this,CursosActivity.class);
                startActivity(i);
            }
        });

        btnAbrirSobre = (Button)findViewById(R.id.btnAbrirSobre);
        btnAbrirSobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GraduacaoActivity.this,SobreActivity.class);
                startActivity(i);
            }
        });

        btnAbrirDirigente = (Button)findViewById(R.id.btnAbrirDirigente);
        btnAbrirDirigente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GraduacaoActivity.this,DirigentesActivity.class);
                startActivity(i);
            }
        });

        btnAbrirAutorizacao = (Button)findViewById(R.id.btnAbrirAutorizacao);
        btnAbrirAutorizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CGuideWS.abrirArquivo("AUTORIZACAO.pdf"));
            }
        });
        btnAbrirManual = (Button)findViewById(R.id.btnAbrirManual);
        btnAbrirManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CGuideWS.abrirArquivo("MANUAL.pdf"));
            }
        });
        btnAbrirRegimento = (Button)findViewById(R.id.btnAbrirRegimento);
        btnAbrirRegimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CGuideWS.abrirArquivo("REGIMENTO.pdf"));
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_graduacao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sair)
            sair();
        return super.onOptionsItemSelected(item);
    }




    private void sair(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cimatec_Guide");
        builder.setMessage("Deseja realmente sair?");
        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        });
        builder.setNegativeButton("NAO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        AlertDialog alerta = builder.create();
        alerta.show();
    }


    @Override
    public void onBackPressed() {
        sair();
    }
}
