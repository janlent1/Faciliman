package com.example.d062654.faciliman;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AbrufenundArchivierenIncident {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void abrufenundArchivierenIncident() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.facmanbutton), withText("Facility Management"),
                        withParent(allOf(withId(R.id.content_main),
                                withParent(withId(R.id.fragment_container)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.fac_username),
                        withParent(allOf(withId(R.id.content_main),
                                withParent(withId(R.id.fragment_container)))),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("R.Hartmann"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.fac_password),
                        withParent(allOf(withId(R.id.content_main),
                                withParent(withId(R.id.fragment_container)))),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("password"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.fac_login), withText("Login"),
                        withParent(allOf(withId(R.id.content_main),
                                withParent(withId(R.id.fragment_container)))),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.facarchive), withText("Archivieren"),
                        withParent(allOf(withId(R.id.content_main),
                                withParent(withId(R.id.fragment_container)))),
                        isDisplayed()));
        appCompatButton3.perform(click());

    }

}
