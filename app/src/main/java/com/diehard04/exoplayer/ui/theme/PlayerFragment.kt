package com.diehard04.exoplayer.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.media3.common.PlaybackException
import com.diehard04.exoplayer.R
import com.diehard04.exoplayer.player.PlayerEventListener
import com.diehard04.exoplayer.player.PlayerManager
import com.diehard04.exoplayer.player.CustomPlayerController
import com.diehard04.exoplayer.player.PlayerControlListener
import com.diehard04.exoplayer.vm.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint


// PlayerFragment.kt
@AndroidEntryPoint
class PlayerFragment : Fragment(), PlayerEventListener, PlayerControlListener {

    private val viewModel: PlayerViewModel by activityViewModels()

    private lateinit var controller: CustomPlayerController
    private lateinit var playerManager: PlayerManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_player, container, false)
        controller = view.findViewById(R.id.custom_controller)

        viewModel.initPlayer(this)
        playerManager = viewModel.getPlayerManager()

        val player = playerManager.player
        controller.bindPlayer(player)
        controller.setControlListener(this)

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {}
                is UiState.Success -> playerManager.play(state.url)
                is UiState.Error -> onError(state.message)
            }
        }

        viewModel.getVideoUrl()
        return view
    }

    override fun onPlaybackStarted() {}
    override fun onPlaybackPaused() {}
    override fun onError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
    override fun onPlaybackStateChanged(state: Int) {}
    override fun onPlayerError(error: PlaybackException) {}
    override fun onIsPlayingChanged(isPlaying: Boolean) {}

    override fun onDestroyView() {
        super.onDestroyView()
        controller.unbindPlayer()
        controller.removeControlListener()
    }

    override fun onPlayClicked() {
        playerManager.player.play()
    }

    override fun onPauseClicked() {
        playerManager.pause()
    }
}