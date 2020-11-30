package com.saubantiago.personalmedicaldiary;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.saubantiago.personalmedicaldiary.activities.profile.PatientProfileDetailsActivity;
import com.saubantiago.personalmedicaldiary.activities.profile.PatientProfileEditActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class PatientProfileTests {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(PatientProfileDetailsActivity.class);

    @Test
    public void details_activity_can_open_edit_activity() {


        onView(withId(R.id.mr_editButton)).perform(click());
        onView(withId(R.id.patientProfileSaveButton)).check(matches(isDisplayed()));
    }


}
