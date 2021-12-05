package com.example.sampleappmvvm.login.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelProvider
import com.example.sampleappmvvm.articlesList.view.ArticlesListActivity
import com.example.sampleappmvvm.login.LoginViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class LoginFragment : DaggerFragment() {

    private lateinit var loginViewModel: LoginViewModel

    @Inject
    lateinit var factory: LoginViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                LoginScreen(loginViewModel::onLoginClicked)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        loginViewModel.viewModelData.observe(viewLifecycleOwner, { state ->
            when (state) {
                LoginViewModel.State.TokenStored -> {
                    val intent = Intent(requireContext(), ArticlesListActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                LoginViewModel.State.IncorrectCredentials -> Toast.makeText(
                    requireContext(),
                    "Incorrect username and password",
                    Toast.LENGTH_LONG
                ).show()
                LoginViewModel.State.TechnicalError -> Toast.makeText(
                    requireContext(),
                    "There is technical problem, please contact technical support",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

    }

}