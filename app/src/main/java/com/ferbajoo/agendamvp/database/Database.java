package com.ferbajoo.agendamvp.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by
 * feuribe on 09/02/2018.
 */

public class Database {

    private static final String TAG = Database.class.getSimpleName();
    private static final String DATABASE_NAME = "agendafer.db";
    private static final int DATABASE_VERSION = 1;
    private final Context mContext;
    public static ContactDao contactDao;
    private DatabaseHelper databaseHelper;

    public Database(Context mContext) {
        this.mContext = mContext;
    }

    public Database open() throws SQLException {
        databaseHelper = new DatabaseHelper(mContext);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        contactDao = new ContactDao(database);
        return this;
    }

    public void close(){
        databaseHelper.close();
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(IContactSchema.CONTACT_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(IContactSchema.CONTACT_TABLE_DROP);
            onCreate(sqLiteDatabase);
        }
    }

}
