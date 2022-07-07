package com.example.mywallet.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mywallet.db.BilanzDatabase
import com.example.mywallet.model.Activity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivityListViewModel(application : Application) : AndroidViewModel( application ) {

    private var walletDao = BilanzDatabase.getInstance(application.applicationContext ).walletDao()

    var liveDataActivity: MutableLiveData<List<Activity>> = MutableLiveData()

    fun getActivity() : LiveData<List<Activity>> {

        if ( liveDataActivity.value == null ) {
            CoroutineScope(Dispatchers.IO).launch{
                liveDataActivity.postValue( walletDao.getAll() )
            }
        }

        return liveDataActivity
    }
}
