package com.smallraw.library.smallstatuslayout.status

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.smallraw.library.smallstatuslayout.R
import com.smallraw.library.smallstatuslayout.SmallStatusContainer
import com.smallraw.library.smallstatuslayout.StatePage

class StateError : StatePage() {

    private lateinit var tvErrorMsg: TextView
    private lateinit var imgError: ImageView
    private lateinit var tvRetry: TextView

    override fun onCreateStatePageView(
        context: Context,
        inflater: LayoutInflater,
        container: SmallStatusContainer
    ): View {
        return inflater.inflate(R.layout.small_state_error, container, false)
    }

    override fun onStateViewCreate(view: View) {
        tvErrorMsg = view.findViewById(R.id.tv_error_msg)
        imgError = view.findViewById(R.id.img_error)
        tvRetry = view.findViewById(R.id.tv_retry)
    }

    override fun enableReload() = true

    override fun bindRetryView() = tvRetry
}