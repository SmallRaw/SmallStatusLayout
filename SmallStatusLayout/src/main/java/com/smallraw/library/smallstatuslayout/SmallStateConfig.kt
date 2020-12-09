package com.smallraw.library.smallstatuslayout

import androidx.annotation.DrawableRes

data class SmallStateConfig(
    val errorMsg: String = "哎呀,出错了",
    @DrawableRes
    val errorIcon: Int = R.drawable.ic_small_state_error,
    val emptyMsg: String = "这里什么都没有",
    @DrawableRes
    val emptyIcon: Int = R.drawable.ic_small_state_empty,
    val retryMsg: String = "点击重试",
    val loadingMsg: String = "loading...",
    var alphaDuration: Long = 500
) {

    class Builder {
        private var errorMsg: String = "加载出错了"

        @DrawableRes
        private var errorIcon: Int = R.drawable.ic_small_state_error
        private var emptyMsg: String = "没有数据"

        private var retryMsg: String = "点击重试"

        @DrawableRes
        private var emptyIcon: Int = R.drawable.ic_small_state_empty
        private var loadingMsg: String = "loading..."
        private var alphaDuration: Long = 400

        fun errorMsg(msg: String): Builder {
            this.errorMsg = msg
            return this
        }

        fun errorIcon(@DrawableRes icon: Int): Builder {
            this.errorIcon = icon
            return this
        }

        fun emptyMsg(msg: String): Builder {
            this.emptyMsg = msg
            return this
        }

        fun emptyIcon(@DrawableRes icon: Int): Builder {
            this.emptyIcon = icon
            return this
        }

        fun retryMsg(msg: String): Builder {
            this.retryMsg = msg
            return this
        }

        fun loadingMsg(msg: String): Builder {
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