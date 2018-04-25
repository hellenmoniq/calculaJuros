package com.example.ucl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UCL on 24/04/2018.
 */
public class Helper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    private static final String TABELA = "calcula";

    private static final String ID = "id";
    private static final String NOME = "txtnome";
    private static final String VALORINI = "txtvalorinicial";
    private static final String APLICACAOMEN = "txtaplicaomensal";
    private static final String TEMPOAPLI = "txttempoaplicacao";
    private static final String TAXA = "txttaxa";
    private static final String VALORFINAL = "txtvalorfinal";
    private static final int VERSAO = 3;

    public Helper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =
                "CREATE TABLE " + TABELA + "("
                        + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + NOME + " TEXT, "
                        + VALORINI + " TEXT, "
                        + APLICACAOMEN + " TEXT, "
                        + TEMPOAPLI + " TEXT, "
                        + TAXA + " TEXT, "
                        + VALORFINAL + " TEXT"
                        + ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);
    }

    public long insertItem(ItemCalcula item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOME, item.getName());
        values.put(VALORINI, item.getValorinit());
        values.put(APLICACAOMEN, item.getAlicacaomensal());
        values.put(TEMPOAPLI, item.getTempoaplicacao());
        values.put(TAXA, item.getTaxa());
        values.put(VALORFINAL, item.getValorfinal());

        // insert row
        long id = db.insert(TABELA, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public List<ItemCalcula> getAllItens() {
        List<ItemCalcula> itens = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABELA + " ORDER BY " + ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ItemCalcula item = new ItemCalcula();
                item.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                item.setName(cursor.getString(cursor.getColumnIndex(NOME)));
                item.setValorfinal(cursor.getString(cursor.getColumnIndex(VALORFINAL)));

                itens.add(item);
            } while (cursor.moveToNext());
        }
        db.close();
        return itens;
    }

}