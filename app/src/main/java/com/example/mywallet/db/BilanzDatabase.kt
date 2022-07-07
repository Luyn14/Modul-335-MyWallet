package com.example.mywallet.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mywallet.model.Activity
import com.example.mywallet.model.Wallet

@Database (entities = [Activity::class, Wallet::class], version = 6 )
abstract class BilanzDatabase : RoomDatabase() {
    abstract fun walletDao(): WalletDao

    companion object {

        @Volatile private var  instance: BilanzDatabase? = null

        fun getInstance( context: Context): BilanzDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder( context, BilanzDatabase::class.java, "bilanz" ).fallbackToDestructiveMigration().build()
            }
        }
    }
}


