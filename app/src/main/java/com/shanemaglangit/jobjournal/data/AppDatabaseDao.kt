package com.shanemaglangit.jobjournal.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import java.util.*

@Dao
interface AppDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jobApplication: JobApplication)

    @Delete
    suspend fun delete(jobApplication: JobApplication)

    @Query("SELECT * FROM job_application_table ORDER BY dateModified DESC")
    fun getAllJobApplication(): LiveData<List<JobApplication>>

    @Query("SELECT * FROM job_application_table WHERE id = :id")
    suspend fun getJobApplicationById(id: Long) : JobApplication

    @Query("SELECT company, applicationDate, statusChanges FROM job_application_table")
    fun getApplicationActions() : List<ApplicationActions>
}