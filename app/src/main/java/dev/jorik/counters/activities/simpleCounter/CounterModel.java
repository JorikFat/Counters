package dev.jorik.counters.activities.simpleCounter;

import dev.jorik.counters.entities.SimpleCounter;
import dev.jorik.counters.model.DbHandler;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

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
        updateCounter();
    }

    Observable updateRx(){
        return Observable.just(updateCounter())
                .subscribeOn(Schedulers.io());
    }

    private int updateCounter(){
        return database.updateCounter(counter.getId(), counter);
    }
}
