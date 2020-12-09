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

class InjectFragmentActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
    }

}