package com.example.acedu.feature.auth.presentation.scholarly

import com.example.acedu.core.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScholarlyViewModel @Inject constructor() : BaseViewModel<ScholarlyUiState>() {
    override fun getInitialUiState(): ScholarlyUiState = ScholarlyUiState()

    fun onSearchQueryChanged(query: String) {
        updateState { it.copy(searchQuery = query) }
    }

    fun onUniversitySelected(id: String) {
        updateState { 
            val newId = if (it.selectedUniversityId == id) null else id
            val isOther = it.universities.find { u -> u.id == id }?.isOther == true
            
            if (isOther && newId != null) {
                it.copy(selectedUniversityId = newId, isAddingUniversity = true)
            } else {
                it.copy(selectedUniversityId = newId)
            }
        }
    }

    fun onBackFromAddUniversity() {
        updateState { it.copy(isAddingUniversity = false, selectedUniversityId = null) }
    }

    fun onCustomUniversityAdded(name: String, location: String, domain: String) {
        // Here you would typically save this to a database or proceed to the next screen
        updateState { it.copy(isAddingUniversity = false) }
    }
}
