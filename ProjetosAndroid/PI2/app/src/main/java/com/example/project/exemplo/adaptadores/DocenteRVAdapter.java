package com.example.project.exemplo.adaptadores;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.exemplo.R;
import com.example.project.exemplo.mapeamento.TbCurso;
import com.example.project.exemplo.mapeamento.TbDocente;
import com.example.project.exemplo.mapeamento.TbDocente;

import org.w3c.dom.Text;

import java.util.List;

import static com.example.project.exemplo.R.string.cursos;
import static com.example.project.exemplo.R.string.docentes;

/**
 * Created by Project on 23/07/2015.
 */
public class DocenteRVAdapter extends RecyclerView.Adapter<DocenteRVAdapter.DocenteViewHolder>{

    List<TbDocente> docentes;
    private int rowLayout;
    private Context mContext;
    DocenteRVAdapter.DocenteListener docenteListener;


    public DocenteRVAdapter(List<TbDocente> docentes, int rowLayout, Context context, DocenteRVAdapter.DocenteListener docenteListener){
        this.docentes = docentes;
        this.rowLayout = rowLayout;
        this.mContext = context;
        this.docenteListener = docenteListener;
    }

    @Override
    public int getItemCount() {
        return docentes.size();

    }

    @Override
    public DocenteRVAdapter.DocenteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new DocenteRVAdapter.DocenteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DocenteRVAdapter.DocenteViewHolder docenteViewHolder, final int i) {
        docenteViewHolder.nomedocente.setText(docentes.get(i).getDocente_nome());
        docenteViewHolder.regime.setText(String.valueOf(docentes.get(i).getDocente_regime()));
        docenteViewHolder.codigo.setText(docentes.get(i).getDocente_codigo());

        final TbDocente disc = docentes.get(i);
        docenteViewHolder.disciplinas.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(docenteListener != null){
                    docenteListener.verDisciplinas(disc);
                }
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class DocenteViewHolder extends RecyclerView.ViewHolder {
        //CardView cv;
        Button disciplinas;
        TextView nomedocente;
        TextView codigo;
        TextView regime;

        public DocenteViewHolder(View itemView) {
            super(itemView);
            disciplinas = (Button) itemView.findViewById(R.id.btnAbrirDisciplinas);
            nomedocente = (TextView) itemView.findViewById(R.id.textViewnomedocente);
            codigo = (TextView) itemView.findViewById(R.id.textViewcodigodocente);
            regime = (TextView) itemView.findViewById(R.id.textViewregimedocente);
        }
    }

    public interface DocenteListener {
        public void verDisciplinas(TbDocente docente);
    }
}
