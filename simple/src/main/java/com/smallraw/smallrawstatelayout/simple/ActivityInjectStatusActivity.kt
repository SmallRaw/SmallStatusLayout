package com.smallraw.smallrawstatelayout.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.smallraw.library.smallstatuslayout.SmallStatus
import com.smallraw.library.smallstatuslayout.status.StateError
import com.smallraw.library.smallstatuslayout.status.StateLoading
import com.smallraw.library.smallstatuslayout.status.StateEmpty
import com.smallraw.library.smallstatuslayout.status.StateSuccess
import com.smallraw.smallrawstatelayout.simple.databinding.ActivityInjectActivityStatusBinding
import com.smallraw.smallrawstatelayout.simple.databinding.ActivityInjectViewStatusBinding
import kotlinx.coroutines.*

class ActivityInjectStatusActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private val bind by lazy {
        ActivityInjectActivityStatusBinding.inflate(layoutInflater)
    }

    private val smallStatus by lazy {
        SmallStatus.bindTarget(this) {
            it.show<StateSuccess>()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        launch(Dispatchers.IO) {
            delay(3000)
            withContext(Dispatchers.Main) {
                smallStatus.show<StateEmpty>()
            }
            delay(3000)
            withContext(Dispatchers.Main) {
                smallStatus.show<StateLoading>()
            }
            delay(3000)
            withContext(Dispatchers.Main) {
                smallStatus.show<StateSuccess>()
            }
            delay(3000)
            withContext(Dispatchers.Main) {
                smallStatus.show<StateError>()
            }
        }
    }

}