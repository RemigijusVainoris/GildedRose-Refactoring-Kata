package com.vainoris.gildedroseservice.factories;

import com.vainoris.gildedroseservice.strategies.QualityStrategy;

public interface QualityStrategyFactory {
    QualityStrategy getQualityStrategy(String name);
}
