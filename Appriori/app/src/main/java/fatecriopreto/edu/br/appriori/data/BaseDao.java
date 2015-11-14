package fatecriopreto.edu.br.appriori.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Reinaldo on 19/07/2015.
 */
public abstract class BaseDao {

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    public BaseDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    protected SQLiteDatabase getDataBase(){
        if(db == null){
            db = dbHelper.getWritableDatabase();
        }
        return db;
    }

    protected void fechar(){
        dbHelper.close();
        db.close();
        db = null;
    }

}
