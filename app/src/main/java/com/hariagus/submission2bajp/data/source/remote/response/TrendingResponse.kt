package com.hariagus.submission2bajp.data.source.remote.response

data class TrendingResponse(
    val page: Int,
    val results: List<TrendingItem>,
)