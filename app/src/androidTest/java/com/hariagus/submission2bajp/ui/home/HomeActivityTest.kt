package com.hariagus.submission2bajp.ui.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.hariagus.submission2bajp.R
import com.hariagus.submission2bajp.utils.DataDummyMovies
import com.hariagus.submission2bajp.utils.DataDummyTvShow
import com.hariagus.submission2bajp.utils.EspressoIdlingResource
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test


class HomeActivityTest {

    private val dummyMovies = DataDummyMovies.generateDummyMovies()
    private val dummyTvShows = DataDummyTvShow.generateDummyTvShow()

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun clickChangeLanguage() {
        onView(withId(R.id.ivChangeLanguage)).perform(click())
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size)
        )
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rvMovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.posterBg)).check(matches(isDisplayed()))
        onView(withId(R.id.roundedPosterDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverviewDetail)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size)
        )
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rvTvShow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        onView(withId(R.id.posterBg)).check(matches(isDisplayed()))
        onView(withId(R.id.roundedPosterDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitleDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverviewDetail)).check(matches(isDisplayed()))
    }

    @Test
    fun emptyMovies() {
        onView(withId(R.id.rvMovie)).perform(setVisibility(false))
        onView(withId(R.id.lottie_empty_movie)).perform(setVisibility(true))
    }

    @Test
    fun emptyTvShow() {
        onView(withId(R.id.rvTvShow)).perform(setVisibility(false))
        onView(withId(R.id.lottie_empty_tvshow)).perform(setVisibility(true))
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    private fun setVisibility(value: Boolean): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(View::class.java)
            }

            override fun getDescription(): String {
                return "Show / Hide View"
            }

            override fun perform(uiController: UiController?, view: View?) {
                view?.visibility = if (value) View.VISIBLE else View.GONE
            }
        }
    }

}