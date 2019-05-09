package dev.jorik.counters.activities.main.create;

import dev.jorik.counters.entities.SimpleCounter;
import dev.jorik.counters.model.DbHandler;

public class CreateModel {
    private DbHandler database;

    public CreateModel(DbHandler database){
        this.database = database;
    }

    public void addCounter(SimpleCounter counter) {
        database.createCounter(counter);
    }
}
