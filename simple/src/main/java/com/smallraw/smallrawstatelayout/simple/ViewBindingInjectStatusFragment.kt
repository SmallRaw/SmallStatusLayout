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
import com.smallraw.smallrawstatelayout.simple.databinding.FragmentInjectFragmentStatusBinding
import kotlinx.coroutines.*

class ViewBindingInjectStatusFragment : Fragment(), CoroutineScope by MainScope() {
    private lateinit var mBind: FragmentInjectFragmentStatusBinding
    private lateinit var mSmallStatus: SmallStatusContainer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBind = FragmentInjectFragmentStatusBinding.inflate(inflater, container, false)
        mSmallStatus = SmallStatus.bindTarget(mBind.root) {
            it.show<StateSuccess>()
        }
        return mSmallStatus
    }

    private fun getSmallStatus(): SmallStatusContainer {
        return mSmallStatus
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