package id.ac.polinema.bukuresep.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "resep.db";
    private  static final int DATABASE_VERSION=1;

    public dataHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE resep (nama TEXT, alat TEXT, bahan TEXT);";
        db.execSQL(sql);
        sql = "INSERT INTO resep(nama, alat, bahan)values('telur ceplok','wajan','telur');";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
