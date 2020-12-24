package com.developerartemmotuznyi.sdhtest.network.model.response

import com.developerartemmotuznyi.sdhtest.domain.model.TradeLabel
import kotlinx.serialization.SerialName

class TradeLabelDTO(
    @SerialName("id")
    val id: Long? = null,
    @SerialName("name")
    val name: String? = null
)

fun TradeLabelDTO?.toDomain() = TradeLabel(
    this?.id ?: -1,
    this?.name.orEmpty()
)
