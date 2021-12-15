package com.example.sampleappmvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.sampleappmvvm.login.LoginViewModel
import com.example.sampleappmvvm.login.repository.AuthRepository
import com.example.sampleappmvvm.login.ui.OnTokenStored
import com.example.sampleappmvvm.server.NetworkErrors
import com.example.sampleappmvvm.server.TokenRequest
import com.example.sampleappmvvm.server.TokenResponse
import com.example.sampleappmvvm.utils.CustomResult
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var authRepository: AuthRepository

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = LoginViewModel(authRepository)
    }

    @Test
    fun `When login is clicked the token gets stored and listener is called`(){
        val user = "user"
        val pass = "pass"
        val request = TokenRequest(user, pass,"password")
        val tokenResponse = TokenResponse("access","refresh")
        coEvery { authRepository.generateToken(request) } returns CustomResult.Success(tokenResponse)

        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.onLoginClicked(user, pass)
            verify { authRepository.storeToken("access") }
        }
    }

    @Test
    fun `When there is a problem retrieving the token the state is updated to Technical Error`() {

        coEvery { authRepository.generateToken(any()) } returns CustomResult.Failure(NetworkErrors.IncorrectCredentials)

        coroutinesTestRule.testDispatcher.runBlockingTest {
            val observer = mockk<Observer<LoginViewModel.State>>()
            val slot = slot<LoginViewModel.State>()
            val list = arrayListOf<LoginViewModel.State>()

            every { observer.onChanged(capture(slot)) } answers {
                list.add(slot.captured)
            }
            viewModel.viewModelData.observeForever(observer)
            viewModel.onLoginClicked("incorrect","credentials")
            verify { observer.onChanged(LoginViewModel.State.IncorrectCredentials) }
        }
    }
}