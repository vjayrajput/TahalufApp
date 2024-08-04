package com.app.tahaluf.features.list

import com.app.tahaluf.business.university.domain.model.University

sealed class ListUiState {

    object Loading : ListUiState()

    data class Success(
        val data: List<University>,
    ) : ListUiState()

    data class Error(
        val errorMessage: String,
    ) : ListUiState()
}