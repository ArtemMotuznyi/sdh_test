package com.developerartemmotuznyi.sdhtest.local.datastore.model

import com.developerartemmotuznyi.sdhtest.TradeLabelPreferences
import com.developerartemmotuznyi.sdhtest.domain.model.TradeLabel

fun TradeLabelPreferences.toDomain() = TradeLabel(
		this.id,
		this.name
)

fun TradeLabelPreferences.Builder.parse(tradeLabel: TradeLabel): TradeLabelPreferences = with(TradeLabelPreferences.newBuilder()) {
	id = tradeLabel.id
	name = tradeLabel.name
	build()
}