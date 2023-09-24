package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Patient
import kotlinx.coroutines.flow.Flow

interface PatientRepository{
    suspend fun addOrUpdatePatient(patient: Patient)

    suspend fun deletePatient(patient: Patient)

    suspend fun getPatientById(patientId: Int): Patient?

    fun getAllPatients(): Flow<List<Patient>>

}