package com.shanemaglangit.jobjournal.data

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface AppDatabaseDao {
    @Insert
    fun insert(jobApplication: JobApplication)
}