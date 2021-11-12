package com.example.sampleappmvvm

import com.example.sampleappmvvm.domain.Repository
import com.example.sampleappmvvm.server.ApiManager
import com.example.sampleappmvvm.server.Movie
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class RepositoryTest {

    @MockK
    lateinit var apiManager: ApiManager

    private lateinit var repository : Repository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = Repository(apiManager)
    }

    @Test
    fun `The repository should return a domain object`(){
        val title = "hola que ase"
        val movie = mockk<Movie>(relaxed = true)
        every { movie.title }.returns(title)

        every { apiManager.provideApiManager().loadData(any()) }.answers { Single.just(movie) }
        repository.loadResponse().test().assertValue { it.titleDomain == "hola que ase" }
    }
}