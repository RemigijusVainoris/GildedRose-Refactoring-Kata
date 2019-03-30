package com.gildedrose;

public class GildedRose
{
	Item[] items;

	public GildedRose(Item[] items)
	{
		this.items = items;
	}

	public void updateQuality()
	{
		for (int i = 0; i < items.length; i++)
		{
			if (!items[i].name.equals(ItemConstants.AGED_BRIE)
					&& !items[i].name.equals(ItemConstants.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT))
			{
				if (items[i].quality > 0)
				{
					if (!items[i].name.equals(ItemConstants.SULFURAS_HAND_OF_RAGNAROS))
					{
						if (items[i].name.startsWith(ItemConstants.CONJURED))
						{
							items[i].quality = items[i].quality > 2 ? items[i].quality - 2 : 0;
						}
						else
						{
							items[i].quality = items[i].quality - 1;
						}
					}

				}
			}
			else
			{
				// Aged brie and backstage passes

				if (items[i].quality < 50)
				{
					items[i].quality = items[i].quality + 1;

					if (items[i].name.equals(ItemConstants.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT))
					{
						if (items[i].sellIn < 11)
						{
							if (items[i].quality < 50)
							{
								items[i].quality = items[i].quality + 1;
							}
						}

						if (items[i].sellIn < 6)
						{
							if (items[i].quality < 50)
							{
								items[i].quality = items[i].quality + 1;
							}
						}
					}
				}
			}

			if (!items[i].name.equals(ItemConstants.SULFURAS_HAND_OF_RAGNAROS))
			{
				items[i].sellIn = items[i].sellIn - 1;
			}

			if (items[i].sellIn < 0)
			{
				if (!items[i].name.equals(ItemConstants.AGED_BRIE))
				{
					if (!items[i].name.equals(ItemConstants.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT))
					{
						if (items[i].quality > 0)
						{
							if (!items[i].name.equals(ItemConstants.SULFURAS_HAND_OF_RAGNAROS))
							{
								if (items[i].name.startsWith(ItemConstants.CONJURED))
								{
                                    items[i].quality = items[i].quality > 2 ? items[i].quality - 2 : 0;
								}
								else
								{
									items[i].quality = items[i].quality - 1;
								}
							}
						}
					}
					else
					{
						items[i].quality = items[i].quality - items[i].quality;
					}
				}
				else
				{
					if (items[i].quality < 50)
					{
						items[i].quality = items[i].quality + 1;
					}
				}
			}
		}
	}
}