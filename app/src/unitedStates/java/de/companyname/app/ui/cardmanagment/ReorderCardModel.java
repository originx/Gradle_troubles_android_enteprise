package de.companyname.app.ui.cardmanagment;

import android.databinding.BaseObservable;

import javax.inject.Inject;


public class ReorderCardModel extends BaseObservable{
    private int mReason1;
    private int mReason2;

//    private int mReason1DescVisibility;  // TODO remove
//    private int mReason2DescVisibility; //TODO remove
//
//    @Bindable
//    public int getReason1DescVisibility() {
//        return mReason1DescVisibility;
//    }
//
//    public void setReason1DescVisibility(int reason1DescVisibility) {
//        mReason1DescVisibility = reason1DescVisibility;
//        notifyPropertyChanged(BR.reason1DescVisibility);
//    }
//
//    @Bindable
//    public int getReason2DescVisibility() {
//        return mReason2DescVisibility;
//    }
//
//    public void setReason2DescVisibility(int reason2DescVisibility) {
//        mReason2DescVisibility = reason2DescVisibility;
//        notifyPropertyChanged(BR.reason2DescVisibility);
//    }

    public int getReason1() {
        return mReason1;
    }

    public void setReason1(int reason1) {
        mReason1 = reason1;
    }

    public int getReason2() {
        return mReason2;
    }

    public void setReason2(int reason2) {
        mReason2 = reason2;
    }

    @Inject
    public ReorderCardModel() {
    }
}
