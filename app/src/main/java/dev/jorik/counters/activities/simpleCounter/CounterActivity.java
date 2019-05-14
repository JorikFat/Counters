package dev.jorik.counters.activities.simpleCounter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import dev.jorik.counters.App;
import dev.jorik.counters.R;
import dev.jorik.counters.entities.SimpleCounter;
import dev.jorik.counters.model.DbHandler;
import dev.jorik.counters.utils.SimpleCounterWrapper;

public class CounterActivity extends MvpAppCompatActivity implements CounterView{
    public static final String COUNTER = "counter";
    @InjectPresenter CounterPresenter presenter;
    private TextView count;

    @ProvidePresenter
    public CounterPresenter providePresenter(){
        long id = getIntent().getLongExtra(COUNTER, -1);
//        SimpleCounter counter = ((App) getApplication()).getCounters().get(id);
        DbHandler database = ((App) getApplication()).getDatabase();
        return new CounterPresenter(database, id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__simple_counter);
        count = findViewById(R.id.tv_counterA_count);
        findViewById(R.id.btn_counterA_minus).setOnClickListener(presenter);
        findViewById(R.id.btn_counterA_plus).setOnClickListener(presenter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.viewIsReady();
    }

    @Override
    public void setupTitle(String name) {
        setTitle(name);
    }

    @Override
    public void updateValue(String newVal) {
        count.setText(newVal);
    }
}

