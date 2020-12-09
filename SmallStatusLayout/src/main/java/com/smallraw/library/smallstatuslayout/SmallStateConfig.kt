package com.smallraw.library.smallstatuslayout

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class SmallStateConfig(
    @StringRes
    val errorMsg: Int = R.string.msg_small_status_error,
    @DrawableRes
    val errorIcon: Int = R.drawable.ic_small_state_error,
    @StringRes
    val emptyMsg: Int = R.string.msg_small_status_empty,
    @DrawableRes
    val emptyIcon: Int = R.drawable.ic_small_state_empty,
    @StringRes
    val retryMsg: Int = R.string.msg_small_status_retry,
    @StringRes
    val loadingMsg: Int = R.string.msg_small_status_loading,
    var alphaDuration: Long = 500
) {

    class Builder {
        @StringRes
        private var errorMsg: Int = R.string.msg_small_status_error

        @DrawableRes
        private var errorIcon: Int = R.drawable.ic_small_state_error

        @StringRes
        private var emptyMsg: Int = R.string.msg_small_status_empty

        @StringRes
        private var retryMsg: Int = R.string.msg_small_status_retry

        @DrawableRes
        private var emptyIcon: Int = R.drawable.ic_small_state_empty

        @StringRes
        private var loadingMsg: Int = R.string.msg_small_status_loading
        private var alphaDuration: Long = 400

        fun errorMsg(@StringRes msg: Int): Builder {
            this.errorMsg = msg
            return this
        }

        fun errorIcon(@DrawableRes icon: Int): Builder {
            this.errorIcon = icon
            return this
        }

        fun emptyMsg(@StringRes msg: Int): Builder {
            this.emptyMsg = msg
            return this
        }

        fun emptyIcon(@DrawableRes icon: Int): Builder {
            this.emptyIcon = icon
            return this
        }

        fun retryMsg(@StringRes msg: Int): Builder {
            this.retryMsg = msg
            return this
        }

        fun loadingMsg(@StringRes msg: Int): Builder {
            this.loadingMsg = msg
            return this
        }

        fun alphaDuration(duration: Long): Builder {
            this.alphaDuration = duration
            return this
        }

        fun build() = SmallStateConfig(
            errorMsg = errorMsg,
            errorIcon = errorIcon,
            emptyMsg = emptyMsg,
            emptyIcon = emptyIcon,
            retryMsg = retryMsg,
            loadingMsg = loadingMsg,
            alphaDuration = alphaDuration
        )
    }
}