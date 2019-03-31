package com.gildedrose.strategies;

public class BackstagePassQualityStrategyImpl implements QualityStrategy
{
	private static final int QUALITY_DEGRADATION_NORMAL = 1;
	private static final int QUALITY_DEGRADATION_AFTER_FIRST_INTERVAL = 2;
	private static final int QUALITY_DEGRADATION_AFTER_SECOND_INTERVAL = 3;

	private static final int FIRST_INTERVAL = 10;
	private static final int SECOND_INTERVAL = 5;

	@Override
	public int getQuality(int quality, int sellIn)
	{
	    if (sellIn < 0)
            return 0;

		if (sellIn <= SECOND_INTERVAL)
		    return quality + QUALITY_DEGRADATION_AFTER_SECOND_INTERVAL;

		if (sellIn <= FIRST_INTERVAL)
		    return quality + QUALITY_DEGRADATION_AFTER_FIRST_INTERVAL;

		return quality + QUALITY_DEGRADATION_NORMAL;
	}

}
