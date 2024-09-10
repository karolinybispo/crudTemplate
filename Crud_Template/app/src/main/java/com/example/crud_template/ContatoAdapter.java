package com.example.crud_template;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.ContatoViewHolder> {

    private List<Contato> contatoList;

    public ContatoAdapter(List<Contato> contatoList) {
        this.contatoList = contatoList;
    }

    @Override
    public ContatoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contato, parent, false);
        return new ContatoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContatoViewHolder holder, int position) {
        Contato contato = contatoList.get(position);
        holder.nome.setText(contato.getNome());
        holder.telefone.setText(contato.getTelefone());
    }

    @Override
    public int getItemCount() {
        return contatoList.size();
    }

    public class ContatoViewHolder extends RecyclerView.ViewHolder {
        public TextView nome, telefone;

        public ContatoViewHolder(View view) {
            super(view);
            nome = view.findViewById(R.id.textViewNome);
            telefone = view.findViewById(R.id.textViewTelefone);
        }
    }
}
