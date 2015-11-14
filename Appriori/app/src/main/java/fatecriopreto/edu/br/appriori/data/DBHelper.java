package fatecriopreto.edu.br.appriori.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thaysa on 11/10/15.
 */

//criar banco
public class DBHelper extends SQLiteOpenHelper {

    //TODO qualquer coisa

    public static final String NOME_BANCO = "bd_appriori";
    public static final Integer VERSAO = 1;

    public static final String TABELA_LOGIN = "login";

    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String EMAIL = "email";
    public static final String SENHA = "senha";


//criar tabela
    public static final String CRIAR_TABELA =
            "create table " + TABELA_LOGIN + " ( " +
                    ID + " integer primary key autoincrement, " +
                    NOME + " text not null, " +
                    EMAIL + " text not null, " +
                    SENHA + " text not null );";


//criar evento para limpeza de tabela !!!???
    public static final String APAGAR_TABELA =
            "DROP TABLE IF EXISTS " + TABELA_LOGIN;

    public DBHelper(Context context){

        super(context, NOME_BANCO,null,VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CRIAR_TABELA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {

        db.execSQL(APAGAR_TABELA);
        onCreate(db);

    }
}
