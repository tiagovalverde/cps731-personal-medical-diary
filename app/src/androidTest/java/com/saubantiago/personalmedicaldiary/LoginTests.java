package com.saubantiago.personalmedicaldiary;


import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.saubantiago.personalmedicaldiary.activities.auth.LoginActivity;
import com.saubantiago.personalmedicaldiary.activities.dashboard.Dashboard;
import com.saubantiago.personalmedicaldiary.activities.profile.PatientProfileDetailsActivity;
import com.saubantiago.personalmedicaldiary.activities.profile.PatientProfileEditActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class LoginTests {
    String testUserEmail, testUserPass;

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(LoginActivity.class);

    @Before
    public void setUp() {
        testUserEmail = "tiago.valverde@ryerson.ca";
        testUserPass = "12345678";
    }

    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.saubantiago.personalmedicaldiary", appContext.getPackageName());
    }

    @Test
    public void registered_user_can_login() {
        onView(withId(R.id.editTextEmailValue)).perform(clearText(), typeText(testUserEmail));
        onView(withId(R.id.editTextPasswordValue)).perform(clearText(), typeText(testUserPass));
        onView(withId(R.id.loginButton)).perform(click());
        onView(withId(R.id.logoutBtn)).check(matches(isDisplayed()));
    }
}
