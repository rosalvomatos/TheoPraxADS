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
import com.example.project.exemplo.adaptadores.DisciplinaRVAdapter;
import com.example.project.exemplo.mapeamento.TbDisciplina;
import com.example.project.exemplo.util.CGuideWS;
import com.example.project.exemplo.util.GenericDialogFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 13/10/2016.
 */
public class DisciplinasActivity  extends AppCompatActivity {

    Toolbar toolbar;
    List<TbDisciplina> disciplinas;
    String cursoSelecionado;
    String docenteSelecionado;
    private Button btnAbrirDocentes;
    private Button btnAbrirDisciplinas;

    RecyclerView recyclerView;
    DisciplinaRVAdapter adaptador;
    ProgressDialog pd;
    private ObterDisciplinasTask disciplinaTSK;
    private android.support.v4.app.FragmentManager mFragmentManager;
    static final String listaDisciplina = "listaDisciplina";
    static final String listaDisciplinaO = "listaDisciplinaOriginal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listadisciplinas);
        Intent intent = getIntent();
        cursoSelecionado = intent.getExtras().getString("curso");
        docenteSelecionado = intent.getExtras().getString("docente");


        adaptador = new DisciplinaRVAdapter(disciplinas, R.layout.layout_disciplinas, this, disciplinaListener);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Disciplinas");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mFragmentManager = getSupportFragmentManager();
        recyclerView = (RecyclerView) findViewById(R.id.RCV_listadisciplinas);


        if (savedInstanceState != null) {
            disciplinas = (List<TbDisciplina>) savedInstanceState.getSerializable(listaDisciplina);

            if (disciplinas != null) {
                adaptador = new DisciplinaRVAdapter(disciplinas, R.layout.layout_disciplinas, this, disciplinaListener);
                recyclerView.setAdapter(adaptador);
                recyclerView.setHasFixedSize(true);

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
        } else {
            if (disciplinas == null) povoarListadeDisciplinas();
        }

    }


    private void povoarListadeDisciplinas() {
        disciplinas = new ArrayList<TbDisciplina>();

        pd = new ProgressDialog(this);
        pd.setMessage(getResources().getText(R.string.msg_dialog2));
        try {
            if (disciplinaTSK == null || disciplinaTSK.getStatus() != AsyncTask.Status.RUNNING) {
                disciplinaTSK = new ObterDisciplinasTask();
                disciplinaTSK.execute();
            }
        } catch (Exception e) {
            GenericDialogFragment dialog = GenericDialogFragment.novoDialog(DisciplinasActivity.this,
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



    class ObterDisciplinasTask extends AsyncTask<Void, Void, List<TbDisciplina>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exibirProgresso(true);
        }

        @Override
        protected List<TbDisciplina> doInBackground(Void... params) {
            List<TbDisciplina> lista = null;
            try {
                if(docenteSelecionado == null || docenteSelecionado.isEmpty()){
                    lista = CGuideWS.obterDisciplinasCursos(cursoSelecionado);
                }else{
                    lista = CGuideWS.obterDisciplinasDocente(docenteSelecionado);
                }

            } catch (Exception e) {
                exibirProgresso(false);
                GenericDialogFragment dialog = GenericDialogFragment.novoDialog(DisciplinasActivity.this,
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
        protected void onPostExecute(List<TbDisciplina> lista) {
            super.onPostExecute(lista);
            exibirProgresso(false);
            if (lista == null) {
                GenericDialogFragment dialog = GenericDialogFragment.novoDialog(DisciplinasActivity.this,
                        // Id do dialog
                        0,
                        // titulo
                        R.string.titulo_dialog,
                        // mensagem
                        R.string.msg_dialog2,
                        // texto dos botoes
                        new int[]{
                                android.R.string.ok // String do Android

                        });
                dialog.abrir(mFragmentManager);
            } else {
                disciplinas.addAll(lista);
                adaptador = new DisciplinaRVAdapter(disciplinas, R.layout.layout_disciplinas, getApplicationContext(),disciplinaListener);
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
        savedInstanceState.putSerializable(listaDisciplina, (Serializable) disciplinas);
    }

    private DisciplinaRVAdapter.DisciplinaListener disciplinaListener =
            new DisciplinaRVAdapter.DisciplinaListener(){

                @Override
                public void verProfessores(TbDisciplina disciplina) {
                    Intent intent = new Intent(DisciplinasActivity.this,DocentesActivity.class);
                    intent.putExtra("disc",disciplina.getDisciplina_codigo());
                    startActivity(intent);
                }

                @Override
                public void verPdf(TbDisciplina disciplina) {

                    startActivity(CGuideWS.abrirArquivo(disciplina.getDisciplina_codigo()+".pdf"));
                }
            };

}