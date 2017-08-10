package com.example.project.exemplo.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.exemplo.R;
import com.example.project.exemplo.mapeamento.TbCurso;
import com.example.project.exemplo.mapeamento.TbDisciplina;

import java.util.List;

/**
 * Created by Project on 23/07/2015.
 */
public class DisciplinaRVAdapter extends RecyclerView.Adapter<DisciplinaRVAdapter.DisciplinaViewHolder>{

    List<TbDisciplina> disciplinas;
    private int rowLayout;
    private Context mContext;
    DisciplinaListener disciplinaListener;


    public DisciplinaRVAdapter(List<TbDisciplina> disciplinas, int rowLayout, Context context, DisciplinaListener disciplinaListener){
        this.disciplinas = disciplinas;
        this.rowLayout = rowLayout;
        this.mContext = context;
        this.disciplinaListener = disciplinaListener;
    }

    @Override
    public int getItemCount() {
        return disciplinas.size();

    }

    @Override
    public DisciplinaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new DisciplinaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DisciplinaViewHolder disciplinaViewHolder, final int i) {
        disciplinaViewHolder.nomedisciplina.setText(disciplinas.get(i).getDisciplina_nome());
        disciplinaViewHolder.cargahoraria.setText(String.valueOf(disciplinas.get(i).getDisciplina_cargahoraria()));
        disciplinaViewHolder.codigo.setText(disciplinas.get(i).getDisciplina_codigo());

        final TbDisciplina disc = disciplinas.get(i);
        disciplinaViewHolder.docentes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(disciplinaListener != null){
                    disciplinaListener.verProfessores(disc);
                }
            }
        });
        disciplinaViewHolder.pdf.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(disciplinaListener != null){
                    disciplinaListener.verPdf(disc);
                }
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class DisciplinaViewHolder extends RecyclerView.ViewHolder {
        //CardView cv;
        Button docentes;
        Button pdf;
        TextView nomedisciplina;
        TextView codigo;
        TextView cargahoraria;

        public DisciplinaViewHolder(View itemView) {
            super(itemView);
            docentes = (Button) itemView.findViewById(R.id.btnAbrirDocentes);
            pdf = (Button) itemView.findViewById(R.id.btnAbrirEmenta);
            nomedisciplina = (TextView) itemView.findViewById(R.id.textViewnomedisciplina);
            codigo = (TextView) itemView.findViewById(R.id.textViewcodigodisciplina);
            cargahoraria = (TextView) itemView.findViewById(R.id.textViewcargahorariadisciplina);
        }
    }

    public interface DisciplinaListener {
        public void verProfessores(TbDisciplina disciplina);
        public void verPdf(TbDisciplina disciplina);
    }
}
