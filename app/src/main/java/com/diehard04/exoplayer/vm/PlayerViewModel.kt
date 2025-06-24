package com.diehard04.exoplayer.vm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diehard04.exoplayer.player.PlayerEventListener
import com.diehard04.exoplayer.data.model.Resource
import com.diehard04.exoplayer.data.repository.VideoRepository
import com.diehard04.exoplayer.player.PlayerManager
import com.diehard04.exoplayer.ui.theme.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

// PlayerViewModel.kt
@HiltViewModel
class PlayerViewModel @Inject constructor(@ApplicationContext val context: Context,
                                          private val repository: VideoRepository) : ViewModel() {

    private var _playerManager: PlayerManager? = null
    fun initPlayer(callback: PlayerEventListener) {
        if (_playerManager == null) {
            _playerManager = PlayerManager(context, callback)
        }
    }
    fun getPlayerManager(): PlayerManager {
        return _playerManager ?: throw IllegalStateException("Call initPlayer first")
    }


    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState
    fun getVideoUrl() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            when (val result = repository.fetchVideo()) {
                is Resource.Success -> _uiState.value = UiState.Success(result.data)
                is Resource.Error -> _uiState.value = UiState.Error(result.message)
                else -> {}
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        _playerManager?.release()
    }
}