package com.example.project.exemplo.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.project.exemplo.Activity.Task.DisciplineTask;
import com.example.project.exemplo.Adapter.DisciplineAdapter;
import com.example.project.exemplo.Adapter.Interface.IDisciplineListener;
import com.example.project.exemplo.Mapper.Json.DisciplineJson;
import com.example.project.exemplo.R;
import com.example.project.exemplo.util.CGuideWS;
import com.example.project.exemplo.util.Enum.TeacherTypeSearch;
import com.example.project.exemplo.util.GenericDialogFragment;
import com.example.project.exemplo.util.ProgressDialogUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DisciplineActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener {

    Toolbar toolbar;
    RecyclerView recyclerView;
    DisciplineAdapter disciplineAdapter;
    private DisciplineTask disciplineTask;
    private FragmentManager fragmentManager;
    static final String disciplineList = "disciplineList";
    static final String disciplineListOrigin = "disciplineListOrigin";
    List<DisciplineJson> disciplineJsonList;
    List<DisciplineJson> lastSearch;
    List<DisciplineJson> disciplinesFound;
    int typeSearch;
    int refferId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_discipline_list);
        Intent intent = getIntent();
        typeSearch = intent.getExtras().getInt("typeSearch");
        refferId = intent.getExtras().getInt("refferId");

        String titlePage = "Disciplinas";

        disciplineAdapter = new DisciplineAdapter(disciplineJsonList, R.layout.layout_discipline, this, iDisciplineListener);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(titlePage);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fragmentManager = getSupportFragmentManager();
        recyclerView = (RecyclerView) findViewById(R.id.RCV_discipline_list );

        if (savedInstanceState != null) {
            disciplineJsonList = (List<DisciplineJson>) savedInstanceState.getSerializable(disciplineList);
            lastSearch = (List<DisciplineJson>) savedInstanceState.getSerializable(disciplineListOrigin);
            if (disciplineJsonList != null) {
                disciplineAdapter = new DisciplineAdapter(disciplineJsonList, R.layout.layout_discipline, this, iDisciplineListener);
                recyclerView.setAdapter(disciplineAdapter);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            }
        } else {
            if (disciplineJsonList == null) populateDisciplineList();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putSerializable(disciplineList, (Serializable) disciplineJsonList);
        savedInstanceState.putSerializable(disciplineListOrigin, (Serializable) lastSearch);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint(getString(R.string.procurar));
        MenuItemCompat.setOnActionExpandListener(searchItem, this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.update)
            populateDisciplineList();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        lastSearch = disciplineJsonList;
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        clearSearch();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        search(newText);
        return false;
    }

    private IDisciplineListener iDisciplineListener = new IDisciplineListener() {

        @Override
        public void showTeacher(DisciplineJson disciplineJson) {
            Intent intent = new Intent(DisciplineActivity.this, TeacherActivity.class);
            int typeSearch = TeacherTypeSearch.valueOf("ByDiscipline").ordinal() + 1;
            intent.putExtra("refferId", disciplineJson.getId());
            intent.putExtra("typeSearch", typeSearch);
            startActivity(intent);
        }

        @Override
        public void showMenu(DisciplineJson disciplineJson) {
            startActivity(CGuideWS.openFile(disciplineJson.getCodigo()+".pdf"));
        }
    };

    private void populateDisciplineList() {
        disciplineJsonList = new ArrayList<>();
        ProgressDialogUtil.instantiateContext(this);
        ProgressDialogUtil.setDialogMessage(R.string.msg_dialog1);
        try {
            if (disciplineTask == null || disciplineTask.getStatus() != AsyncTask.Status.RUNNING) {
                disciplineTask = new DisciplineTask(this, typeSearch, refferId, fragmentManager, disciplineAdapter, disciplineJsonList, iDisciplineListener, recyclerView);
                disciplineTask.execute();
            }
        } catch (Exception e) {
            GenericDialogFragment dialog = GenericDialogFragment.novoDialog(DisciplineActivity.this,
                    0,
                    R.string.titulo_dialog,
                    R.string.msg_dialog,
                    new int[]{
                            android.R.string.ok // String do Android
                    });
            dialog.abrir(fragmentManager);
        }
    }

    public void clearSearch() {
        disciplineJsonList = new ArrayList<>();
        if (lastSearch != null) disciplineJsonList.addAll(lastSearch);
        if (disciplineJsonList != null) {
            disciplineAdapter = new DisciplineAdapter(disciplineJsonList, R.layout.layout_discipline, this, iDisciplineListener);
            recyclerView.setAdapter(disciplineAdapter);
            recyclerView.setHasFixedSize(true);

            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            fragmentManager = getSupportFragmentManager();
        }
    }

    public void search(String s) {
        if (s == null || s.trim().equals("")) {
            clearSearch();
            return;
        }
        disciplinesFound = new ArrayList<>();
        disciplinesFound.addAll(disciplineJsonList);
        for (int i = disciplinesFound.size() - 1; i >= 0; i--) {
            DisciplineJson disciplineTemp = disciplinesFound.get(i);
            if (!disciplineTemp.getNome().toUpperCase().contains(s.toUpperCase())) {
                disciplinesFound.remove(disciplineTemp);
            }
        }
        disciplineAdapter = new DisciplineAdapter(disciplinesFound, R.layout.layout_discipline, this, iDisciplineListener);
        recyclerView.setAdapter(disciplineAdapter);
    }
}
