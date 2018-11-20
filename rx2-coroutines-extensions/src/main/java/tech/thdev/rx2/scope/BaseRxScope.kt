package tech.thdev.rx2.scope

import io.reactivex.disposables.CompositeDisposable

interface BaseRxScope {

    val disposable: CompositeDisposable

    fun releaseRx()
}