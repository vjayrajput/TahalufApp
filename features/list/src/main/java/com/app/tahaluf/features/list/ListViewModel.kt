package com.app.tahaluf.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.tahaluf.business.university.domain.main.usecase.GetUniversityListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(
    private val getUniversityListUseCase: GetUniversityListUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<ListUiState>(
        ListUiState.Loading
    )
    val uiState = _uiState.asStateFlow()

    init {
        refreshData()
    }

    fun refreshData() {
        fetchUniversityList()
    }

    private fun fetchUniversityList() {
        viewModelScope.launch {
            try {
                _uiState.value = ListUiState.Loading
                getUniversityListUseCase.invoke(DEFAULT_COUNTRY_NAME).fold(
                    onSuccess = { data ->
                        _uiState.value = ListUiState.Success(data = data)
                    },
                    onFailure = { error ->
                        error.printStackTrace()
                        showErrorView()
                    }
                )
            } catch (e: Exception) {
                e.printStackTrace()
                showErrorView()
            }
        }
    }

    private fun showErrorView() {
        _uiState.value =
            ListUiState.Error(errorMessage = "Something went wrong.\n Please Try again")
    }

    companion object {
        /**
         * used default country UAE
         */
        private const val DEFAULT_COUNTRY_NAME = "United Arab Emirates"
    }
}

