package com.example.myapplication.di

import android.app.Application
import androidx.room.Room
import com.example.myapplication.data.local.PatientDatabase
import com.example.myapplication.data.repository.PatientRepositoryImpl
import com.example.myapplication.domain.repository.PatientRepository
import com.example.myapplication.util.Constants.PATIENT_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providePatientDatabase(
        app: Application
    ): PatientDatabase {
        return Room.databaseBuilder(
            app,
            PatientDatabase::class.java,
            PATIENT_DATABASE
        ).build()
    }

    @Provides
    @Singleton
    fun providesPatientRepository(
        db: PatientDatabase
    ): PatientRepository {
        return PatientRepositoryImpl(db.patientDao)
    }
}