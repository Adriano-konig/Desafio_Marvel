package br.com.zup.marvel.ui.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.zup.marvel.R
import br.com.zup.marvel.USER_KEY
import br.com.zup.marvel.databinding.ActivityLoginBinding
import br.com.zup.marvel.domain.model.User
import br.com.zup.marvel.ui.register.view.RegisterActivity
import br.com.zup.marvel.ui.home.view.HomeActivity
import br.com.zup.marvel.ui.login.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setTitle(R.string.login)

        binding.tvRegisterNow?.setOnClickListener {
            goToRegister()
        }

        binding.bvLogin?.setOnClickListener {
            val user = getDataUser()
            viewModel.validateDataUser(user)
        }
        initObservers()
    }

    private fun getDataUser(): User {
        return User(
            email = binding.etUserEmail?.text.toString(),
            password = binding.etPassword?.text.toString()
        )
    }

    private fun goToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun initObservers() {
        viewModel.loginState.observe(this) {
            goToHome(it)
        }

        viewModel.errorState.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
    }

    private fun goToHome(user: User) {
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra(USER_KEY, user)
        }
        startActivity(intent)
    }

}