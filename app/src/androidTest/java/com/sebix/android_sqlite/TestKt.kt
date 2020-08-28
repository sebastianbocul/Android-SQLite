package com.sebix.android_sqlite

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.sebix.android_sqlite.adpaters.NotesAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition

@RunWith(AndroidJUnit4ClassRunner::class)
class TagsFragmentTest {
    @Test
    fun testSwipe() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(5000);
        onView(withId(R.id.mainActivity)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<NotesAdapter.ViewHolder>(1, ViewActions.swipeLeft()));
        Thread.sleep(2000)
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<NotesAdapter.ViewHolder>(0, ViewActions.swipeLeft()));
        Thread.sleep(2000)
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<NotesAdapter.ViewHolder>(2, ViewActions.swipeLeft()));
        Thread.sleep(2000)
    }
}