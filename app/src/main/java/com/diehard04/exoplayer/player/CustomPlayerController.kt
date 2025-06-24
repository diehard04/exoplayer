package com.diehard04.exoplayer.player

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.media3.common.Player
import com.diehard04.exoplayer.R

// CustomPlayerController.kt
class CustomPlayerController @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private var playButton: ImageButton
    private var pauseButton: ImageButton
    private var player: Player? = null
    private var listener: PlayerControlListener? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_player_controller, this, true)
        playButton = findViewById(R.id.play_button)
        pauseButton = findViewById(R.id.pause_button)


        playButton.setOnClickListener { listener?.onPlayClicked() }
        pauseButton.setOnClickListener { listener?.onPauseClicked() }

    }

    fun setControlListener(listener: PlayerControlListener) {
        this.listener = listener
    }

    fun removeControlListener() {
        this.listener = null
    }

    fun bindPlayer(player: Player) {
        this.player = player
    }

    fun unbindPlayer() {
        this.player = null
    }
}
