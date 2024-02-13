package i212474.a1.i212474

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class page2_Activity_loginTest {

    @Test
    fun clickButton() {
        // Launch the activity
        val activityScenario = ActivityScenario.launch(page2_Activity_login::class.java)

        onView(withId(R.id.login)).perform(click())
    }
}
