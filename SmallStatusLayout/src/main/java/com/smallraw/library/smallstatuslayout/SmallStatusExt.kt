package com.smallraw.library.smallstatuslayout

import android.app.Activity
import android.view.View

fun View.bindSmallState(
    onRetryEventListener: OnRetryEventListener = OnRetryEventListener { },
    onCloseEventListener: OnCloseEventListener = OnCloseEventListener { }
) = SmallStatus.bindTarget(this, onRetryEventListener, onCloseEventListener)

fun Activity.bindSmallState(
    onRetryEventListener: OnRetryEventListener = OnRetryEventListener { },
    onCloseEventListener: OnCloseEventListener = OnCloseEventListener { }
) = SmallStatus.bindTarget(this, onRetryEventListener, onCloseEventListener)