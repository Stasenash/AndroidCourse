package com.example.animeapi

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule

import org.junit.Rule
import org.junit.Test

class AnimeUITest {
    private val str = "123"

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun сhangeTextTest() {
        onView(withId(R.id.textInput))
            .perform(typeText(str), closeSoftKeyboard())
        onView(withId(R.id.changeButton)).perform(click())

        onView(withId(R.id.textToBeChanged))
            .check(matches(withText(str)))
    }
}