package com.levtttech.bibleapp

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule

abstract class BaseTest {
    @get:Rule
    val activityTestRule = ActivityScenarioRule(MainActivity::class.java)

    fun String.check(viewAssertion: ViewAssertion): ViewInteraction? {
        return onView(withText(this)).check(viewAssertion)
    }

    fun String.action(viewAction: ViewAction): ViewInteraction? {
        return onView(withText(this)).perform(viewAction)
    }
}