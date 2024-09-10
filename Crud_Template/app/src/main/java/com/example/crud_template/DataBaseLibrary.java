package com.example.crud_template;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseLibrary extends SQLiteOpenHelper {


    // Classe responsável por gerenciar o banco de dados SQLite


    // Nome e versão do banco de dados
    private static final String DATABASE_NAME = "contatos.db";
    private static final int DATABASE_VERSION = 1;

    // Nome da tabela que armazenará os contatos
    private static final String TABLE_CONTATOS = "contatos";

    // Construtor da classe DataBaseLibrary
    public DataBaseLibrary(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Método chamado quando o banco de dados é criado pela primeira vez
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL para criar a tabela de contatos
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CONTATOS + "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT," // Coluna de ID (chave primária)
                + "nome TEXT," // Coluna para o nome do contato
                + "telefone TEXT" // Coluna para o telefone do contato
                + ")";
        db.execSQL(CREATE_TABLE); // Executa o comando SQL para criar a tabela
    }

    // Método chamado quando o banco de dados é atualizado (mudança de versão)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Apaga a tabela antiga e cria uma nova
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTATOS);
        onCreate(db);
    }

    // Método para adicionar um novo contato ao banco de dados
    public boolean addContato(String nome, String telefone) {
        SQLiteDatabase db = this.getWritableDatabase(); // Obtém o banco de dados em modo de escrita
        ContentValues values = new ContentValues(); // Objeto para armazenar os valores das colunas
        values.put("nome", nome); // Adiciona o nome ao ContentValues
        values.put("telefone", telefone); // Adiciona o telefone ao ContentValues

        // Insere o novo contato na tabela de contatos
        long result = db.insert(TABLE_CONTATOS, null, values);
        db.close(); // Fecha a conexão com o banco de dados
        return result != -1; // Retorna true se a inserção foi bem-sucedida, caso contrário false
    }

    // Método para obter todos os contatos armazenados no banco de dados
    public Cursor getAllContatos() {
        SQLiteDatabase db = this.getReadableDatabase(); // Obtém o banco de dados em modo de leitura
        return db.rawQuery("SELECT * FROM " + TABLE_CONTATOS, null); // Executa a query para obter todos os contatos

    }
}

