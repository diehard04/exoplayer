package com.diehard04.enterprise.presentation.login
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.Lifecycle
import com.diehard04.enterprise.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by viewModels() {
        LoginViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupListeners()

        observeState()
    }

    private fun setupListeners() {

        binding.loginButton.setOnClickListener {

            val username =
                binding.usernameEditText
                    .text
                    .toString()

            val password =
                binding.passwordEditText
                    .text
                    .toString()

            viewModel.login(
                username,
                password
            )
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.progressBar.visibility =
                        if (state.isLoading) {
                            View.VISIBLE
                        } else {
                            View.GONE
                        }
                    binding.errorText.text =
                        state.error ?: ""
                    if (state.isLoginSuccessful) {
                        binding.errorText.text = "Login successful"
                    }
                }
            }
        }
    }
}