package com.example.sampleappmvvm.articlesList.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.ViewModelProvider
import com.example.sampleappmvvm.articleDetails.ui.ArticleDetailsActivity
import com.example.sampleappmvvm.articlesList.di.ArticlesListViewModelProviderFactory
import com.example.sampleappmvvm.articlesList.viewmodel.ArticlesListViewModel
import com.example.sampleappmvvm.login.ui.LoginActivity
import com.example.sampleappmvvm.login.ui.LoginActivity.Companion.LOGOUT
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ArticlesListFragment : DaggerFragment() {

    private lateinit var viewModel: ArticlesListViewModel

    @Inject
    lateinit var factory: ArticlesListViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ArticlesList(viewModel = viewModel, logout())
            }
        }
    }

    private fun logout(): () -> Unit = {
        viewModel.logOut()
        activity?.finish()
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadArticles()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[ArticlesListViewModel::class.java]
        viewModel.loadArticles()

        viewModel.viewModelData.observe(requireActivity(), { state ->
            when (state) {
                is ArticlesListViewModel.State.ItemClick -> {
                    val intent = Intent(requireContext(), ArticleDetailsActivity::class.java)
                    intent.apply { putExtra(ArticleDetailsActivity.ITEM_KEY, state.item.id) }
                    startActivity(intent)
                }
                is ArticlesListViewModel.State.NoAuth -> {
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    intent.putExtra(LOGOUT, true)
                    startActivity(intent)
                }
                is ArticlesListViewModel.State.Loaded -> {}
            }
        })
    }
}