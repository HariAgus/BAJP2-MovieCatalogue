package com.hariagus.submission2bajp.ui.home

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.interaction.BaristaClickInteractions.clickOn
import com.adevinta.android.barista.interaction.BaristaListInteractions.clickListItem
import com.hariagus.submission2bajp.R
import com.hariagus.submission2bajp.utils.DataDummyMovies
import com.hariagus.submission2bajp.utils.DataDummyTvShow
import com.hariagus.submission2bajp.utils.EspressoIdlingResource
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {

    private var dummyMovies = DataDummyMovies.generateDummyMovies()
    private var dummyTvShows = DataDummyTvShow.generateDummyTvShow()

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun clickChangeLanguage() {
        clickOn(R.id.ivChangeLanguage)
    }

    @Test
    fun loadMovies() {
        assertDisplayed(R.id.rvMovie)
        clickListItem(R.id.rvMovie, dummyMovies.size)
    }

    @Test
    fun loadDetailMovies() {
        clickListItem(R.id.rvMovie, 0)
        assertDisplayed(R.id.posterBg)
        assertDisplayed(R.id.roundedPosterDetail)
        assertDisplayed(R.id.tvTitleDetail)
        assertDisplayed(R.id.tvReleaseDate)
        assertDisplayed(R.id.tvOverviewDetail)
        assertDisplayed(R.id.tvLanguage)
        assertDisplayed(R.id.tvScoreDetail)
        assertDisplayed(R.id.tvPopularity)
    }

    @Test
    fun loadTvShow() {
        clickOn("TV SHOW")
        assertDisplayed(R.id.rvTvShow)
        clickListItem(R.id.rvTvShow, dummyTvShows.size)
    }

    @Test
    fun loadDetailTvShow() {
        clickOn("TV SHOW")
        clickListItem(R.id.rvTvShow, 0)
        assertDisplayed(R.id.posterBg)
        assertDisplayed(R.id.roundedPosterDetail)
        assertDisplayed(R.id.tvTitleDetail)
        assertDisplayed(R.id.tvReleaseDate)
        assertDisplayed(R.id.tvOverviewDetail)
        assertDisplayed(R.id.tvLanguage)
        assertDisplayed(R.id.tvScoreDetail)
        assertDisplayed(R.id.tvPopularity)
    }

    @Test
    fun emptyMovies() {
        dummyMovies = emptyList()
        dummyMovies.ifEmpty {
            onView(withId(R.id.rvMovie)).perform(setVisibility(false))
            onView(withId(R.id.lottie_empty_movie)).perform(setVisibility(true))
        }
    }

    @Test
    fun emptyTvShow() {
        onView(withText("TV SHOW")).perform(click())
        dummyTvShows = emptyList()
        dummyTvShows.ifEmpty {
            onView(withId(R.id.rvTvShow)).perform(setVisibility(false))
            onView(withId(R.id.lottie_empty_tvshow)).perform(setVisibility(true))
        }
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