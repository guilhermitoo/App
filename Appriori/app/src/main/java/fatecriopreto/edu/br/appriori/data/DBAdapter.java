package fatecriopreto.edu.br.appriori.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fatecriopreto.edu.br.appriori.bean.Login;

/**
 * Created by thaysa on 11/10/15.
 */
public class DBAdapter {

    SQLiteDatabase database;
    DBHelper dbHelper;

    String[] colunas = {DBHelper.ID, DBHelper.NOME, DBHelper.EMAIL, DBHelper.SENHA};

    //instancia um novo DBHelper
    public DBAdapter(Context context) { dbHelper = new DBHelper(context); }

    //abre conexao
    public void open() { database = dbHelper.getWritableDatabase();}

    //fecha conexao
    public void close() { dbHelper.close();}


    //adicionar um novo login
    public void adicionar(Login login){
        ContentValues values = new ContentValues();


        values.put(DBHelper.NOME, login.getNome());
        values.put(DBHelper.EMAIL, login.getEmail());
        values.put(DBHelper.SENHA, login.getSenha());

        database.insert(DBHelper.TABELA_LOGIN, null, values);
    }




}
