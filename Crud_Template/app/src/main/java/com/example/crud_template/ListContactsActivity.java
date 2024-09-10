package com.example.crud_template;


import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ListView;
import java.util.ArrayList;

public class ListContactsActivity extends AppCompatActivity {

    private ListView listViewContatos;
    private DataBaseLibrary atributoDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contatos);

        listViewContatos = findViewById(R.id.listViewContatos);
        atributoDataBase = new DataBaseLibrary(this);

        loadContatos();
    }

    private void loadContatos() {
        ArrayList<String> contatos = new ArrayList<>();
        Cursor cursor = atributoDataBase.getAllContatos();

        if (cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
                contatos.add(nome + " - " + telefone);
            } while (cursor.moveToNext());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contatos);
        listViewContatos.setAdapter(adapter);
    }
}

