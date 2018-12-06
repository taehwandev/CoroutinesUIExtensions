package tech.thdev.support.base.coroutines.ui

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatDialogFragment
import tech.thdev.coroutines.scope.BaseCoroutineScope
import tech.thdev.coroutines.scope.UICoroutineScope

abstract class CoroutineScopeDialog @SuppressLint("ValidFragment") @JvmOverloads constructor(
        scope: BaseCoroutineScope = UICoroutineScope()) : AppCompatDialogFragment(), BaseCoroutineScope by scope {

    override fun onDestroyView() {
        super.onDestroyView()

        releaseCoroutine()
    }
}