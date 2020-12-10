package com.smallraw.library.smallstatuslayout.status

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.smallraw.library.smallstatuslayout.*

class StateError : StatePage() {
    lateinit var tvErrorMsg: TextView
    lateinit var imgError: ImageView
    lateinit var tvRetry: TextView

    override fun onCreateStatePageView(
        context: Context,
        inflater: LayoutInflater,
        container: SmallStatusContainer
    ): View {
        return inflater.inflate(R.layout.small_state_error, container, false)
    }

    override fun onConfigStateView(view: View, config: SmallStateConfig?) {
        tvErrorMsg = view.findViewById(R.id.tv_error_msg)
        imgError = view.findViewById(R.id.img_error)
        tvRetry = view.findViewById(R.id.tv_retry)
        (config ?: SmallStatus.globalConfig).apply {
            tvErrorMsg.setText(errorMsg)
            imgError.setImageResource(errorIcon)
            tvRetry.setText(retryMsg)
        }
    }

    override fun enableReload() = true

    override fun bindRetryView() = tvRetry
}