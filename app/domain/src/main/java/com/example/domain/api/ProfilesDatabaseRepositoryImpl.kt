package com.example.domain.api

import com.example.data.dao.ProfileDao
import com.example.data.model.ProfileEntity

class ProfilesDatabaseRepositoryImpl(private val profileDao: ProfileDao) {
    suspend fun insertProfile(profile: ProfileEntity) {
        profileDao.insertNewProfile(profile)
    }

    suspend fun checkIfUserExist(email: String, password: String): Boolean {
        return profileDao.checkIfUserExist(email, password) != null
    }
}