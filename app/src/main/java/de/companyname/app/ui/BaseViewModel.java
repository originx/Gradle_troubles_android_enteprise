package de.companyname.app.ui;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public abstract class BaseViewModel extends BaseObservable {


    /**
     * This is only called by Fragments.
     * <p>
     * Called when a fragment is first attached to its context.
     * {@link #onCreate(Bundle)} will be called after this.
     */
    public void onAttach() {
        //no-op
    }

    /**
     * Called when the activity/fragment is starting.
     * For Fragments this is called after {@link #onAttach()} and before, but is not called if the fragment
     * instance is retained across Activity re-creation (see {@link android.app.Fragment#setRetainInstance(boolean)}).
     *
     * @param savedInstanceState If the activity/fragment is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null
     *                           .</i></b>
     */
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //no-op
    }

    /**
     * This is only called by Fragments.
     * <p>
     * Called when the fragment's activity has been created and this
     * fragment's view hierarchy instantiated.  It can be used to do final
     * initialization once these pieces are in place, such as retrieving
     * views or restoring state.  It is also useful for fragments that use
     * {@link android.app.Fragment#setRetainInstance(boolean)} to retain their instance,
     * as this callback tells the fragment when it is fully associated with
     * the new activity instance.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        }
    }

    /**
     * Called to retrieve per-instance state from an activity before being killed
     * so that the state can be restored in {@link #onCreate} or
     * {@link #onRestoreInstanceState} (the {@link Bundle} populated by this method
     * will be passed to both).
     *
     * @param outState Bundle in which to place your saved state.
     */
    public void onSaveInstanceState(Bundle outState) {
        //no-op
    }

    /**
     * This method is called after {@link #onStart} when the activity/fragment is
     * being re-initialized from a previously saved state, given here in
     * <var>savedInstanceState</var>.  Most implementations will simply use {@link #onCreate}
     * to restore their state, but it is sometimes convenient to do it here
     * after all of the initialization has been done or to allow subclasses to
     * decide whether to use your default implementation.  The default
     * implementation of this method performs a restore of any view state that
     * had previously been frozen by {@link #onSaveInstanceState}.
     *
     * @param savedInstanceState the data most recently supplied in {@link #onSaveInstanceState}.
     */
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        //no-op
    }

    /**
     * Called after {@link #onCreate} or when the activity/fragment had been stopped, but is now again being
     * displayed to the user. It will be followed by {@link #onResume}.
     */
    public void onStart() {
        //no-op
    }

    /**
     * Called after {@link #onRestoreInstanceState} or {@link #onPause}, for your activity/fragment to start interacting
     * with the user. This is a good place to begin animations, open exclusive-access devices (such as the camera), etc.
     */
    public void onResume() {
        //no-op
    }

    /**
     * Called as part of the activity/fragment lifecycle when an activity is going into
     * the background, but has not (yet) been killed.  The counterpart to
     * {@link #onResume}.
     */
    public void onPause() {
        //no-op
    }

    /**
     * Called when you are no longer visible to the user.  You will next
     * receive either {@link #onDestroy}, or nothing, depending on later user activity.
     */
    public void onStop() {
        //no-op
    }

    /**
     * Perform any final cleanup before an activity/fragment is destroyed.  This can
     * happen either because the activity is finishing (someone called
     * {@link android.app.Activity#finish} on it, or because the system is temporarily destroying
     * this instance of the activity to save space.
     * For fragments this is called after {@link #onStop()} and before {@link #onDetach()}.
     */
    public void onDestroy() {
        //no-op
    }

    /**
     * This is only called by Fragments.
     * <p>
     * Called when the fragment is no longer attached to its activity.  This is called after
     * {@link #onDestroy()}, except in the cases where the fragment instance is retained across
     * Activity re-creation (see {@link android.app.Fragment#setRetainInstance(boolean)}), in which case it is called
     * after {@link #onStop()}.
     */
    public void onDetach() {
        //no-op
    }
}
