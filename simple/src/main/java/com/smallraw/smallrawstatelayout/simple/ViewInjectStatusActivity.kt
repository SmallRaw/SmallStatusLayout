package com.smallraw.smallrawstatelayout.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.smallraw.library.smallstatuslayout.SmallStatus
import com.smallraw.library.smallstatuslayout.status.StateError
import com.smallraw.library.smallstatuslayout.status.StateLoading
import com.smallraw.library.smallstatuslayout.status.StateEmpty
import com.smallraw.library.smallstatuslayout.status.StateSuccess
import com.smallraw.smallrawstatelayout.simple.databinding.ActivityInjectViewStatusBinding

class ViewInjectStatusActivity : AppCompatActivity() {
    private val bind by lazy {
        ActivityInjectViewStatusBinding.inflate(layoutInflater)
    }

    private val smallStatus by lazy {
        SmallStatus.bindTarget(bind.tvTargetView, {
            it.show<StateSuccess>()
        })
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
            R.id.btnLoadingState -> {
                smallStatus.show<StateLoading>()
            }
            R.id.btnEmptyState -> {
                smallStatus.show<StateEmpty>()
            }
        }
    }
}