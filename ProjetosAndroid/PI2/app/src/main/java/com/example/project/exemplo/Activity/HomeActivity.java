package com.example.project.exemplo.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.project.exemplo.R;
import com.example.project.exemplo.util.TypeCourseEnum;

public class HomeActivity extends AppCompatActivity {

    private Button btnShowGraduationCourse;
    private Button btnShowPostgraduateCourse;
    private Button btnShowGeneralAuthorizationAct;
    private Button btnShowStudentManual;
    private Button btnShowDisciplinaryRules;
    private Button btnShowLeader;
    private Button btnShowAbout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_home);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Cimatec Guide");
        setSupportActionBar(toolbar);

        btnShowGraduationCourse = (Button) findViewById(R.id.btnShowGraduationCourse);
        btnShowPostgraduateCourse = (Button) findViewById(R.id.btnShowPostgraduateCourse);
        btnShowGeneralAuthorizationAct = (Button) findViewById(R.id.btnShowGeneralAuthorizationAct);
        btnShowStudentManual = (Button) findViewById(R.id.btnShowStudentManual);
        btnShowDisciplinaryRules = (Button) findViewById(R.id.btnShowDisciplinaryRules);
        btnShowLeader = (Button) findViewById(R.id.btnShowLeader);
        btnShowAbout = (Button) findViewById(R.id.btnShowAbout);

        btnShowGraduationCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, CourseActivity.class);
                int typeCourse = TypeCourseEnum.valueOf("Graduation").ordinal() + 1;
                i.putExtra("typeCourse", typeCourse);
                startActivity(i);
            }
        });

        btnShowPostgraduateCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, CourseActivity.class);
                int typeCourse = TypeCourseEnum.valueOf("Postgraduate").ordinal() + 1;
                i.putExtra("typeCourse", typeCourse);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.quit)
            quit();
        return super.onOptionsItemSelected(item);
    }

    private void quit() {
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
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onBackPressed() {
        quit();
    }

}
