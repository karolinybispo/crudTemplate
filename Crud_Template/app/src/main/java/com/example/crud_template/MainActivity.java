package com.example.crud_template;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextTitulo, editTextAutor;
    Button buttonSalvar;
    RecyclerView recyclerViewLivros;
    DataBaseLibrary dataBaseLibrary;
    ArrayList<String> titulos, autores;
    LivroAdapter livroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextAutor = findViewById(R.id.editTextAutor);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        recyclerViewLivros = findViewById(R.id.recyclerViewLivros);

        dataBaseLibrary = new DataBaseLibrary(this);

        // Configura o RecyclerView
        titulos = new ArrayList<>();
        autores = new ArrayList<>();
        livroAdapter = new LivroAdapter(this, titulos, autores);
        recyclerViewLivros.setAdapter(livroAdapter);
        recyclerViewLivros.setLayoutManager(new LinearLayoutManager(this));

        // Botão para salvar o livro
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = editTextTitulo.getText().toString();
                String autor = editTextAutor.getText().toString();

                if (titulo.isEmpty() || autor.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isInserted = dataBaseLibrary.addLivro(titulo, autor);
                    if (isInserted) {
                        Toast.makeText(MainActivity.this, "Livro cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                        editTextTitulo.setText("");
                        editTextAutor.setText("");
                        carregarLivros(); // Atualiza a lista
                    } else {
                        Toast.makeText(MainActivity.this, "Erro ao cadastrar livro", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Carrega os livros cadastrados
        carregarLivros();
    }

    private void carregarLivros() {
        Cursor cursor = dataBaseLibrary.getAllLivros();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Nenhum livro encontrado", Toast.LENGTH_SHORT).show();
        } else {
            titulos.clear();
            autores.clear();
            while (cursor.moveToNext()) {
                titulos.add(cursor.getString(1)); // Título
                autores.add(cursor.getString(2)); // Autor
            }
            livroAdapter.notifyDataSetChanged(); // Notifica que os dados mudaram
        }
    }
}


