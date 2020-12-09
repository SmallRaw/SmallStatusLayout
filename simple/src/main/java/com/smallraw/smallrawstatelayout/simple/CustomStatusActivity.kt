package com.smallraw.smallrawstatelayout.simple

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import com.smallraw.library.smallstatuslayout.SmallStateConfig
import com.smallraw.library.smallstatuslayout.SmallStatus
import com.smallraw.library.smallstatuslayout.SmallStatusContainer
import com.smallraw.library.smallstatuslayout.StatePage
import com.smallraw.library.smallstatuslayout.status.StateError
import com.smallraw.library.smallstatuslayout.status.StateLoading
import com.smallraw.library.smallstatuslayout.status.StateEmpty
import com.smallraw.library.smallstatuslayout.status.StateSuccess
import com.smallraw.smallrawstatelayout.simple.databinding.ActivityCustomViewStatusBinding
import com.smallraw.smallrawstatelayout.simple.databinding.ActivityInjectViewStatusBinding

class StateCustom : StatePage() {

    override fun onCreateStatePageView(
        context: Context,
        inflater: LayoutInflater,
        container: SmallStatusContainer
    ): View {
        return inflater.inflate(R.layout.small_state_custom, container, false)
    }

    override fun onConfigStateView(view: View, config: SmallStateConfig?) {
    }

    override fun enableReload(): Boolean = false
}

class StateCustom1 : StatePage() {
    lateinit var tvCustomMsg: TextView
    override fun onCreateStatePageView(
        context: Context,
        inflater: LayoutInflater,
        container: SmallStatusContainer
    ): View {
        return inflater.inflate(R.layout.small_state_custom, container, false)
    }

    override fun onConfigStateView(view: View, config: SmallStateConfig?) {
        tvCustomMsg = view.findViewById(R.id.tv_custom_msg)
    }

    override fun enableReload(): Boolean = false
}

class CustomStatusActivity : AppCompatActivity() {
    private val bind by lazy {
        ActivityCustomViewStatusBinding.inflate(layoutInflater)
    }

    private val smallStatus by lazy {
        SmallStatus.bindTarget(bind.tvTargetView) {
            it.show<StateSuccess>()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnSuccessState -> {
                smallStatus.show<StateSuccess>()
            }
            R.id.btnErrorState -> {
                smallStatus.show<StateError>()
            }
            R.id.btnCustomState -> {
                // 可以自定义状态布局
                smallStatus.show<StateCustom>()
            }
            R.id.btnCustomState1 -> {
                // 可以动态切换自定义状态布局的文字和元素
                smallStatus.show<StateCustom1> {
                    it.tvCustomMsg.text = "StateCustom Text1"
                }
            }
            R.id.btnCustomState2 -> {
                // 可以动态切换自定义状态布局的文字和元素
                smallStatus.show<StateCustom1> {
                    it.tvCustomMsg.text = "StateCustom Text2"
                }
            }
        }
    }
}