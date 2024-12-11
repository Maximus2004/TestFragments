package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.model.ProfileEntity

@Dao
interface ProfileDao {
    @Insert
    suspend fun insertNewProfile(profileEntity: ProfileEntity)

    @Query("SELECT * FROM profiles WHERE email = :email AND password = :password LIMIT 1")
    suspend fun checkIfUserExist(email: String, password: String): ProfileEntity?
}