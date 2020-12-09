package com.smallraw.library.smallstatuslayout

import android.content.Context
import android.view.LayoutInflater
import android.view.View

abstract class StatePage {
    abstract fun onCreateStatePageView(
        context: Context,
        inflater: LayoutInflater,
        container: SmallStatusContainer
    ): View

    abstract fun onStateViewCreate(view: View)

    abstract fun enableReload(): Boolean

    open fun bindRetryView(): View? = null

    open fun bindCloseView(): View? = null
}