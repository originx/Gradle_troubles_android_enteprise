package espresso.fail.multidex;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    IntentsTestRule<MainActivity> mMainActivityIntentsTestRule = new IntentsTestRule<MainActivity>(MainActivity
            .class, true, false);
    @Test
    public void testStartActivity() throws Exception {
        mMainActivityIntentsTestRule.launchActivity(null);
        onView(withId(R.id.hello)).check(matches(withText("Hello World!")));

    }

}
