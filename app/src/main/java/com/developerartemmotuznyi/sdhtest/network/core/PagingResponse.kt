package com.developerartemmotuznyi.sdhtest.network.core

class PagingResponse<T>(
    val count: Long,
    val next: String,
    val previous: String,
    val result: List<T>
)