package com.gildedrose.factories;

import com.gildedrose.strategies.QualityStrategy;

public interface QualityStrategyFactory {
    QualityStrategy getQualityStrategy(String name);
}
