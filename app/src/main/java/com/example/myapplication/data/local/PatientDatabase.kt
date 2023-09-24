package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Patient::class],
    version = 1
)
abstract class PatientDatabase: RoomDatabase(){

    abstract val patientDao:PatientDao

}