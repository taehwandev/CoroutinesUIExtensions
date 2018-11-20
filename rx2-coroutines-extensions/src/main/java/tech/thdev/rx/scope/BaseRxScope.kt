package tech.thdev.rx.scope

import io.reactivex.disposables.CompositeDisposable

interface BaseRxScope {

    val disposable: CompositeDisposable

    fun releaseRx()
}