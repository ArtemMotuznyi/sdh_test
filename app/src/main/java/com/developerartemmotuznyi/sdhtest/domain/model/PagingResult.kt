package com.developerartemmotuznyi.sdhtest.domain.model

data class PagingResult(
    val count: Long,
    val next: Int,
    val previous: Int,
    val result: List<Medicine>
)