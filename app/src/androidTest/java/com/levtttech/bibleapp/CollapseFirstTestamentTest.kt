package com.levtttech.bibleapp

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class CollapseFirstTestamentTest : BaseTest() {

    @Test
    fun test() {
        "Genesis".check(matches(isDisplayed()))
        "Old Testament".action(click())
        "Genesis".check(doesNotExist())
    }
}