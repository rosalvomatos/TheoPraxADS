package com.example.project.exemplo.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.exemplo.R;
import com.example.project.exemplo.mapeamento.CursoPI;

import java.util.List;

/**
 * Created by Project on 23/07/2015.
 */
public class CursosRVAdapter extends RecyclerView.Adapter<CursosRVAdapter.CursoViewHolder> {

    List<CursoPI> cursos;
    private int rowLayout;
    private Context mContext;
    CursoListener cursoListener;


    public CursosRVAdapter(List<CursoPI> cursos, int rowLayout, Context context, CursoListener cursoListener) {
        this.cursos = cursos;
        this.rowLayout = rowLayout;
        this.mContext = context;
        this.cursoListener = cursoListener;
    }


    @Override
    public int getItemCount() {
        return cursos.size();

    }

    @Override
    public CursoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new CursoViewHolder(v);
    }


    @Override
    public void onBindViewHolder(CursoViewHolder cursoViewHolder, final int i) {
        cursoViewHolder.nomecurso.setText(cursos.get(i).getCursoNome());
        cursoViewHolder.codigo.setText(cursos.get(i).getCursoCodigo());
        cursoViewHolder.turno.setText(cursos.get(i).getCursoTurno());
        cursoViewHolder.modalidade.setText(cursos.get(i).getCursoModalidade());
        cursoViewHolder.avaliacaomec.setText(String.valueOf(cursos.get(i).getCursoAvaliacaoMec()));
        cursoViewHolder.coordenador.setText(cursos.get(i).getCursoCoordenador());
        cursoViewHolder.duracao.setText(cursos.get(i).getCursoDuracao());

        final CursoPI curso = cursos.get(i);
        cursoViewHolder.ato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cursoListener != null) {
                    cursoListener.verAto(curso);

                }

            }
        });
        cursoViewHolder.ppc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cursoListener != null) {
                    cursoListener.verPPC(curso);

                }

            }
        });
        cursoViewHolder.acervo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cursoListener != null) {
                    cursoListener.verAcervo(curso);

                }

            }
        });
        cursoViewHolder.docentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cursoListener != null) {
                    cursoListener.verDocente(curso);

                }

            }
        });
        cursoViewHolder.matrizcurricular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cursoListener != null) {
                    cursoListener.verMatriz(curso);

                }

            }
        });
        cursoViewHolder.disciplinas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cursoListener != null) {
                    cursoListener.verDisciplina(curso);

                }

            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class CursoViewHolder extends RecyclerView.ViewHolder {
        //CardView cv;

        TextView nomecurso;
        TextView codigo;
        TextView turno;
        TextView modalidade;
        TextView avaliacaomec;
        TextView duracao;
        TextView coordenador;
        Button ato;
        Button ppc;
        Button acervo;
        Button docentes;
        Button matrizcurricular;
        Button disciplinas;

        public CursoViewHolder(View itemView) {
            super(itemView);

//            codigo = (TextView) itemView.findViewById(R.id.textViewcodigo);
//            nomecurso = (TextView) itemView.findViewById(R.id.textViewnomecurso);
//            turno = (TextView) itemView.findViewById(R.id.textViewturno);
//            modalidade = (TextView) itemView.findViewById(R.id.textViewmodalidade);
//            avaliacaomec = (TextView) itemView.findViewById(R.id.textViewavaliacaomec);
//            duracao = (TextView) itemView.findViewById(R.id.textViewduracao);
//            coordenador = (TextView) itemView.findViewById(R.id.textViewcoordenador);
//            ato = (Button) itemView.findViewById(R.id.btnAbrirAto);
//            ppc = (Button) itemView.findViewById(R.id.btnAbrirPPC);
//            acervo = (Button) itemView.findViewById(R.id.btnAbrirAcervo);
//            matrizcurricular = (Button) itemView.findViewById(R.id.btnAbrirMatriz);
            disciplinas = (Button) itemView.findViewById(R.id.btnAbrirDisciplinas);
            docentes = (Button) itemView.findViewById(R.id.btnAbrirDocentes);

        }
    }

    public interface CursoListener {
        public void verDisciplina(CursoPI curso);

        public void verAto(CursoPI curso);

        public void verPPC(CursoPI curso);

        public void verDocente(CursoPI curso);

        public void verAcervo(CursoPI curso);

        public void verMatriz(CursoPI curso);
    }
}

