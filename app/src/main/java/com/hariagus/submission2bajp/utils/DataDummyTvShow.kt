package com.hariagus.submission2bajp.utils

import com.hariagus.submission2bajp.data.source.local.entity.TvShowEntity
import com.hariagus.submission2bajp.data.source.remote.response.TvShowItem

object DataDummyTvShow {

    fun generateDummyTvShow(): List<TvShowEntity> {
        val tvShow = ArrayList<TvShowEntity>()
        tvShow.add(
            TvShowEntity(
                "2018-05-02",
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                "en",
                "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
                "/gL8myjGc2qrmqVosyGm5CWTir9A.jpg",
                1495.092,
                8.1,
                77169,
                "Cobra Kai",
                1529,
            )
        )
        tvShow.add(
            TvShowEntity(
                "2018-10-26",
                "As her 16th birthday nears, Sabrina must choose between the witch world of her family and the human world of her friends. Based on the Archie comic.",
                "en",
                "/yxMpoHO0CXP5o9gB7IfsciilQS4.jpg",
                "/8AdmUPTyidDebwIuakqkSt6u1II.jpg",
                1097.927,
                8.4,
                79242,
                "Chilling Adventures of Sabrina",
                1988
            )
        )
        return tvShow
    }

    fun generateRemoteDummyTvShow(): List<TvShowItem> {
        val tvShow = ArrayList<TvShowItem>()
        tvShow.add(
            TvShowItem(
                "2018-05-02",
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                "en",
                "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
                "/gL8myjGc2qrmqVosyGm5CWTir9A.jpg",
                1495.092,
                8.1,
                77169,
                "Cobra Kai",
                1529,
            )
        )
        tvShow.add(
            TvShowItem(
                "2018-10-26",
                "As her 16th birthday nears, Sabrina must choose between the witch world of her family and the human world of her friends. Based on the Archie comic.",
                "en",
                "/yxMpoHO0CXP5o9gB7IfsciilQS4.jpg",
                "/8AdmUPTyidDebwIuakqkSt6u1II.jpg",
                1097.927,
                8.4,
                79242,
                "Chilling Adventures of Sabrina",
                1988
            )
        )
        return tvShow
    }

}