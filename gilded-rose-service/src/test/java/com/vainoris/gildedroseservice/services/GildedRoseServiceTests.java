package com.vainoris.gildedroseservice.services;

import static com.vainoris.gildedroseservice.constants.ItemConstants.AGED_BRIE;
import static com.vainoris.gildedroseservice.constants.ItemConstants.BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT;
import static com.vainoris.gildedroseservice.constants.ItemConstants.SULFURAS_HAND_OF_RAGNAROS;
import static com.vainoris.gildedroseservice.constants.QualityConstants.MAX_QUALITY_LEGENDARY;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vainoris.gildedroseservice.factories.DefaultQualityStrategyFactoryImpl;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vainoris.gildedroseservice.factories.QualityStrategyFactory;
import com.vainoris.gildedroseservice.model.Item;

public class GildedRoseServiceTests
{
	@Mock
	private ItemServiceImpl itemService;

	private GildedRoseService gildedRoseService;

	private List<Item> items;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

        QualityStrategyFactory qualityStrategyFactory = new DefaultQualityStrategyFactoryImpl();
		gildedRoseService = new GildedRoseServiceImpl(qualityStrategyFactory, itemService);

		items = new ArrayList<>(Arrays.asList(
				new Item(ObjectId.get().toHexString(), "+5 Dexterity Vest", 10, 20), // [0]
				new Item(ObjectId.get().toHexString(), AGED_BRIE, 2, 0), // [1]
				new Item(ObjectId.get().toHexString(), "Elixir of the Mongoose", 5, 7), // [2]
				new Item(ObjectId.get().toHexString(), SULFURAS_HAND_OF_RAGNAROS, 0, MAX_QUALITY_LEGENDARY), // [3]
				new Item(ObjectId.get().toHexString(), BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 15, 40), // [4]
				new Item(ObjectId.get().toHexString(), BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 10, 40), // [5]
				new Item(ObjectId.get().toHexString(), BACKSTAGE_PASSES_TO_A_TAFKAL80ETC_CONCERT, 5, 40), // [6]
				new Item(ObjectId.get().toHexString(), "Conjured Mana Cake", 3, 6), // [7]
				new Item(ObjectId.get().toHexString(), "Conjured Health Potion", 1, 17) // [8]
		));

		when(itemService.getItemDocuments()).thenReturn(items);
	}

	@Test
	public void the_quality_of_items_lowers_at_the_end_of_each_day_test()
	{
		gildedRoseService.processQuality();

		assertEquals(19, items.get(0).getQuality());
		assertEquals(6, items.get(2).getQuality());
	}

	@Test
	public void the_quality_of_an_item_cannot_be_negative_test()
	{
		for (int i = 0; i < 30; i++)
			gildedRoseService.processQuality();

		assertEquals(0, items.get(0).getQuality());
	}

	@Test
	public void the_quality_of_an_item_should_never_be_more_than_fifty_test()
	{
		// With the exception of Sulfuras, Hand of Ragnaros, which can go up to 80
		for (int i = 0; i < 60; i++)
			gildedRoseService.processQuality();

		assertEquals(50, items.get(1).getQuality());
	}

	@Test
	public void the_quality_degrades_twice_as_fast_for_out_of_date_items_test()
	{
		for (int i = 0; i < 10; i++)
			gildedRoseService.processQuality();

		assertEquals(10, items.get(0).getQuality());

		gildedRoseService.processQuality();

		assertEquals(8, items.get(0).getQuality());
	}

	@Test
	public void aged_brie_quality_increases_as_it_gets_older_test()
	{
		gildedRoseService.processQuality();

		assertEquals(1, items.get(1).getQuality());
	}

	@Test
	public void sulfuras_should_not_degrade_its_quality_test()
	{
		gildedRoseService.processQuality();

		assertEquals(80, items.get(3).getQuality());
	}

	@Test
	public void sulfuras_should_not_degrade_its_sell_in_value_test()
	{
		gildedRoseService.processQuality();

		assertEquals(0, items.get(3).getSellIn());
	}

	@Test
	public void backstage_passes_increase_in_quality_as_sell_in_date_approaches_test()
	{
		gildedRoseService.processQuality();

		assertEquals(41, items.get(4).getQuality());
		assertEquals(42, items.get(5).getQuality());
		assertEquals(43, items.get(6).getQuality());
	}

	@Test
	public void conjured_item_quality_degrades_twice_as_fast_test()
	{
		gildedRoseService.processQuality();

		assertEquals(4, items.get(7).getQuality());
		assertEquals(15, items.get(8).getQuality());

		gildedRoseService.processQuality();

		assertEquals(2, items.get(7).getQuality());
		assertEquals(11, items.get(8).getQuality());
	}

	@Test
	public void conjured_item_quality_should_not_be_negative_test()
	{
		for (int i = 0; i < 10; i++)
			gildedRoseService.processQuality();

		assertEquals(0, items.get(8).getQuality());
	}

}