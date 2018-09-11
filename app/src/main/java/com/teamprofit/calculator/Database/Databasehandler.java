package com.teamprofit.calculator.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import com.teamprofit.calculator.Models.logdata;

public class Databasehandler extends SQLiteOpenHelper {

    Context context;


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "data";
    private static final String TABLE_MAIN = "MAIN";
    private static final String LOG_BETS = "LOG_BETS";
    private static final String KEY_ID = "id";
    private static final String bd_event = "bd_event";
    private static final String bd_out = "bd_out";
    private static final String bd_book = "bd_book";
    private static final String bd_exchange = "bd_exchange";
    private static final String profit = "profit";
    private static final String timestamp = "timestamp";
    private static final String type = "type";


    public Databasehandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table = "CREATE TABLE " + TABLE_MAIN + " (" + KEY_ID + " INTEGER PRIMARY KEY, " + bd_event + " TEXT, " + bd_out + " TEXT, " + bd_book + " TEXT, " + bd_exchange + " TEXT," + profit + " REAL," + timestamp + " TEXT," + type + " TEXT" + ")";


        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAIN);
        onCreate(db);

    }

    public void adddata(logdata logdata) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(bd_event, logdata.getEvent());
        values.put(bd_out, logdata.getOut());
        values.put(bd_exchange, logdata.getExchange());
        values.put(bd_book, logdata.getBook());
        values.put(profit, logdata.getProfit());
        values.put(timestamp, logdata.getTimestamp());
        values.put(type, logdata.getType());
        db.insert(TABLE_MAIN, null, values);
        db.close();
    }

    public ArrayList<logdata> getdata() {

        String selectQuery = "SELECT  * FROM " + TABLE_MAIN + " ORDER BY " + KEY_ID + " DESC";
        ArrayList<logdata> logdataArrayList = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                logdata logdata = new logdata();
                logdata.setId(Integer.parseInt(cursor.getString(0)));
                logdata.setEvent(cursor.getString(1));
                logdata.setOut(cursor.getString(2));
                logdata.setBook(cursor.getString(3));
                logdata.setExchange(cursor.getString(4));
                logdata.setProfit(cursor.getString(5));
                logdata.setTimestamp(cursor.getString(6));
                logdata.setType(cursor.getString(7));
                logdataArrayList.add(logdata);
            } while (cursor.moveToNext());
        }
        return logdataArrayList;
    }

    public void cleardata() {

        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from " + TABLE_MAIN);
        db.close();
    }

    public void deleteContact(logdata logdata) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MAIN, KEY_ID + " = ?", new String[]{String.valueOf(logdata.getId())});
        db.close();
    }
}
