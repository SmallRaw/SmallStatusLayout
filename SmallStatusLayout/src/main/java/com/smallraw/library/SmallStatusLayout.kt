package com.smallraw.library

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import com.smallraw.library.smallstatuslayout.*
import com.smallraw.library.smallstatuslayout.status.StateSuccess

class SmallStatusLayout : FrameLayout {
    private var mOriginalView: View? = null
    private var mOnRetryEventListener: OnRetryEventListener? = null
    private var mOnCloseEventListener: OnCloseEventListener? = null
    private var mConfig: SmallStateConfig? = null

    constructor(
        context: Context,
        originalView: View,
        onRetryEventListener: OnRetryEventListener? = null,
        onCloseEventListener: OnCloseEventListener? = null,
        config: SmallStateConfig? = null
    ) : super(context) {
        mOriginalView = originalView
        mOnRetryEventListener = onRetryEventListener
        mOnCloseEventListener = onCloseEventListener
        mConfig = config
    }

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, @AttrRes defStyleAttr: Int = 0) :
            super(context, attrs, defStyleAttr)

    private var mStatePool: MutableMap<Class<out StatePage>, StatePage> = mutableMapOf()

    private var animator = ValueAnimator.ofFloat(0.0f, 1.0f).apply {
        duration = mConfig?.alphaDuration ?: SmallStatus.globalConfig.alphaDuration
    }

    fun init() {
        addView(mOriginalView, 0)
    }

    fun setOnRetryEventListener(onRetryEventListener: OnRetryEventListener) {
        mOnRetryEventListener = onRetryEventListener
    }

    fun setOnCloseEventListener(onCloseEventListener: OnCloseEventListener) {
        mOnCloseEventListener = onCloseEventListener
    }

    fun setConfig(config: SmallStateConfig) {
        mConfig = config
    }

    inline fun <reified T : StatePage> show(noinline notify: (T) -> Unit = {}) {
        show(T::class.java, false, notify)
    }

    inline fun <reified T : StatePage> show(
        forcedRefresh: Boolean,
        noinline notify: (T) -> Unit = {}
    ) {
        show(T::class.java, forcedRefresh, notify)
    }

    fun <T : StatePage> show(clazz: Class<T>, forcedRefresh: Boolean = false) {
        show(clazz, forcedRefresh, onNotifyListener = { })
    }

    fun <T : StatePage> show(
        clazz: Class<T>,
        onNotifyListener: OnNotifyListener<T> = OnNotifyListener { }
    ) {
        show(clazz, false, onNotifyListener)
    }

    @Throws(IllegalArgumentException::class)
    fun <T : StatePage> show(
        clazz: Class<T>,
        forcedRefresh: Boolean = false,
        onNotifyListener: OnNotifyListener<T> = OnNotifyListener { }
    ) {
        findStatePage(clazz)?.let { statePage ->
            if (mOriginalView == null) {
                if (childCount != 1) {
                    throw IllegalArgumentException("SmallStatusLayout can only have one child View")
                }
                mOriginalView = getChildAt(0)
            }
            if (childCount == 0) {
                addView(mOriginalView, 0)
            }
            if (childCount > 1) {
                removeViewAt(1)
            }
            if (statePage is StateSuccess) {
                if (mOriginalView?.visibility == View.VISIBLE) {
                    return
                }
                mOriginalView?.visibility = View.VISIBLE
                mOriginalView?.doAnimator()
                val targetViewLayoutParams = mOriginalView?.layoutParams
                if (targetViewLayoutParams is MarginLayoutParams) {
                    targetViewLayoutParams.setMargins(0, 0, 0, 0)
                    mOriginalView?.layoutParams = targetViewLayoutParams
                }
            } else {
                mOriginalView?.visibility = View.GONE
                val currentStateView =
                    statePage.createStatePageView(context, LayoutInflater.from(context), this)
                statePage.configStateView(currentStateView, mConfig, forcedRefresh)
                val retryView = statePage.bindRetryView()
                if (statePage.enableReload()) {
                    if (retryView != null) {
                        retryView.setOnClickListener { mOnRetryEventListener?.onRetryEvent(this) }
                    } else {
                        currentStateView.setOnClickListener {
                            mOnRetryEventListener?.onRetryEvent(
                                this
                            )
                        }
                    }
                }
                val closeView = statePage.bindCloseView()
                closeView?.setOnClickListener { mOnCloseEventListener?.onCloseEvent(this) }

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