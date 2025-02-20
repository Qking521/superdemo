package com.king.superdemo.activities

import android.annotation.SuppressLint
import android.app.StatusBarManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.king.superdemo.R
import com.king.superdemo.fragments.AppLeftFragment
import com.king.superdemo.fragments.AppLeftFragment.CallBack
import com.king.superdemo.fragments.AppRightFragment

class AppManagerActivity : BaseActivity() {

    lateinit var leftFragment: AppLeftFragment
    lateinit var rightFragment: AppRightFragment

    private lateinit var sharedViewModel: AppSharedViewModel

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_manager_layout)
        leftFragment = supportFragmentManager.findFragmentById(R.id.app_left_fragment) as AppLeftFragment
        rightFragment = supportFragmentManager.findFragmentById(R.id.app_right_fragment) as AppRightFragment
        leftFragment.setOnItemClickListener {position : Int -> update(position) }
        val statusBarManager: StatusBarManager = getSystemService(Context.STATUS_BAR_SERVICE) as StatusBarManager
        sharedViewModel = ViewModelProvider(this).get(AppSharedViewModel::class.java)

    }

    private fun update(position: Int) {
        val bundle = Bundle()
        bundle.putInt("position", position)
        rightFragment!!.arguments = bundle
        Log.i("wq", "onItemClick: position=$position")
    }

    class AppSharedViewModel: ViewModel() {
        private val _data = MutableLiveData<Pair<String, Int>>()
        val data: LiveData<Pair<String, Int>> = _data

        fun updateData(newData: Pair<String, Int>) {
            _data.value = newData
        }
    }
}