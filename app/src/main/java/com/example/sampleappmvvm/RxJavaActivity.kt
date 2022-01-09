package com.example.sampleappmvvm

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.login_activity.*

class RxJavaActivity : DaggerAppCompatActivity() {

    private val compositable = CompositeDisposable()

    companion object {
        const val SLEEPING_TIME = 5000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        button1.setOnClickListener {
            testSingle()
        }

        button2.setOnClickListener {
            testSingleJust()
        }

        button3.setOnClickListener {
            testObservable()
        }

        //Single.defer { Single.just(getSleeping()) }
        val test = Single.fromCallable { getSleeping() }
            .doOnSubscribe {
                println("EK doOnSubscribe Thread ${Thread.currentThread().name}")
                //Single.just(getSleeping()).blockingGet()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
        println("$test")
    }

    private fun getSleeping(): Int {
        println("EK getSleeping Thread ${Thread.currentThread().name}")
        Thread.sleep(SLEEPING_TIME)
        return 10
    }

    fun testSingle() {
        val single = Single.fromCallable { getCars() }.map { carList -> carList.map { car -> SuperCar(car.brand!!, 3000) } }
        compositable.add(
            single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ d ->
                    println("EK Received $d")
                }, { t ->
                    println("EK error $t")
                })
        )
    }

    private fun testSingleJust() {
        val singleJust = Single.just(getSleeping())
        compositable.add(
            singleJust
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ d ->
                    println("EK Received $d")
                }, { t ->
                    println("EK error $t")
                })
        )
    }

    private fun testObservable() {
        val observable = Observable.fromCallable { getSleeping() }
        compositable.add(
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ d ->
                    println("EK Received $d")
                }, { t ->
                    println("EK error $t")
                })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositable.clear()
    }

    fun getCars(): ArrayList<Car> {
        val bmw = Car(null, 4)
        val mercedes = Car("MERCEDES", 4)
        val toyota = Car("TOYOTA", 4)
        return arrayListOf(bmw, mercedes, toyota)
    }

}

data class Car(val brand: String?, val wheels: Int)
data class SuperCar(val brand: String, val price: Int)