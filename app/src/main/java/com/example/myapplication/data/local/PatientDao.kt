package com.example.myapplication.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PatientDao{

    @Upsert
    suspend fun addOrUpdatePatient(patient:Patient)

    @Query("SELECT * FROM patient_table WHERE patientId = :patientId")
     suspend fun getPatientById(patientId: Int): Patient?

     @Query("SELECT * FROM patient_table")
     fun getAllPatient(): Flow<List<Patient>>

}