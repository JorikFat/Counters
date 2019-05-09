package dev.jorik.counters.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.List;

import dev.jorik.counters.App;
import dev.jorik.counters.R;
import dev.jorik.counters.activities.main.create.CreateDialog;
import dev.jorik.counters.activities.simpleCounter.CounterActivity;
import dev.jorik.counters.entities.SimpleCounter;

public class MainActivity extends MvpAppCompatActivity implements MainView{
    @InjectPresenter MainPresenter presenter;
    private CounterAdapter adapter;

    @ProvidePresenter
    MainPresenter providePresenter(){
        return new MainPresenter(((App) getApplication()).getDatabase());
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
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadData(List<SimpleCounter> data) {
        adapter.setData(data);
    }

    @Override
    public void addCounter(int position) {
        adapter.addItem(position);
    }

    @Override
    public void updateItem(int position) {
        adapter.updateItem(position);
    }

    @Override
    public void removeItem(int position) {
        adapter.removeItem(position);
    }

    @Override
    public void openActivity(int id) {
        Intent intent = new Intent(this, CounterActivity.class);
        intent.putExtra(CounterActivity.COUNTER, id);
        startActivity(intent);
    }

    @Override
    public void createCounter() {
        getSupportFragmentManager().beginTransaction().
                add(CreateDialog.constructor(presenter), null).
                commit();
    }
}
