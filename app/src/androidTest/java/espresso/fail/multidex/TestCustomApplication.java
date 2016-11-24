package espresso.fail.multidex;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

/**
 * Created by morsolic on 24/11/16.
 */

public class TestCustomApplication  extends CustomApplication{

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            MultiDex.install(this);
        } catch (RuntimeException e) {
            Log.e(CustomApplication.class.getSimpleName(), "ignoring failed multidex installation in test flight", e);
            throw e;
        }
    }
}
