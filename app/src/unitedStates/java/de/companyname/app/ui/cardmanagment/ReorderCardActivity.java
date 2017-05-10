package de.companyname.app.ui.cardmanagment;

import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.os.Bundle;

import de.companyname.app.ui.BaseMvvmActivity;
import espresso.fail.multidex.databinding.ReorderCardActivityBinding;


public class ReorderCardActivity extends BaseMvvmActivity<ReorderCardViewModel> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ReorderCardActivityBinding binding = DataBindingUtil.setContentView(this, getContentLayoutId());
        binding.setViewModel(getViewModel());

        //View v = binding.toolbardBinding.toolbarTitle;  //<---- ERROR in databinding, does not work

        trackPageView();
    }

    private int getContentLayoutId() {
        return 0;
    }


    @Override
    protected Observable.OnPropertyChangedCallback getOnViewModelPropertyChangedCallback() {
        return new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {

            }
        };
    }

    protected void trackPageView() {
        //track
    }

    public int getOmnitureViewId() {
        return getViewModel().getOmnitureViewId();
    }
}
