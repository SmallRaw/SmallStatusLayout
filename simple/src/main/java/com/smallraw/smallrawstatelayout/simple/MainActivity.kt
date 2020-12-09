package com.smallraw.smallrawstatelayout.simple

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.smallraw.smallrawstatelayout.simple.databinding.ActivityMainBinding
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {
    private val bind by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
    }

    @Throws(IllegalArgumentException::class)
    fun onClick(view: View) {
        val clazz = when (view.id) {
            R.id.btnInjectViewStatus -> {
                ViewInjectStatusActivity::class.java
            }
            R.id.btnViewStatus -> {
                ViewStatusActivity::class.java
            }
            R.id.btnActivityStatus -> {
                ActivityInjectStatusActivity::class.java
            }
            R.id.btnFragmentStatus -> {
                InjectFragmentActivity::class.java
            }
            R.id.btnFragmentViewBindingStatus -> {
                ViewBindingInjectFragmentActivity::class.java
            }
            R.id.btnRecyclerViewStatus -> {
                ViewInjectStatusActivity::class.java
            }
            R.id.btnCustomStatus -> {
                ViewInjectStatusActivity::class.java
            }
            else -> throw IllegalArgumentException()
        }
        startActivity(Intent(this, clazz))
    }
}