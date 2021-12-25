package com.hariagus.submission2bajp.data.source.local.entity

data class
TvShowEntity(
    val firstAirDate: String,
    val overview: String,
    val originalLanguage: String,
    val posterPath: String,
    val backdropPath: String? = null,
    val popularity: Double,
    val voteAverage: Double,
    val id: Int,
    val name: String,
    val voteCount: Int
)