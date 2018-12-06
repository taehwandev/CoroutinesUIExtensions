package tech.thdev.support.base.ui

import android.annotation.SuppressLint
import tech.thdev.rx2.scope.BaseRxScope
import tech.thdev.rx2.scope.RxScope
import tech.thdev.support.base.coroutines.ui.CoroutineScopeDialog

abstract class RxCoroutineScopeDialog @SuppressLint("ValidFragment") @JvmOverloads constructor(
        scope: BaseRxScope = RxScope()) : CoroutineScopeDialog(), BaseRxScope by scope {

    override fun onDestroyView() {
        super.onDestroyView()

        releaseRx()
    }
}