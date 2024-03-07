package com.example.goodfood.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val email: String,
    val password: String,
    val phoneNumber: String = "0895384252730",
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB) val image: ByteArray? = null

)