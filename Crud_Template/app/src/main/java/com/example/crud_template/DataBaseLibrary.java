package com.example.crud_template;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseLibrary extends SQLiteOpenHelper {

    // Nome e versão do banco de dados
    private static final String DATABASE_NAME = "biblioteca.db";
    private static final int DATABASE_VERSION = 1;

    // Nome da tabela que armazenará os livros
    private static final String TABLE_LIVROS = "livros";

    // Construtor da classe DataBaseLibrary
    public DataBaseLibrary(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Método chamado quando o banco de dados é criado pela primeira vez
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL para criar a tabela de livros
        String CREATE_TABLE = "CREATE TABLE " + TABLE_LIVROS + "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT," // Coluna de ID (chave primária)
                + "titulo TEXT," // Coluna para o título do livro
                + "autor TEXT" // Coluna para o autor do livro
                + ")";
        db.execSQL(CREATE_TABLE); // Executa o comando SQL para criar a tabela
    }

    // Método chamado quando o banco de dados é atualizado (mudança de versão)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Apaga a tabela antiga e cria uma nova
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIVROS);
        onCreate(db);
    }

    // Método para adicionar um novo livro ao banco de dados
    public boolean addLivro(String titulo, String autor) {
        SQLiteDatabase db = this.getWritableDatabase(); // Obtém o banco de dados em modo de escrita
        ContentValues contentValues = new ContentValues(); // Objeto para armazenar os valores a serem inseridos
        contentValues.put("titulo", titulo); // Insere o título do livro
        contentValues.put("autor", autor); // Insere o nome do autor
        long result = db.insert(TABLE_LIVROS, null, contentValues); // Insere os dados na tabela
        db.close(); // Fecha o banco de dados
        return result != -1; // Se o resultado for -1, houve erro ao inserir
    }

    // Método para buscar todos os livros cadastrados
    public Cursor getAllLivros() {
        SQLiteDatabase db = this.getReadableDatabase(); // Obtém o banco de dados em modo de leitura
        return db.rawQuery("SELECT * FROM " + TABLE_LIVROS, null); // Executa a query para buscar todos os livros
    }
}

