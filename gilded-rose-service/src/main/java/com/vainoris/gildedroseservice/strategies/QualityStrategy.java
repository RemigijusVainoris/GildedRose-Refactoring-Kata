package com.vainoris.gildedroseservice.strategies;

import static com.vainoris.gildedroseservice.constants.QualityConstants.MAX_QUALITY;
import static com.vainoris.gildedroseservice.constants.QualityConstants.MIN_QUALITY;

public interface QualityStrategy
{
	int getQuality(int quality, int sellIn);

	default int getQualityInRange(int quality)
	{
		if (quality > MAX_QUALITY)
			return MAX_QUALITY;

		if (quality < MIN_QUALITY)
			return MIN_QUALITY;

		return quality;
	}
}
