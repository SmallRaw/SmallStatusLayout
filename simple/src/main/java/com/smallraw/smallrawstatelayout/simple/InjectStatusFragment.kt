package com.smallraw.smallrawstatelayout.simple

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smallraw.library.smallstatuslayout.SmallStatus
import com.smallraw.library.smallstatuslayout.SmallStatusContainer
import com.smallraw.library.smallstatuslayout.status.StateEmpty
import com.smallraw.library.smallstatuslayout.status.StateError
import com.smallraw.library.smallstatuslayout.status.StateLoading
import com.smallraw.library.smallstatuslayout.status.StateSuccess
import kotlinx.coroutines.*

class InjectStatusFragment : Fragment(), CoroutineScope by MainScope() {
    private lateinit var mRootView: SmallStatusContainer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inflate = inflater.inflate(R.layout.fragment_inject_fragment_status, container, false)
        mRootView = SmallStatus.bindTarget(inflate) {
            it.show<StateSuccess>()
        }
        return mRootView
    }

    private fun getSmallStatus(): SmallStatusContainer {
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch(Dispatchers.IO) {
            delay(3000)
            withContext(Dispatchers.Main) {
                getSmallStatus().show<StateEmpty>()
            }
            delay(3000)
            withContext(Dispatchers.Main) {
                getSmallStatus().show<StateLoading>()
            }
            delay(3000)
            withContext(Dispatchers.Main) {
                getSmallStatus().show<StateSuccess>()
            }
            delay(3000)
            withContext(Dispatchers.Main) {
                getSmallStatus().show<StateError>()
            }
        }
    }
}