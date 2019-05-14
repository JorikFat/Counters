package dev.jorik.counters.activities.main;

import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import dev.jorik.counters.R;
import dev.jorik.counters.activities.main.create.CreateDialog;
import dev.jorik.counters.entities.SimpleCounter;
import dev.jorik.counters.model.DbHandler;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView>
    implements
        CounterAdapter.Callback,
        View.OnClickListener,
        CreateDialog.Callback {
    private MainModel model;

    public MainPresenter(DbHandler database) {
        this.model = new MainModel(database);
    }

    public void viewIsReady(){

        getViewState().loadData(model.getData());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_mainA_add:
                getViewState().createCounter();
                break;
            default:
        }
    }

    @Override
    public void onClick(int position) {
        long id = model.getData().get(position).getId();
        getViewState().openActivity(id);
    }

    @Override
    public boolean onHold(int position) {
        SimpleCounter delCounter = model.getData().remove(position);
        model.deleteItem(delCounter.getId());
        getViewState().removeItem(position);
        return delCounter != null;
    }

    @Override
    public void plusClick(int position) {
        model.getData().get(position).increase();
        model.updateItem(position);
        getViewState().updateItem(position);
    }

    @Override
    public void minusClick(int position) {
        model.getData().get(position).degrease();
        model.updateItem(position);
        getViewState().updateItem(position);
    }

    @Override
    public void result(boolean create) {
        if (create){
            model.addLastCounter();
            getViewState().addCounter(model.getData().size()-1);
        } else {
            getViewState().showToast("creating cancel");
        }
    }
}
