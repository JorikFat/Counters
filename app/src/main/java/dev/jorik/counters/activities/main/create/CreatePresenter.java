package dev.jorik.counters.activities.main.create;

import android.content.DialogInterface;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import dev.jorik.counters.entities.SimpleCounter;
import dev.jorik.counters.model.DbHandler;

@InjectViewState
public class CreatePresenter extends MvpPresenter<CreateView>
    implements
        DialogInterface.OnClickListener {

    private CreateModel model;

    public CreatePresenter(DbHandler database){
        model = new CreateModel(database);
    }

    public void createCounter(String name){
        if (!name.isEmpty()) {
            SimpleCounter counter = new SimpleCounter(name);
            model.addCounter(counter);
            getViewState().onResult(true);
        } else {
            getViewState().onResult(false);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case DialogInterface.BUTTON_POSITIVE:
                getViewState().passName();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                getViewState().onResult(false);
                break;
            default:
        }
    }
}
