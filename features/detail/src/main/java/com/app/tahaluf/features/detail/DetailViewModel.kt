package com.app.tahaluf.features.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.tahaluf.business.university.domain.main.usecase.GetUniversityDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getUniversityDetailUseCase: GetUniversityDetailUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailUiState>(
        DetailUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    fun fetchUniversityDetail(name: String) {
        viewModelScope.launch {
            getUniversityDetailUseCase.invoke(name)
                .fold(
                    onSuccess = { data ->
                        _uiState.value = DetailUiState.Success(data)
                    },
                    onFailure = { error ->
                        _uiState.value =
                            DetailUiState.Error(errorMessage = "Something went wrong. ${error.message.orEmpty()}")
                    }
                )
        }
    }
}
