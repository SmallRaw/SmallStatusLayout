package com.smallraw.library.smallstatuslayout

fun interface OnNotifyListener<T> {
    fun onNotify(statePage: T)
}