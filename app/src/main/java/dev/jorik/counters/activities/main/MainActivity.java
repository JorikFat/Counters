package dev.jorik.counters.activities.main;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import dev.jorik.counters.App;
import dev.jorik.counters.R;
import dev.jorik.counters.activities.simpleCounter.CounterActivity;
import dev.jorik.counters.entities.SimpleCounter;
import dev.jorik.counters.utils.DataSet;
import dev.jorik.counters.utils.SimpleCounterWrapper;

public class MainActivity extends MvpAppCompatActivity implements MainView{
    @InjectPresenter MainPresenter presenter;
    private CounterAdapter adapter;

    @ProvidePresenter
    MainPresenter providePresenter(){
        return new MainPresenter(((App) getApplication()).getCounters());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recycler = findViewById(R.id.rv_mainA_list);
        adapter = new CounterAdapter(presenter);
        recycler.setAdapter(adapter);
        findViewById(R.id.fab_mainA_add).setOnClickListener(presenter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.viewIsReady();
    }

    @Override
    public void loadData(List<SimpleCounter> data) {
        adapter.setData(data);
    }

    @Override
    public void addCounter(SimpleCounter counter) {
        adapter.addCounter(counter);
    }

    @Override
    public void updateItem(int position) {
        adapter.updateItem(position);
    }

    @Override
    public void openActivity(int id) {
        Intent intent = new Intent(this, CounterActivity.class);
        intent.putExtra(CounterActivity.COUNTER, id);
        startActivity(intent);
    }
}
