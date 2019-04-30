package dev.jorik.counters.activities.main.create;

import android.content.DialogInterface;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import dev.jorik.counters.entities.SimpleCounter;

@InjectViewState
public class CreatePresenter extends MvpPresenter<CreateView>
    implements
        DialogInterface.OnClickListener {

    private CreateModel model = new CreateModel();

    public void createDialog(String name){
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
//                getViewState().onResult(true);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                getViewState().onResult(false);
                break;
            default:
        }
    }
}
