package com.vainoris.gildedroseservice.strategies;

public class ConjuredItemQualityStrategyImpl implements QualityStrategy
{
    private static final int QUALITY_DEGRADATION_NORMAL = 2;
    private static final int QUALITY_DEGRADATION_AFTER_SELL_IN = 4;

    @Override
    public int getQuality(int quality, int sellIn) {
        return quality - (sellIn < 0 ? QUALITY_DEGRADATION_AFTER_SELL_IN : QUALITY_DEGRADATION_NORMAL);
    }

}
