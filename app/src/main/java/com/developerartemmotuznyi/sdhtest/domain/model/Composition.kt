package com.developerartemmotuznyi.sdhtest.domain.model

data class Composition(
    val id: Long,
    val description: String,
    val atc: List<String>,
    val inn: InternationalNonproprietaryName,
    val pharmForm: PharmForm,
    val dosage: Long,
    val measure: Measure
)