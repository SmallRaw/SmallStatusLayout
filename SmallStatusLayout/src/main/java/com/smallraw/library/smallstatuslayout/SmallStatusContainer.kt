package com.smallraw.library.smallstatuslayout

import android.animation.ValueAnimator
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.smallraw.library.smallstatuslayout.status.SuccessState

class SmallStatusContainer constructor(
    context: Context,
    private val originalView: View,
    private val onRetryEventListener: OnRetryEventListener,
    private val onCloseEventListener: OnCloseEventListener? = null,
    config: SmallStatusContainerConfig? = null,
) : FrameLayout(context) {
    private var mStatePool: MutableMap<Class<out StatePage>, StatePage> = mutableMapOf()

    private var animator = ValueAnimator.ofFloat(0.0f, 1.0f).apply {
        duration = config?.animatorDuration ?: 600
    }

    fun init() {
        addView(originalView, 0)
    }

    inline fun <reified T : StatePage> show(noinline notify: (T) -> Unit = {}) {
        show(T::class.java, notify)
    }

    fun <T : StatePage> show(clazz: Class<T>) {
        show(clazz, onNotifyListener = { })
    }

    fun <T : StatePage> show(
        clazz: Class<T>,
        onNotifyListener: OnNotifyListener<T> = OnNotifyListener { }
    ) {
        findStatePage(clazz)?.let { statePage ->
            if (childCount == 0) {
                addView(originalView, 0)
            }
            if (childCount > 1) {
                removeViewAt(1)
            }
            if (statePage is SuccessState) {
                originalView.visibility = View.VISIBLE
                originalView.doAnimator()
                val targetViewLayoutParams = originalView.layoutParams
                if (targetViewLayoutParams is MarginLayoutParams) {
                    targetViewLayoutParams.setMargins(0, 0, 0, 0)
                    originalView.layoutParams = targetViewLayoutParams
                }
            } else {
                originalView.visibility = View.GONE
                val currentStateView =
                    statePage.onCreateMultiStateView(context, LayoutInflater.from(context), this)
                statePage.onMultiStateViewCreate(currentStateView)
                val retryView = statePage.bindRetryView()
                if (statePage.enableReload()) {
                    if (retryView != null) {
                        retryView.setOnClickListener { onRetryEventListener.onRetryEvent(this) }
                    } else {
                        currentStateView.setOnClickListener { onRetryEventListener.onRetryEvent(this) }
                    }
                }
                val closeView = statePage.bindCloseView()
                if (closeView != null && onCloseEventListener != null) {
                    closeView.setOnClickListener { onCloseEventListener.onCloseEvent(this) }
                }

                addView(currentStateView)
                currentStateView.doAnimator()
                onNotifyListener.onNotify(statePage as T)
            }
        }
    }

    private fun <T : StatePage> findStatePage(clazz: Class<T>): StatePage? {
        return if (mStatePool.containsKey(clazz)) {
            mStatePool[clazz]
        } else {
            val state = clazz.newInstance()
            mStatePool[clazz] = state
            state
        }
    }

    private fun View.doAnimator() {
        this.clearAnimation()
        animator.addUpdateListener {
            this.alpha = it.animatedValue as Float
        }
        animator.start()
    }
}

