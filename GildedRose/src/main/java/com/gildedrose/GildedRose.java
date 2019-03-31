package com.gildedrose;

import java.util.Arrays;
import java.util.concurrent.Executors;

import com.gildedrose.constants.ItemConstants;
import com.gildedrose.factory.QualityStrategyFactory;
import com.gildedrose.strategy.QualityStrategy;

public class GildedRose
{
	private final QualityStrategyFactory qualityStrategyFactory;
	Item[] items;

	public GildedRose(Item[] items, QualityStrategyFactory qualityStrategyFactory)
	{
		this.items = items;
		this.qualityStrategyFactory = qualityStrategyFactory;
	}

	public void updateQuality()
	{
        Executors.newSingleThreadExecutor().submit(this::processQuality);
	}

	public void processQuality()
    {
        Arrays.stream(items)
                .filter(item -> !ItemConstants.SULFURAS_HAND_OF_RAGNAROS.equalsIgnoreCase(item.name))
                .forEach(item -> {
                    item.sellIn--;

                    QualityStrategy strategy = qualityStrategyFactory.getQualityStrategy(item.name);
                    item.quality = strategy.getQualityInRange(strategy.getQuality(item.quality, item.sellIn));
                });
    }
}