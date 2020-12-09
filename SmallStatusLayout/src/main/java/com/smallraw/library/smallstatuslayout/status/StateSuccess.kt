package com.smallraw.library.smallstatuslayout.status

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.smallraw.library.smallstatuslayout.SmallStateConfig
import com.smallraw.library.smallstatuslayout.SmallStatusContainer
import com.smallraw.library.smallstatuslayout.StatePage

class StateSuccess : StatePage() {
    override fun onCreateStatePageView(
        context: Context,
        inflater: LayoutInflater,
        container: SmallStatusContainer
    ): View {
        return View(context)
    }

    override fun onConfigStateView(view: View, config: SmallStateConfig?) {
    }

    override fun enableReload(): Boolean {
        return false
    }
}