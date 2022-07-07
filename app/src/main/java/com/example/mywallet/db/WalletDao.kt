package com.example.mywallet.db

import androidx.room.*
import com.example.mywallet.model.Activity
import com.example.mywallet.model.Wallet

@Dao
interface WalletDao {

    @Query("Select * From activity")
    fun getAll(): List<Activity>

    @Insert
    fun insertActivity( activity: Activity)

    @Update
    fun updateActivity( activity: Activity)

    @Delete
    fun deleteActivity( activity: Activity)

    @Insert
    fun insertWallet( wallet: Wallet)

    @Update
    fun updateWallet( wallet: Wallet)

    @Delete
    fun deleteWallet( wallet: Wallet)

    @Query( "DELETE FROM activity")
    fun deleteAllActivitys()


}