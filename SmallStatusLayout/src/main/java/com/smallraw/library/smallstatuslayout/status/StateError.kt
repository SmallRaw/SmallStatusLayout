package com.smallraw.library.smallstatuslayout.status

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.smallraw.library.smallstatuslayout.*

class StateError : StatePage() {
    private lateinit var tvRetry: TextView

    override fun onCreateStatePageView(
        context: Context,
        inflater: LayoutInflater,
        container: SmallStatusContainer
    ): View {
        return inflater.inflate(R.layout.small_state_error, container, false)
    }

    override fun onConfigStateView(view: View, config: SmallStateConfig?) {
        val tvErrorMsg = view.findViewById<TextView>(R.id.tv_error_msg)
        val imgError = view.findViewById<ImageView>(R.id.img_error)
        tvRetry = view.findViewById(R.id.tv_retry)
        (config ?: SmallStatus.globalConfig).apply {
            tvErrorMsg.text = errorMsg
            imgError.setImageResource(errorIcon)
            tvRetry.text = retryMsg
        }
    }

    override fun enableReload() = true

    override fun bindRetryView() = tvRetry
}