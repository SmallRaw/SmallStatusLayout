package com.smallraw.library.smallstatuslayout

import android.content.Context
import android.view.LayoutInflater
import android.view.View

abstract class StatePage {
    private var mRootView: View? = null
    private var isConfigured: Boolean = false

    fun createStatePageView(
        context: Context,
        inflater: LayoutInflater,
        container: SmallStatusContainer
    ): View {
        return mRootView ?: synchronized(this) {
            mRootView ?: onCreateStatePageView(context, inflater, container).also { mRootView = it }
        }
    }

    @Synchronized
    fun configStateView(view: View, config: SmallStateConfig?, forcedRefresh: Boolean = false) {
        if (forcedRefresh || !isConfigured) {
            onConfigStateView(view, config)
            isConfigured = true
        }
    }

    abstract fun onCreateStatePageView(
        context: Context,
        inflater: LayoutInflater,
        container: SmallStatusContainer
    ): View

    abstract fun onConfigStateView(view: View, config: SmallStateConfig?)

    abstract fun enableReload(): Boolean

    open fun bindRetryView(): View? = null

    open fun bindCloseView(): View? = null
}