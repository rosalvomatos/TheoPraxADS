package com.example.project.exemplo.atividades;


import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;
import com.example.project.exemplo.R;
import com.example.project.exemplo.adaptadores.CursosRVAdapter;
import com.example.project.exemplo.mapeamento.CursoPI;
import com.example.project.exemplo.mapeamento.TbCurso;
import com.example.project.exemplo.util.CGuideWS;
import com.example.project.exemplo.util.GenericDialogFragment;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CursosActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener  {

    Toolbar toolbar;

    RecyclerView recyclerView;
    CursosRVAdapter adaptador;
    ProgressDialog pd;
    private ObterCursosTask cursoTSK;
    private android.support.v4.app.FragmentManager mFragmentManager;
    static final String listaCurso = "listaCurso";
    static final String listaCursoOriginal = "listaCursoOriginal";
    List<CursoPI> cursos;
    List<CursoPI> ultimaPesquisa;
    List<CursoPI> cursosEncontrados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_listacursos);

        adaptador = new CursosRVAdapter(cursos,R.layout.layout_curso,this,cursoListener);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Cursos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mFragmentManager = getSupportFragmentManager();
        recyclerView = (RecyclerView) findViewById(R.id.RCV_listacursos);


        if (savedInstanceState != null) {
            cursos = (List<CursoPI>) savedInstanceState.getSerializable(listaCurso);
            ultimaPesquisa = (List<CursoPI>) savedInstanceState.getSerializable(listaCursoOriginal);

            if (cursos != null) {
                adaptador = new CursosRVAdapter(cursos, R.layout.layout_curso, this, cursoListener);
                recyclerView.setAdapter(adaptador);
                recyclerView.setHasFixedSize(true);

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
        } else {
            if (cursos == null) povoarListadeCursos();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(listaCurso, (Serializable) cursos);
        savedInstanceState.putSerializable(listaCursoOriginal, (Serializable) ultimaPesquisa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_curso, menu);
        MenuItem searchItem = menu.findItem(R.id.pesquisar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint(getString(R.string.procurar));
        MenuItemCompat.setOnActionExpandListener(searchItem, this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.atualizar)
            povoarListadeCursos();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        ultimaPesquisa = cursos;
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        limparBusca();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        buscar(newText);
        return false;
    }

    public void buscar(String s) {
        if (s == null || s.trim().equals("")) {
            limparBusca();
            return;
        }
        cursosEncontrados = new ArrayList<>();
        cursosEncontrados.addAll(cursos);
        for (int i = cursosEncontrados.size() - 1; i >= 0; i--) {
            CursoPI cursoTemp = cursosEncontrados.get(i);
            if (!cursoTemp.getCursoNome().toUpperCase().contains(s.toUpperCase())) {
                cursosEncontrados.remove(cursoTemp);
            }
        }
        adaptador = new CursosRVAdapter(cursosEncontrados, R.layout.layout_curso, this,cursoListener);
        recyclerView.setAdapter(adaptador);

    }

    public void limparBusca() {
        cursos = new ArrayList<CursoPI>();
        if (ultimaPesquisa != null) cursos.addAll(ultimaPesquisa);
        if (cursos != null) {
            adaptador = new CursosRVAdapter(cursos, R.layout.layout_curso, this,cursoListener);
            recyclerView.setAdapter(adaptador);
            recyclerView.setHasFixedSize(true);

            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            mFragmentManager = getSupportFragmentManager();
        }

    }

    private void povoarListadeCursos() {
        cursos = new ArrayList<CursoPI>();

        pd = new ProgressDialog(this);
        pd.setMessage(getResources().getText(R.string.msg_dialog1));
        try {
            if (cursoTSK == null || cursoTSK.getStatus() != AsyncTask.Status.RUNNING) {
                cursoTSK = new ObterCursosTask();
                cursoTSK.execute();
            }
        } catch (Exception e) {
            GenericDialogFragment dialog = GenericDialogFragment.novoDialog(CursosActivity.this,
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

    class ObterCursosTask extends AsyncTask<Void, Void, List<CursoPI>>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            exibirProgresso(true);
        }

        @Override
        protected List<CursoPI> doInBackground(Void... params) {
            List<CursoPI> lista = null;
            try {
                lista = CGuideWS.obterCursos();

            } catch (Exception e) {
                exibirProgresso(false);
                GenericDialogFragment dialog = GenericDialogFragment.novoDialog(CursosActivity.this,
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
        protected void onPostExecute(List<CursoPI> lista) {
            super.onPostExecute(lista);
            exibirProgresso(false);
            if (lista == null) {
                GenericDialogFragment dialog = GenericDialogFragment.novoDialog(CursosActivity.this,
                        // Id do dialog
                        0,
                        // titulo
                        R.string.titulo_dialog,
                        // mensagem
                        R.string.msg_dialog1,
                        // texto dos botoes
                        new int[]{
                                android.R.string.ok // String do Android

                        });
                dialog.abrir(mFragmentManager);
            } else {
                cursos.addAll(lista);
                adaptador = new CursosRVAdapter(cursos, R.layout.layout_curso, getApplicationContext(),cursoListener);
                recyclerView.setAdapter(adaptador);
                recyclerView.setHasFixedSize(true);

                //Layout manager for Recycler view
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }

        }
    }

    private CursosRVAdapter.CursoListener cursoListener =
            new CursosRVAdapter.CursoListener() {

                @Override
                public void verDisciplina(CursoPI curso) {
                    Intent intent = new Intent(CursosActivity.this,DisciplinasActivity.class);
                    intent.putExtra("curso",curso.getCursoCodigo());
                    startActivity(intent);
                }

                @Override
                public void verAto(CursoPI curso) {
                    startActivity(CGuideWS.abrirArquivo("ATO_"+curso.getCursoCodigo()+".pdf"));
                }

                @Override
                public void verPPC(CursoPI curso) {
                    startActivity(CGuideWS.abrirArquivo("PPC_"+curso.getCursoCodigo()+".pdf"));

                }

                @Override
                public void verDocente(CursoPI curso) {
                    Intent intent = new Intent(CursosActivity.this,DocentesActivity.class);
                    intent.putExtra("curso",curso.getCursoCodigo());
                    startActivity(intent);
                }

                @Override
                public void verAcervo(CursoPI curso) {

                }

                @Override
                public void verMatriz(CursoPI curso) {
                    startActivity(CGuideWS.abrirArquivo("MATRIZ_"+curso.getCursoCodigo()+".pdf"));
                }
            };


}
