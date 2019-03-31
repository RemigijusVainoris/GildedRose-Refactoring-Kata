package com.gildedrose.strategies;

public class DefaultQualityStrategyImpl implements QualityStrategy
{
    private static final int QUALITY_DEGRADATION_NORMAL = 1;
    private static final int QUALITY_DEGRADATION_AFTER_SELL_IN = 2;

    @Override
    public int getQuality(int quality, int sellIn) {
        return quality - (sellIn < 0 ? QUALITY_DEGRADATION_AFTER_SELL_IN : QUALITY_DEGRADATION_NORMAL);
    }
}
