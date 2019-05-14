package dev.jorik.counters.activities.simpleCounter;

import dev.jorik.counters.entities.SimpleCounter;
import dev.jorik.counters.model.DbHandler;

public class CounterModel {
    private DbHandler database;
    private SimpleCounter counter;

    public CounterModel(DbHandler database, long id) {
        this.database = database;
        this.counter = database.readCounter(id);
    }

    public SimpleCounter getCounter() {
        return counter;
    }

    public void update(){
        database.updateCounter(counter.getId(), counter);
    }
}
