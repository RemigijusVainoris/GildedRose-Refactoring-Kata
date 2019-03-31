package com.gildedrose.functional;

import static com.gildedrose.constants.ItemConstants.AGED_BRIE;
import static com.gildedrose.constants.ItemConstants.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT;
import static com.gildedrose.constants.ItemConstants.SULFURAS_HAND_OF_RAGNAROS;
import static com.gildedrose.constants.QualityConstants.MAX_QUALITY_LEGENDARY;
import static org.junit.Assert.assertEquals;

import com.gildedrose.factory.DefaultQualityStrategyFactoryImpl;
import org.junit.Before;
import org.junit.Test;

import com.gildedrose.GildedRose;
import com.gildedrose.Item;

public class UpdateQualityFunctionalTest
{
	private GildedRose gildedRose;
	private Item[] items;

	@Before
	public void setUp()
	{
		items = new Item[]
			{
					new Item("+5 Dexterity Vest", 10, 20), // [0]
					new Item(AGED_BRIE, 2, 0), // [1]
					new Item("Elixir of the Mongoose", 5, 7), // [2]
					new Item(SULFURAS_HAND_OF_RAGNAROS, 0, MAX_QUALITY_LEGENDARY), // [3]
					new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 15, 40), // [4]
					new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 10, 40), // [5]
					new Item(BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 5, 40), // [6]
					new Item("Conjured Mana Cake", 3, 6), // [7]
					new Item("Conjured Health Potion", 1, 17) // [8]
			};

		gildedRose = new GildedRose(items, new DefaultQualityStrategyFactoryImpl());
	}

	@Test
	public void the_quality_of_items_lowers_at_the_end_of_each_day_test()
	{
		gildedRose.processQuality();

		assertEquals(19, items[0].quality);
		assertEquals(6, items[2].quality);
	}

	@Test
	public void the_quality_of_an_item_cannot_be_negative_test()
	{
		for (int i = 0; i < 30; i++)
			gildedRose.processQuality();

		assertEquals(0, items[0].quality);
	}

	@Test
	public void the_quality_of_an_item_should_never_be_more_than_fifty_test()
	{
		// With the exception of Sulfuras, Hand of Ragnaros, which can go up to 80
		for (int i = 0; i < 60; i++)
			gildedRose.processQuality();

		assertEquals(50, items[1].quality);
	}

	@Test
	public void the_quality_degrades_twice_as_fast_for_out_of_date_items_test()
	{
		for (int i = 0; i < 10; i++)
			gildedRose.processQuality();

		assertEquals(10, items[0].quality);

		gildedRose.processQuality();

		assertEquals(8, items[0].quality);
	}

	@Test
	public void aged_brie_quality_increases_as_it_gets_older_test()
	{
		gildedRose.processQuality();

		assertEquals(1, items[1].quality);
	}

	@Test
	public void sulfuras_should_not_degrade_its_quality_test()
	{
		gildedRose.processQuality();

		assertEquals(80, items[3].quality);
	}

	@Test
	public void sulfuras_should_not_degrade_its_sell_in_value_test()
	{
		gildedRose.processQuality();

		assertEquals(0, items[3].sellIn);
	}

	@Test
	public void backstage_passes_increase_in_quality_as_sell_in_date_approaches_test()
	{
		gildedRose.processQuality();

		assertEquals(41, items[4].quality);
		assertEquals(42, items[5].quality);
		assertEquals(43, items[6].quality);
	}

	@Test
	public void conjured_item_quality_degrades_twice_as_fast_test()
	{
		gildedRose.processQuality();

		assertEquals(4, items[7].quality);
		assertEquals(15, items[8].quality);

		gildedRose.processQuality();

		assertEquals(2, items[7].quality);
		assertEquals(11, items[8].quality);
	}

	@Test
	public void conjured_item_quality_should_not_be_negative_test()
	{
		for (int i = 0; i < 10; i++)
			gildedRose.processQuality();

		assertEquals(0, items[8].quality);
	}
}
