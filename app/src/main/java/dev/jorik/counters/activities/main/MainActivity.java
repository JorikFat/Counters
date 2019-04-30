package dev.jorik.counters.activities.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import dev.jorik.counters.R;
import dev.jorik.counters.entities.SimpleCounter;

public class MainActivity extends MvpAppCompatActivity implements MainView{
    @InjectPresenter MainPresenter presenter;
    private CounterAdapter adapter;

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
}
