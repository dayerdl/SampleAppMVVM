package com.example.sampleappmvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.sampleappmvvm.articleDetails.repository.ArticleDetailsRepository
import com.example.sampleappmvvm.articleDetails.ui.getMockArticleDetails
import com.example.sampleappmvvm.articleDetails.viewmodel.ArticleDetailsViewModel
import com.example.sampleappmvvm.login.repository.AuthRepository
import com.example.sampleappmvvm.utils.CustomResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArticleDetailsTest {
    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var articleDetailsRepository: ArticleDetailsRepository

    @MockK
    lateinit var authRepository: AuthRepository

    var articleId = 1

    private lateinit var viewModel: ArticleDetailsViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = ArticleDetailsViewModel(articleDetailsRepository, authRepository, articleId)
    }

    @Test
    fun `When the details are loaded correctly the state gets updated correctly`(){
        val token = "mockToken"
        val articleListDetails = getMockArticleDetails()
        val result = CustomResult.Success(articleListDetails)
        every { authRepository.getToken() }.returns(token)
        coEvery { articleDetailsRepository.loadArticleDetails(articleId, token) } returns result

        coroutinesTestRule.testDispatcher.runBlockingTest {
            viewModel.viewModelData.value?.let { it ->
                if (it is ArticleDetailsViewModel.State.Loaded) {
                    assert(it.details.articleDetails == articleListDetails)
                }
            }
        }
    }
}