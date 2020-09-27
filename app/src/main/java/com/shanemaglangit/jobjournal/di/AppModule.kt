package com.shanemaglangit.jobjournal.di

import android.content.Context
import com.shanemaglangit.jobjournal.data.AppDatabase
import com.shanemaglangit.jobjournal.data.AppDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object HiltModule {
    @Provides
    @Singleton
    fun provideDatabaseDao(@ApplicationContext context: Context) : AppDatabaseDao {
        return AppDatabase.getInstance(context).appDatabaseDao()
    }
}