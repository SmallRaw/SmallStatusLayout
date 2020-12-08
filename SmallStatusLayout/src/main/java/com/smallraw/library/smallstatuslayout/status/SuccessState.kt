package com.smallraw.library.smallstatuslayout.status

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.smallraw.library.smallstatuslayout.SmallStatusContainer
import com.smallraw.library.smallstatuslayout.StatePage

class SuccessState : StatePage() {
    override fun onCreateMultiStateView(
        context: Context,
        inflater: LayoutInflater,
        container: SmallStatusContainer
    ): View {
        return View(context)
    }

    override fun onMultiStateViewCreate(view: View) {
    }

    override fun enableReload(): Boolean {
        return false
    }
}