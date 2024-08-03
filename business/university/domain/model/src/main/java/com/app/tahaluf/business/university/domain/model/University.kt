package com.app.tahaluf.business.university.domain.model

data class University(
    val country: String,
    val name: String,
    val stateProvince: String,
    val alphaTwoCode: String,
    val webPages: List<String>,
    val domains: List<String>,
)
