package com.example.myapplication.presentation.patient_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import com.example.myapplication.domain.model.Patient
import com.example.myapplication.domain.repository.PatientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel PatientDetailsViewModel @Inject constructor(
    private val repository: PatientRepository,
    private val savedStateHandle: SavedStateHandle,
    ): ViewModel(){

        var state by mutableStateOf(PatientDetailsUiState())

        private val _eventFlow = MutableSharedFlow<UiEvent>()
        val eventFlow = _eventFlow.asSharedFlow()

        private var currentPatientId: Int? = null

        init{
            fetchPatientDetails()
        }

        fun onAction(event: PatientDetailsEvent){
            when(event){
                is PatientDetailsEvent.EnteredName ->{
                    state = state.copy(name = event.name)
                }
                is PatientDetailsEvent.EnteredAge ->{
                    state = state.copy(age = event.age)
                }
                is PatientDetailsEvent.EnteredAssignedDoctor ->{
                    state = state.copy(doctorAssigned = event.doctor)
                }
                is PatientDetailsEvent.EnteredPrescription ->{
                    state = state.copy(gender = 2)
                }
                PatientDetailsEvent.SelectedMale -> {
                    state = state.copy(gender = 1)
                }
                PatientDetailsEvent.SaveButton -> {
                    viewModelScope.launch{
                        try {
                            savePatient()
                            _eventFlow.emit(UiEvent.SaveNote)
                        } catch (e: Exception) {
                            _eventFlow.emit(
                                UiEvent.ShowToast(
                                    message = e.message?: "Couldn't save patient's details."
                                )
                            )

                        }
                    }
                }
            }
        }

    private fun savePatient(){
        val age = state.age.toIntOrNull()
        when{
            state.name.isEmpty()-> throw TextFieldException("Please Enter name.")
            state.age.isEmpty()-> throw TextFieldException("Please Enter age.")
            state.gender == 0 -> throw TextFieldException("Please Select gender.")
            state.doctorAssigned.isEmpty() -> throw TextFieldException("Please enter doctor assigned.")
            state.prescription.isEmpty() -> throw TextFieldException("Please enter doctor a valid prescription.")
            age == null || age < 0 || age > 100 ->throw TextFieldException("Please enter valid age.")
        }

        val trimmmedName = state.name.trim()
        val trimmedDoctorName = state.doctorAssigned.trim()
        viewModelScope.launch{
            repository.addOrUpdatePatient(
                patient = Patient(
                    name = trimmmedName,
                    age = state.age,
                    gender = state.gender,
                    doctorsAssigned = trimmedDoctorName,
                    prescription = state.prescription,
                    patieentId = currentPatientId
                )

                    )
        }


    }

    private fun fetchPatientDetails(){
        savedStateHandle.get<Int>(key = PATIENT_DETAILS_ARGUMENTS_KEY)?.let { patientId ->
            if (patientId != -1) {
                viewModelScope.launch{
                        repository.getPatientById(patientId)?.apply {
                            state = state.copy(
                                name = name,
                                age = age,
                                gender = gender,
                                doctorsAssigned = doctorsAssigned
                                prescription = prescription
                            )
                            currentPatientId = patientId
                        }
            }
            }
        }


    sealed class UiEvent {
        data class ShowToast(val message: String):UiEvent()
        object SaveNote : UiEvent()

    }    }
class TextFieldException(message: String)