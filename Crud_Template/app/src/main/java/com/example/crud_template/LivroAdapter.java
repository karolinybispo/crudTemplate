package com.example.crud_template;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.LivroViewHolder> {
    private Context context;
    private ArrayList<String> titulos, autores;

    public LivroAdapter(Context context, ArrayList<String> titulos, ArrayList<String> autores) {
        this.context = context;
        this.titulos = titulos;
        this.autores = autores;
    }

    @NonNull
    @Override
    public LivroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_livro, parent, false);
        return new LivroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LivroViewHolder holder, int position) {
        holder.textTitulo.setText(titulos.get(position));
        holder.textAutor.setText(autores.get(position));
    }

    @Override
    public int getItemCount() {
        return titulos.size();
    }

    public class LivroViewHolder extends RecyclerView.ViewHolder {
        TextView textTitulo, textAutor;

        public LivroViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitulo = itemView.findViewById(R.id.textTitulo);
            textAutor = itemView.findViewById(R.id.textAutor);
        }
    }
}
