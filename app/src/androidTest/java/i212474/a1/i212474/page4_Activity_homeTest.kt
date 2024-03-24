package i212474.a1.i212474

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class page4_Activity_homeTest{
    @Test
    fun clickButton() {
        // Launch the activity
        val activityScenario = ActivityScenario.launch(page4_Activity_home::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.noti)).perform(ViewActions.click())
    }
}