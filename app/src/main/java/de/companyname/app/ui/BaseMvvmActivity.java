package de.companyname.app.ui;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * Base class for all MVVM activites. Provides the required infrastructure for injecting the VM and calling the
 * lifecycle methods.
 */

public abstract class BaseMvvmActivity<ViewModelT extends BaseViewModel> extends BaseActivity {
    private ViewModelT mViewModel;

    @Inject
    void injectViewModel(ViewModelT viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.onStart();
        mViewModel.addOnPropertyChangedCallback(getOnViewModelPropertyChangedCallback());
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewModel.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewModel.onStop();
        mViewModel.removeOnPropertyChangedCallback(getOnViewModelPropertyChangedCallback());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mViewModel.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mViewModel.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.onDestroy();
    }

    @NonNull
    protected abstract Observable.OnPropertyChangedCallback getOnViewModelPropertyChangedCallback();

    public ViewModelT getViewModel() {
        return mViewModel;
    }
}
