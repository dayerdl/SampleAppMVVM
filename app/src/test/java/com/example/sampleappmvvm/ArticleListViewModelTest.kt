package com.example.sampleappmvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.sampleappmvvm.articlesList.repository.ArticlesRepository
import com.example.sampleappmvvm.articlesList.view.getMockArticles
import com.example.sampleappmvvm.articlesList.viewmodel.ArticlesListViewModel
import com.example.sampleappmvvm.articlesList.viewmodel.OnArticleClickListener
import com.example.sampleappmvvm.login.repository.AuthRepository
import com.example.sampleappmvvm.utils.CustomResult
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArticleListViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var articlesRepository: ArticlesRepository

    @MockK
    lateinit var authRepository: AuthRepository

    @MockK
    lateinit var listener: OnArticleClickListener

    private lateinit var listViewModel: ArticlesListViewModel


    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        listViewModel = ArticlesListViewModel(articlesRepository, authRepository, listener)
    }

    @Test
    fun `When loading articles successfully the state is updated correctly`() {
        val token = "mockToken"
        val listArticles = getMockArticles()
        val result = CustomResult.Success(listArticles)
        every { authRepository.getToken() }.returns(token)
        coEvery { articlesRepository.loadArticles(token) } returns result

        coroutinesTestRule.testDispatcher.runBlockingTest {
            listViewModel.viewModelData.value?.let { it ->
                if (it is ArticlesListViewModel.State.Loaded) {
                    assert(it.articles == listArticles)
                }
            }
        }
    }

    @Test
    fun `Verify the loading state is called while loading articles`(){
        coroutinesTestRule.testDispatcher.runBlockingTest {
            val observer = mockk<Observer<ArticlesListViewModel.State>>()
            val slot = slot<ArticlesListViewModel.State>()
            val list = arrayListOf<ArticlesListViewModel.State>()

            every { observer.onChanged(capture(slot)) } answers {
                list.add(slot.captured)
            }
            listViewModel.viewModelData.observeForever(observer)
            verify { observer.onChanged(ArticlesListViewModel.State.Loading) }
        }
    }


    @Test
    fun `When token is not present the state is updated to NoAuth`() {
        every { authRepository.getToken() }.returns(null)
        coroutinesTestRule.testDispatcher.runBlockingTest {
            assert(listViewModel.viewModelData.value is ArticlesListViewModel.State.NoAuth)
        }
    }

    @Test
    fun `When I click on an item the listener is called passing the correct article id`(){
        val article = getMockArticles()[0]
        listViewModel.itemClick(article)
        verify { listener.onItemClickListener(127) }
    }
}