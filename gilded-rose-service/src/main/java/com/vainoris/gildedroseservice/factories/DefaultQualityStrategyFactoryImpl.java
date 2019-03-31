package com.vainoris.gildedroseservice.factories;

import static com.vainoris.gildedroseservice.constants.ItemConstants.AGED_BRIE;
import static com.vainoris.gildedroseservice.constants.ItemConstants.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT;
import static com.vainoris.gildedroseservice.constants.ItemConstants.CONJURED;

import java.util.HashMap;
import java.util.Map;

import com.vainoris.gildedroseservice.strategies.AgedBrieQualityStrategyImpl;
import com.vainoris.gildedroseservice.strategies.BackstagePassQualityStrategyImpl;
import com.vainoris.gildedroseservice.strategies.ConjuredItemQualityStrategyImpl;
import com.vainoris.gildedroseservice.strategies.DefaultQualityStrategyImpl;
import com.vainoris.gildedroseservice.strategies.QualityStrategy;
import org.springframework.stereotype.Component;

@Component
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
