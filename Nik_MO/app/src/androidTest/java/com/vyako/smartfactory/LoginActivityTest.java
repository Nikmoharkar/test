package com.vyako.smartfactory;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vyako.smartfactory.main.R;
import com.vyako.smartfactory.main.views.activities.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by nikhilm on 29-Jun-17.
 */

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Test
    public void testLogin() {
        onView(withId(R.id.userNameEditText)).perform(typeText("hjh"));
        onView(withId(R.id.passwordEditText)).perform(typeText("123456"),closeSoftKeyboard());
//        onView(withId(R.id.signUpButton)).perform(click());

//        intended(allOf(hasComponent("com.vyako.smartfactory.main.ui.activities.HomeActivity")));

        ViewInteraction viewInteraction = onView(withId(R.id.launch_mo_module_activity_Button));
        viewInteraction.check(matches(withText("Launch Mo List Activity")));

//        Launch Mo List Activity
//        try {
////            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
