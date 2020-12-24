package com.developerartemmotuznyi.sdhtest.network.api

import com.developerartemmotuznyi.sdhtest.core.model.ActionResult


interface BaseApi

suspend inline fun <T : BaseApi, DataType> T.execute(
    crossinline block: suspend T.() -> DataType
): ActionResult<DataType> = try {
    ActionResult.Success(block())
} catch (e: Exception) {
    ActionResult.Error(e)
}