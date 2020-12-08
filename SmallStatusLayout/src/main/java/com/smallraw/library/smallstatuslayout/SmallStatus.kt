package com.smallraw.library.smallstatuslayout

import android.app.Activity
import android.view.View
import android.view.ViewGroup

object SmallStatus {
    @JvmStatic
    fun bind(targetView: View) {
        bind(targetView) { }
    }

    @JvmStatic
    fun bind(
        targetView: View,
        retryEventListener: OnRetryEventListener
    ) {
        bind(targetView, retryEventListener, {})
    }

    @JvmStatic
    fun bind(
        targetView: View,
        onRetryEventListener: OnRetryEventListener,
        onCloseEventListener: OnCloseEventListener?
    ): SmallStatusContainer {
        val parent = targetView.parent as ViewGroup?
        var targetViewIndex = 0
        val smallStatusContainer =
            SmallStatusContainer(
                targetView.context,
                targetView,
                onRetryEventListener,
                onCloseEventListener
            )
        parent?.let { targetViewParent ->
            for (i in 0 until targetViewParent.childCount) {
                if (targetViewParent.getChildAt(i) == targetView) {
                    targetViewIndex = i
                    break
                }
            }
            targetViewParent.removeView(targetView)
            targetViewParent.addView(smallStatusContainer, targetViewIndex, targetView.layoutParams)
        }
        smallStatusContainer.init()
        return smallStatusContainer
    }

    @JvmStatic
    fun bind(
        activity: Activity,
    ): SmallStatusContainer {
        return bind(activity) { }
    }

    @JvmStatic
    fun bind(
        activity: Activity,
        onRetryEventListener: OnRetryEventListener
    ): SmallStatusContainer {
        return bind(activity, onRetryEventListener) { }
    }

    @JvmStatic
    fun bind(
        activity: Activity,
        onRetryEventListener: OnRetryEventListener,
        onCloseEventListener: OnCloseEventListener?
    ): SmallStatusContainer {
        val targetView = activity.findViewById<ViewGroup>(android.R.id.content)
        val targetViewIndex = 0
        val oldContent: View = targetView.getChildAt(targetViewIndex)
        targetView.removeView(oldContent)
        val oldLayoutParams = oldContent.layoutParams
        val multiStateContainer =
            SmallStatusContainer(
                oldContent.context,
                oldContent,
                onRetryEventListener,
                onCloseEventListener
            )
        targetView.addView(multiStateContainer, targetViewIndex, oldLayoutParams)
        multiStateContainer.init()
        return multiStateContainer
    }
}