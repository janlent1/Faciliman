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
public class IncidentRecording {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void incidentRecording() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button), withText("Schaden melden"),
                        withParent(allOf(withId(R.id.content_main),
                                withParent(withId(R.id.fragment_container)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.matnr),
                        withParent(allOf(withId(R.id.content_main),
                                withParent(withId(R.id.fragment_container)))),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("628123"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.matpassword),
                        withParent(allOf(withId(R.id.content_main),
                                withParent(withId(R.id.fragment_container)))),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("password"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.matloginbutton), withText("Login"),
                        withParent(allOf(withId(R.id.content_main),
                                withParent(withId(R.id.fragment_container)))),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.select_pic), withText("Bild Auswählen"),
                        withParent(allOf(withId(R.id.content_main),
                                withParent(withId(R.id.fragment_container)))),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.send_button), withText("Senden"),
                        withParent(allOf(withId(R.id.content_main),
                                withParent(withId(R.id.fragment_container)))),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title_input), withText("Bitte geben Sie dem Schaden einen passenden Namen"),
                        withParent(allOf(withId(R.id.content_main),
                                withParent(withId(R.id.fragment_container)))),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.title_input), withText("Bitte geben Sie dem Schaden einen passenden Namen"),
                        withParent(allOf(withId(R.id.content_main),
                                withParent(withId(R.id.fragment_container)))),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.title),
                        withParent(allOf(withId(R.id.content_main),
                                withParent(withId(R.id.fragment_container)))),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("test"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.detailed_location_description),
                        withParent(allOf(withId(R.id.content_main),
                                withParent(withId(R.id.fragment_container)))),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("yhu"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.damage_description),
                        withParent(allOf(withId(R.id.content_main),
                                withParent(withId(R.id.fragment_container)))),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("xcy"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.descr_finished_button), withText("Senden"),
                        withParent(allOf(withId(R.id.content_main),
                                withParent(withId(R.id.fragment_container)))),
                        isDisplayed()));
        appCompatButton5.perform(click());

    }

}
