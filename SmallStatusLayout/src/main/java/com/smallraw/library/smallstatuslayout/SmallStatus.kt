package com.smallraw.library.smallstatuslayout

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup

object SmallStatus {
    @JvmStatic
    fun bindTarget(targetView: View): SmallStatusContainer {
        return bindTarget(targetView) { }
    }

    @JvmStatic
    fun bindTarget(
        targetView: View,
        retryEventListener: OnRetryEventListener
    ): SmallStatusContainer {
        return bindTarget(targetView, retryEventListener, {})
    }

    @JvmStatic
    fun bindTarget(
        targetView: View,
        onRetryEventListener: OnRetryEventListener,
        onCloseEventListener: OnCloseEventListener?
    ): SmallStatusContainer {
        val parent = targetView.parent as ViewGroup?
        if (parent?.javaClass?.name == "androidx.constraintlayout.widget.ConstraintLayout"
            || parent?.javaClass?.name == "android.support.constraint.ConstraintLayout"
        ) {
            Log.w(
                "SmallStatusLayout",
                "ConstraintLayout 布局因为约束的问题，推荐使用在 XML 布局直接使用 SmallStatusLayout."
            )
        }
        var targetViewIndex = 0
        val statusContainer = SmallStatusContainer(
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
            targetViewParent.addView(statusContainer, targetViewIndex, targetView.layoutParams)
        }
        statusContainer.init()
        return statusContainer
    }

    @JvmStatic
    fun bindTarget(
        activity: Activity,
    ): SmallStatusContainer {
        return bindTarget(activity) { }
    }

    @JvmStatic
    fun bindTarget(
        activity: Activity,
        onRetryEventListener: OnRetryEventListener
    ): SmallStatusContainer {
        return bindTarget(activity, onRetryEventListener) { }
    }

    @JvmStatic
    fun bindTarget(
        activity: Activity,
        onRetryEventListener: OnRetryEventListener,
        onCloseEventListener: OnCloseEventListener?
    ): SmallStatusContainer {
        val targetView = activity.findViewById<ViewGroup>(android.R.id.content)
        val targetViewIndex = 0
        val oldContent: View = targetView.getChildAt(targetViewIndex)
        targetView.removeView(oldContent)
        val oldLayoutParams = oldContent.layoutParams
        val stateContainer = SmallStatusContainer(
            oldContent.context,
            oldContent,
            onRetryEventListener,
            onCloseEventListener
        )
        targetView.addView(stateContainer, targetViewIndex, oldLayoutParams)
        stateContainer.init()
        return stateContainer
    }
}