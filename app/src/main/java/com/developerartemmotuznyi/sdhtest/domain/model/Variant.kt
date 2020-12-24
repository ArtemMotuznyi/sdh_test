package com.developerartemmotuznyi.sdhtest.domain.model

data class Variant(
    val id: Long,
    val pharmForm: PharmForm,
    val name: String,
    val shortName: String
)