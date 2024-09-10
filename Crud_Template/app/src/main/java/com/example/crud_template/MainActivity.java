package com.example.crud_template;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNome, editTextTelefone; // Campos de texto para nome e telefone
    private DataBaseLibrary atributoDataBase; // Objeto para gerenciar o banco de dados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Define o layout da Activity

        // Inicializa os componentes da interface
        editTextNome = findViewById(R.id.editTextNomeContato);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        Button buttonSalvar = findViewById(R.id.buttonSalvar);
        // Botões de salvar e ver contatos
        Button buttonVerContatos = findViewById(R.id.buttonVerContatos);
        atributoDataBase = new DataBaseLibrary(this); // Inicializa o DatabaseHelper

        // Define o comportamento do botão de salvar
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = editTextNome.getText().toString(); // Obtém o texto do campo Nome
                String telefone = editTextTelefone.getText().toString(); // Obtém o texto do campo Telefone

                // Verifica se os campos estão preenchidos
                if (nome.isEmpty() || telefone.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Tenta adicionar o contato ao banco de dados
                    boolean isInserted = atributoDataBase.addContato(nome, telefone);
                    if (isInserted) {
                        Toast.makeText(MainActivity.this, "Contato salvo com sucesso", Toast.LENGTH_SHORT).show();
                        editTextNome.setText(""); // Limpa o campo Nome
                        editTextTelefone.setText(""); // Limpa o campo Telefone
                    } else {
                        Toast.makeText(MainActivity.this, "Erro ao salvar contato", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Define o comportamento do botão de ver contatos
        buttonVerContatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia a Activity que exibe a lista de contatos
                Intent intent = new Intent(MainActivity.this, ListContactsActivity.class);
                startActivity(intent);
            }
        });
    }
}


