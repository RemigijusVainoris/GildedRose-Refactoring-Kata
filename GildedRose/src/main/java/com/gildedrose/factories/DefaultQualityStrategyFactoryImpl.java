package com.gildedrose.factories;

import java.util.HashMap;
import java.util.Map;

import com.gildedrose.strategies.AgedBrieQualityStrategyImpl;
import com.gildedrose.strategies.BackstagePassQualityStrategyImpl;
import com.gildedrose.strategies.ConjuredItemQualityStrategyImpl;
import com.gildedrose.strategies.DefaultQualityStrategyImpl;
import com.gildedrose.strategies.QualityStrategy;

import static com.gildedrose.constants.ItemConstants.AGED_BRIE;
import static com.gildedrose.constants.ItemConstants.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT;
import static com.gildedrose.constants.ItemConstants.CONJURED;

public class DefaultQualityStrategyFactoryImpl implements QualityStrategyFactory
{
    private static final String DEFAULT = "default";
	private final Map<String, QualityStrategy> qualityStrategyMap = new HashMap<>();

	public DefaultQualityStrategyFactoryImpl()
	{
	    qualityStrategyMap.put(AGED_BRIE, new AgedBrieQualityStrategyImpl());
	    qualityStrategyMap.put(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, new BackstagePassQualityStrategyImpl());
	    qualityStrategyMap.put(CONJURED, new ConjuredItemQualityStrategyImpl());
	    qualityStrategyMap.put(DEFAULT, new DefaultQualityStrategyImpl());
	}

	@Override
	public QualityStrategy getQualityStrategy(String name)
	{
	    if (name.startsWith(CONJURED))
	        return qualityStrategyMap.get(CONJURED);

		return qualityStrategyMap.containsKey(name) ? qualityStrategyMap.get(name) : qualityStrategyMap.get(DEFAULT);
	}

}
