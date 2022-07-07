package com.example.mywallet.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Entity
import com.example.mywallet.db.BilanzDatabase
import com.example.mywallet.model.Activity
import com.example.mywallet.model.Wallet
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WalletViewModel(application: Application) : AndroidViewModel(application) {

    private var walletDao = BilanzDatabase.getInstance(application.applicationContext).walletDao()

    var liveDataActivity: MutableLiveData<Activity> = MutableLiveData()

    var liveDataWallet: MutableLiveData<Wallet> = MutableLiveData()



    fun setActivity(activity: Activity?) {

        if (activity != null) {
            liveDataActivity.value = activity
        } else {
            var activity = Activity(0, "", 0)

            liveDataActivity.value = activity
        }
    }

    fun setWallet(wallet: Wallet?) {

        if (wallet != null) {
            liveDataWallet.value = wallet
        } else {
            var wallet = Wallet(0, 0)

            liveDataWallet.value = wallet
            CoroutineScope(Dispatchers.IO).launch {
            walletDao.insertWallet(liveDataWallet.value!!)
            }
        }
        setWalletValue()
    }


    fun onSaveActivityClick() {
        CoroutineScope(Dispatchers.IO).launch {

            if (liveDataActivity.value!!.id.compareTo(0) == 0) {
                walletDao.insertActivity(liveDataActivity.value!!)
            }

        }
    }
        fun setActivityName(name: String) {
            this.liveDataActivity.value?.name = name
        }

        fun setActivityValue(value: Long) {
            this.liveDataActivity.value?.value = value
        }


    fun setWalletValue() {

        CoroutineScope(Dispatchers.IO).launch {

            val activities = walletDao.getAll()

            val wallet = liveDataWallet.value!!


            var walletValue: Long = 0
            for (item in activities) {
                walletValue += item.value
            }
            wallet.value = walletValue
            liveDataWallet.postValue(wallet)
        }

    }



}



