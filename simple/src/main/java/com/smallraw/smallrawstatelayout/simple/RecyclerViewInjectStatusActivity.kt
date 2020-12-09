package com.smallraw.smallrawstatelayout.simple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.smallraw.library.smallstatuslayout.SmallStatus
import com.smallraw.library.smallstatuslayout.status.StateError
import com.smallraw.library.smallstatuslayout.status.StateLoading
import com.smallraw.library.smallstatuslayout.status.StateEmpty
import com.smallraw.library.smallstatuslayout.status.StateSuccess
import com.smallraw.smallrawstatelayout.simple.databinding.ActivityInjectRecyclerViewStatusBinding
import com.smallraw.smallrawstatelayout.simple.databinding.ActivityInjectViewStatusBinding

class RecyclerViewInjectStatusActivity : AppCompatActivity() {
    private val bind by lazy {
        ActivityInjectRecyclerViewStatusBinding.inflate(layoutInflater)
    }

    private val smallStatus by lazy {
        SmallStatus.bindTarget(bind.recyclerTargetView) {
            it.show<StateSuccess>()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)

        val toList = (0..40).map { "$it" }.toMutableList()
        bind.recyclerTargetView.adapter = MyAdapter(toList)
    }

    class MyAdapter(private val dataList: List<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView = view.findViewById(android.R.id.text1)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val inflate = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.activity_list_item, parent, false)
            return MyViewHolder(inflate)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.textView.text = dataList[position]
        }

        override fun getItemCount() = dataList.size

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