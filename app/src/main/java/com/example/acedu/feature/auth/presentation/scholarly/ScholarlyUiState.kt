package com.example.acedu.feature.auth.presentation.scholarly

data class ScholarlyUiState(
    val isLoading: Boolean = false,
    val searchQuery: String = "",
    val selectedUniversityId: String? = null,
    val universities: List<University> = initialUniversities,
    val isAddingUniversity: Boolean = false
)

data class University(
    val id: String,
    val name: String,
    val logoUrl: String? = null,
    val isOther: Boolean = false
)

val initialUniversities = listOf(
    University("1", "Oxford University"),
    University("2", "Stanford University"),
    University("3", "MIT"),
    University("4", "Harvard College"),
    University("5", "Yale University"),
    University("6", "Other / Private", isOther = true)
)
