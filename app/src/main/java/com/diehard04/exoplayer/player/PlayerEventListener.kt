package com.diehard04.exoplayer.player

import androidx.media3.common.PlaybackException

// PlayerEventListener.kt
interface PlayerEventListener {
    fun onPlaybackStateChanged(state: Int)
    fun onPlayerError(error: PlaybackException)
    fun onIsPlayingChanged(isPlaying: Boolean)
    fun onPlaybackStarted()
    fun onPlaybackPaused()
    fun onError(message: String)
}