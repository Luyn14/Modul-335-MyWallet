package com.example.mywallet.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.mywallet.db.BilanzDatabase
import com.example.mywallet.model.Activity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditActivityViewModel(application : Application) : AndroidViewModel( application ) {

    private var walletDao = BilanzDatabase.getInstance(application.applicationContext ).walletDao()

    var liveDataActivity: MutableLiveData<Activity> = MutableLiveData()

    fun setActivity(activity: Activity?) {

        if (liveDataActivity != null) {

            liveDataActivity.value = activity

        }
    }

        fun onSaveActivityClick() {
            CoroutineScope(Dispatchers.IO).launch {

                    walletDao.updateActivity(liveDataActivity.value!!)

            }
        }

        fun onDeleteQuestionClicker() {
            CoroutineScope(Dispatchers.IO).launch {
                walletDao.deleteActivity(liveDataActivity.value!!)
            }
        }

        fun setActivityName(name: String) {
            this.liveDataActivity.value?.name = name
        }

        fun setActivityValue(value: Long) {
            this.liveDataActivity.value?.value = value
        }


    }




