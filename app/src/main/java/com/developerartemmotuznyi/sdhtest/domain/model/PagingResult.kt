package com.developerartemmotuznyi.sdhtest.domain.model

class PagingResult<T>(
    val count: Long,
    val next: String,
    val previous: String,
    val result: List<T>
)