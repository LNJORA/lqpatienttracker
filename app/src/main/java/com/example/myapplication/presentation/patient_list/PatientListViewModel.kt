package com.example.myapplication.presentation.patient_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.domain.model.Patient
import com.example.myapplication.domain.repository.PatientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientListViewModel @Inject constructor(
    private val repository: PatientRepository
) : ViewModel() {

    private var patientList = MutableStateFlow<List<Patient>>(emptyList())
    val patientList = patientList.asStateFlow()

    var isLoading by mutableStateOf(false)

    init {
        viewModelScope.launch {
            isLoading = true
            repository.getAllPatients().collect{
                patientList.value = it
            }
            isLoading = false
        }
    }
    fun deletePatient(patient: Patient){
        viewModelScope.launch {
            repository.deletePatient(patient)
        }
    }

}