package com.example.project.exemplo.atividades;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;

import com.example.project.exemplo.R;
import com.example.project.exemplo.adaptadores.DocenteRVAdapter;
import com.example.project.exemplo.mapeamento.TbDocente;
import com.example.project.exemplo.mapeamento.TbDocente;
import com.example.project.exemplo.util.CGuideWS;
import com.example.project.exemplo.util.GenericDialogFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 13/10/2016.
 */
public class DocentesActivity extends AppCompatActivity {

    Toolbar toolbar;
    List<TbDocente> docentes;
    String cursoSelecionado;
    String disciplinaSelecionada;

    RecyclerView recyclerView;
    DocenteRVAdapter adaptador;
    ProgressDialog pd;
    private DocentesActivity.ObterDocentesTask docenteTSK;
    private android.support.v4.app.FragmentManager mFragmentManager;
    static final String listaDocente = "listaDocente";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listadocentes);
        Intent intent = getIntent();
        cursoSelecionado = intent.getExtras().getString("curso");
        disciplinaSelecionada = intent.getExtras().getString("disc");


        adaptador = new DocenteRVAdapter(docentes, R.layout.layout_docentes, this, docenteListener);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Docentes");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mFragmentManager = getSupportFragmentManager();
        recyclerView = (RecyclerView) findViewById(R.id.RCV_listadocentes);


        if (savedInstanceState != null) {
            docentes = (List<TbDocente>) savedInstanceState.getSerializable(listaDocente);

            if (docentes != null) {
                adaptador = new DocenteRVAdapter(docentes, R.layout.layout_docentes, this, docenteListener);
                recyclerView.setAdapter(adaptador);
                recyclerView.setHasFixedSize(true);

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
        } else {
            if (docentes == null) povoarListadeDocentes();
        }

    }


    private void povoarListadeDocentes() {
        docentes = new ArrayList<TbDocente>();

        pd = new ProgressDialog(this);
        pd.setMessage(getResources().getText(R.string.msg_dialog3));
        try {
            if (docenteTSK == null || docenteTSK.getStatus() != AsyncTask.Status.RUNNING) {
                docenteTSK = new DocentesActivity.ObterDocentesTask();
                docenteTSK.execute();
            }
        } catch (Exception e) {
            GenericDialogFragment dialog = GenericDialogFragment.novoDialog(DocentesActivity.this,
                    0,
                    R.string.titulo_dialog,
                    R.string.msg_dialog,
                    new int[]{
                            android.R.string.ok // String do Android

                    });
            dialog.abrir(mFragmentManager);
        }
    }

    private void exibirProgresso(boolean exibir) {
        try {
            pd.setCancelable(false);
            if (exibir)
                pd.show();
            else
                pd.dismiss();
        } catch (Exception e) {
            Toast.makeText(this, "Erro no Sistema", Toast.LENGTH_LONG).show();
        }
    }



    class ObterDocentesTask extends AsyncTask<Void, Void, List<TbDocente>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exibirProgresso(true);
        }

        @Override
        protected List<TbDocente> doInBackground(Void... params) {
            List<TbDocente> lista = null;
            try {
                if(disciplinaSelecionada == null || disciplinaSelecionada.isEmpty()) {
                    lista = CGuideWS.obterDocentesCursos(cursoSelecionado);
                }else{
                    lista = CGuideWS.obterDocentesDisciplina(disciplinaSelecionada);
                }

            } catch (Exception e) {
                exibirProgresso(false);
                GenericDialogFragment dialog = GenericDialogFragment.novoDialog(DocentesActivity.this,
                        0,
                        R.string.titulo_dialog,
                        R.string.msg_dialog,
                        new int[]{
                                android.R.string.ok // String do Android

                        });
                dialog.abrir(mFragmentManager);
            }
            return lista;
        }

        @Override
        protected void onPostExecute(List<TbDocente> lista) {
            super.onPostExecute(lista);
            exibirProgresso(false);
            if (lista == null) {
                GenericDialogFragment dialog = GenericDialogFragment.novoDialog(DocentesActivity.this,
                        // Id do dialog
                        0,
                        // titulo
                        R.string.titulo_dialog,
                        // mensagem
                        R.string.msg_dialog3,
                        // texto dos botoes
                        new int[]{
                                android.R.string.ok // String do Android

                        });
                dialog.abrir(mFragmentManager);
            } else {
                docentes.addAll(lista);
                adaptador = new DocenteRVAdapter(docentes, R.layout.layout_docentes, getApplicationContext(),docenteListener);
                recyclerView.setAdapter(adaptador);
                recyclerView.setHasFixedSize(true);

                //Layout manager for Recycler view
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }

        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(listaDocente, (Serializable) docentes);
    }

    private DocenteRVAdapter.DocenteListener docenteListener =
            new DocenteRVAdapter.DocenteListener(){
                
                @Override
                public void verDisciplinas(TbDocente docente){
                    Intent intent = new Intent(DocentesActivity.this,DisciplinasActivity.class);
                    intent.putExtra("docente",docente.getDocente_codigo());
                    startActivity(intent);
                }

            };

}