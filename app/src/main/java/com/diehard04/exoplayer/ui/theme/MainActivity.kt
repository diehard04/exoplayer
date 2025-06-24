package com.diehard04.exoplayer.ui.theme

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.ui.PlayerView
import com.diehard04.exoplayer.R
import com.diehard04.exoplayer.vm.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

// MainActivity.kt
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var playerView: PlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerView = findViewById(R.id.player_view)

        supportFragmentManager.beginTransaction()
            .replace(R.id.player_fragment_container, PlayerFragment())
            .commit()
    }

    override fun onStart() {
        super.onStart()
        val viewModel: PlayerViewModel by viewModels()
        viewModel.getPlayerManager().getPlayer().let {
            playerView.player = it
        }
    }

    override fun onStop() {
        super.onStop()
        playerView.player = null
    }

    override fun onPause() {
        super.onPause()
        val viewModel: PlayerViewModel by viewModels()
        viewModel.getPlayerManager().pause()
    }

    override fun onResume() {
        super.onResume()
        val viewModel: PlayerViewModel by viewModels()
        val player = viewModel.getPlayerManager().getPlayer()
        if (!player.isPlaying) {
            player.play()
        }
    }

}
