package com.diehard04.exoplayer.player

import android.content.Context
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

// PlayerManager.kt
class PlayerManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val callback: PlayerEventListener
) {

    private val player: ExoPlayer by lazy {
        ExoPlayer.Builder(context).build().apply {
            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(state: Int) {
                    callback.onPlaybackStateChanged(state)
                }

                override fun onPlayerError(error: PlaybackException) {
                    callback.onPlayerError(error)
                }

                override fun onIsPlayingChanged(isPlaying: Boolean) {
                    callback.onIsPlayingChanged(isPlaying)
                }
            })
        }
    }

    private val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    private val phoneStateListener = object : PhoneStateListener() {
        override fun onCallStateChanged(state: Int, phoneNumber: String?) {
            when (state) {
                TelephonyManager.CALL_STATE_RINGING,
                TelephonyManager.CALL_STATE_OFFHOOK -> {
                    pause()
                }
                TelephonyManager.CALL_STATE_IDLE -> {
                    if (!player.isPlaying) player.play()
                }
            }
        }
    }

    init {
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE)
    }

    fun getPlayer(): ExoPlayer = player

    fun play(url: String) {
        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
        callback.onPlaybackStarted()
    }

    fun pause() {
        player.pause()
        callback.onPlaybackPaused()
    }

    fun release() {
        telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE)
        player.release()
    }
}
