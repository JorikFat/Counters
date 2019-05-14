package dev.jorik.counters.activities.main;

import android.content.ContentValues;

import java.util.List;

import dev.jorik.counters.entities.SimpleCounter;
import dev.jorik.counters.model.DbHandler;

public class MainModel {
    private DbHandler database;
    private List<SimpleCounter> data;

    public MainModel(DbHandler database) {
        this.database = database;
        data = database.readAllCounters();
    }

    public List<SimpleCounter> getData() {
        data.clear();
        data.addAll(database.readAllCounters());
        return data;
    }

    public void addLastCounter(){
        long lastId = database.getLastId();
        data.add(database.readCounter(lastId));
    }

    public long createItem(SimpleCounter counter){
        long id = database.createCounter(counter);
        data.add(database.readCounter(id));
        return id;
    }

    public void updateItem(int position){
        SimpleCounter counter = data.get(position);
        database.updateCounter(counter.getId(), counter);
    }

    public void deleteItem(long id){
        database.deleteItem(id);
    }

    public static ContentValues getValues(SimpleCounter counter){
        ContentValues rValues = new ContentValues();
        rValues.put(DbHandler.Const.NAME, counter.getName());
        rValues.put(DbHandler.Const.COUNT, counter.getCount());
        return rValues;
    }
}
