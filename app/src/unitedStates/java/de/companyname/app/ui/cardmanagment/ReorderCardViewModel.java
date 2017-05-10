package de.companyname.app.ui.cardmanagment;

import android.support.annotation.IdRes;
import android.widget.RadioGroup;

import de.companyname.app.ui.BaseViewModel;

import javax.inject.Inject;


public class ReorderCardViewModel extends BaseViewModel {

    private ReorderCardModel mReorderCardModel;

    @Inject
    public ReorderCardViewModel(ReorderCardModel reorderCardModel) {
        mReorderCardModel = reorderCardModel;
    }

    public int getOmnitureViewId() {
        return 0;
    }

    public void onRadioGroupChange(RadioGroup group, @IdRes int checkedId) {
        //
    }


}
