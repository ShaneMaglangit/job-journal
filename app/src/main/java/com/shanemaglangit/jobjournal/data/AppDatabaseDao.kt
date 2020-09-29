package com.shanemaglangit.jobjournal.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AppDatabaseDao {
    @Insert
    suspend fun insert(jobApplication: JobApplication)

    @Delete
    suspend fun delete(jobApplication: JobApplication)

    @Query("SELECT * FROM job_application_table")
    fun getAllJobApplication() : LiveData<List<JobApplication>>
}