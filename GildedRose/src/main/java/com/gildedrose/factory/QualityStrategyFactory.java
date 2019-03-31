package com.gildedrose.factory;

import com.gildedrose.strategy.QualityStrategy;

public interface QualityStrategyFactory {
    QualityStrategy getQualityStrategy(String name);
}
