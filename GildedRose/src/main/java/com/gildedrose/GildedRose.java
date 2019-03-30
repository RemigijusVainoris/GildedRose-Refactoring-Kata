package com.gildedrose;

import java.util.Arrays;
import java.util.concurrent.Executors;

public class GildedRose
{
	Item[] items;

	public GildedRose(Item[] items)
	{
		this.items = items;
	}

	public void updateQuality()
	{
		Arrays.stream(items).filter(item -> !ItemConstants.SULFURAS_HAND_OF_RAGNAROS.equalsIgnoreCase(item.name)).forEach(item ->
		{
			if (item.name.equals(ItemConstants.AGED_BRIE) ||
					item.name.equals(ItemConstants.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT))
			{
				increaseItemQuality(item);
			}
			else
			{
				reduceItemQuality(item);
			}

			item.sellIn--;

			if (item.sellIn < 0)
			{
				handleOutOfDateItems(item);
			}
		});
	}

	private void increaseItemQuality(Item item)
	{
		if (item.quality < 50)
		{
			item.quality++;

			if (item.name.equals(ItemConstants.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT))
			{
				if (item.sellIn < 11 && item.quality < 50)
				{
					item.quality++;
				}

				if (item.sellIn < 6 && item.quality < 50)
				{
					item.quality++;
				}
			}
		}
	}

	private void reduceItemQuality(Item item)
	{
		if (item.quality > 0)
		{
			if (item.name.startsWith(ItemConstants.CONJURED))
			{
				item.quality = item.quality > 2 ? item.quality - 2 : 0;
			}
			else
			{
				item.quality--;
			}
		}
	}

	private void handleOutOfDateItems(Item item)
	{
		if (!item.name.equals(ItemConstants.AGED_BRIE))
		{
			if (!item.name.equals(ItemConstants.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT))
			{
				reduceItemQuality(item);
			}
			else
			{
				item.quality = 0;
			}
		}
		else
		{
			if (item.quality < 50)
			{
				item.quality++;
			}
		}
	}
}