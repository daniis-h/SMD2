package i212474.a1.i212474

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class page19_Activity_forgetEmailTest{


    @Test
    fun testButton() {
        ActivityScenario.launch(page19_Activity_forgetEmail::class.java)
        Thread.sleep(3000)
        onView(withId(R.id.send)).perform(click())
        }

}