package tech.thdev.rx.scope

import io.reactivex.disposables.CompositeDisposable

class RxScope : BaseRxScope {

    override val disposable: CompositeDisposable =
            CompositeDisposable()

    override fun releaseRx() {
        disposable.clear()
    }
}