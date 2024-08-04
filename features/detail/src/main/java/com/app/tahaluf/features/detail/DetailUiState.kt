package com.app.tahaluf.features.detail

import com.app.tahaluf.business.university.domain.model.University

sealed class DetailUiState {

    object Loading : DetailUiState()

    data class Success(
        val university: University,
    ) : DetailUiState()

    data class Error(
        val errorMessage: String,
    ) : DetailUiState()
}