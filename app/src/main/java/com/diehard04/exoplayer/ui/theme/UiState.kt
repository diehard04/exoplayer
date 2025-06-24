package com.diehard04.exoplayer.ui.theme

sealed class UiState {

    object Loading : UiState()
    data class Success(val url:String) : UiState()
    data class Error(val message: String) : UiState()

}