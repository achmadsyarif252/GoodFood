package com.example.goodfood.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.goodfood.domain.model.MyWallet
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPayment(listWallet: List<MyWallet>)

    @Query("SELECT * FROM table_wallet ORDER BY id ASC")
    fun getAllWallet(): Flow<List<MyWallet>>

    @Query("SELECT COUNT(*) FROM table_wallet")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myWallet: MyWallet)

    @Update
    suspend fun update(myWallet: MyWallet)

    @Delete
    suspend fun delete(myWallet: MyWallet)
}