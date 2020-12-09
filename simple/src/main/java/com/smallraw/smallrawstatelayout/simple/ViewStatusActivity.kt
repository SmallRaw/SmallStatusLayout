package com.smallraw.smallrawstatelayout.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.smallraw.library.smallstatuslayout.SmallStatus
import com.smallraw.library.smallstatuslayout.status.StateError
import com.smallraw.library.smallstatuslayout.status.StateLoading
import com.smallraw.library.smallstatuslayout.status.StateEmpty
import com.smallraw.library.smallstatuslayout.status.StateSuccess
import com.smallraw.smallrawstatelayout.simple.databinding.ActivityViewStatusBinding

class ViewStatusActivity : AppCompatActivity() {
    private val bind by lazy {
        ActivityViewStatusBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        bind.layoutStatus.setOnRetryEventListener {
            it.show<StateSuccess>()
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnSuccessState -> {
                bind.layoutStatus.show<StateSuccess>()
            }
            R.id.btnErrorState -> {
                bind.layoutStatus.show<StateError>()
            }
            R.id.btnLoadingState -> {
                bind.layoutStatus.show<StateLoading>()
            }
            R.id.btnEmptyState -> {
                bind.layoutStatus.show<StateEmpty>()
            }
        }
    }
}