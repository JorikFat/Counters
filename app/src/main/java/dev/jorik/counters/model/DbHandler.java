package dev.jorik.counters.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import dev.jorik.counters.entities.SimpleCounter;

import static dev.jorik.counters.model.DbHandler.Const.ARG;
import static dev.jorik.counters.model.DbHandler.Const.COUNT;
import static dev.jorik.counters.model.DbHandler.Const.DB_NAME;
import static dev.jorik.counters.model.DbHandler.Const.DB_VERSION;
import static dev.jorik.counters.model.DbHandler.Const.ID;
import static dev.jorik.counters.model.DbHandler.Const.NAME;
import static dev.jorik.counters.model.DbHandler.Const.TABLE_NAME;

public class DbHandler extends SQLiteOpenHelper {

    public static class Const {
        public static final String DB_NAME = "counters";
        public static final int DB_VERSION = 1;
        public static final String ARG = "=?";

        public static final String TABLE_NAME = "counters";
        //поля
        public static final String ID = "_id";
        public static final String COUNT = "count";
        public static final String NAME = "name";
    }

    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY, "
                + COUNT + " INTEGER,"
                + NAME + " TEXT"
                + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //null
    }

    public long createItem(ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return id;
    }

    public long createCounter(SimpleCounter counter){
        return createItem(getValuesFromCounter(counter));
    }

    public Cursor readItem(long id) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(TABLE_NAME,
                null,
                ID + ARG,
                new String[]{String.valueOf(id)},
                null, null, null, null
        );
    }

    public SimpleCounter readCounter(long id){
        Cursor cursor = readItem(id);
        cursor.moveToFirst();
        return getCounterFromCursor(cursor);
    }

    public int updateItem(long id, ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();
        return db.update(TABLE_NAME, contentValues, ID + ARG, new String[]{String.valueOf(id)});
    }

    public int updateCounter(long id, SimpleCounter counter){
        return updateItem(id, getValuesFromCounter(counter));
    }

    public void deleteItem(long id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, ID + ARG, new String[]{String.valueOf(id)});
        db.close();
    }

    public Cursor readAllItems() {
        SQLiteDatabase db = getWritableDatabase();
        return db.query(TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public List<SimpleCounter> readAllCounters(){
        return getAllItemsFromCursor(readAllItems());
    }

    private SimpleCounter getCounterFromCursor(Cursor cursor) {
        long id = cursor.getInt(cursor.getColumnIndex(ID));
        String name = cursor.getString(cursor.getColumnIndex(NAME));
        int count = cursor.getInt(cursor.getColumnIndex(COUNT));
        SimpleCounter counter = new SimpleCounter(name, count);
        counter.setId(id);
        return counter;
    }

    private ContentValues getValuesFromCounter(SimpleCounter counter) {
        ContentValues rValues = new ContentValues();
        rValues.put(NAME, counter.getName());
        rValues.put(COUNT, counter.getCount());
        return rValues;
    }

    public List<SimpleCounter> getAllItemsFromCursor(Cursor cursor){
        List<SimpleCounter> rList = new ArrayList<>();
        if (cursor.moveToFirst()){
            do {
                rList.add(getCounterFromCursor(cursor));
            } while (cursor.moveToNext());
        }
        return rList;
    }

    public long getLastId(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{ID},
                null,
                null,
                null,
                null,
                ID + " DESC",
                "1");
        return cursor.moveToFirst() ? cursor.getLong(cursor.getColumnIndex(ID)) : -1;
    }
}