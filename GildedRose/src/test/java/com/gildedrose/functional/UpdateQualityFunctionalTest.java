package com.gildedrose.functional;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.gildedrose.GildedRose;
import com.gildedrose.Item;

public class UpdateQualityFunctionalTest
{
    private static GildedRose gildedRose;
    private static Item[] items;
    @Before
    public void setUp()
    {
       items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), // [0]
                new Item("Aged Brie", 2, 0), // [1]
                new Item("Elixir of the Mongoose", 5, 7), // [2]
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), // [3]
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 40), // [4]
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 40), // [5]
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 40), // [6]
                //new Item("Conjured Mana Cake", 3, 6) // [7]
       };

        gildedRose = new GildedRose(items);
    }

    @Test
    public void the_quality_of_items_lowers_at_the_end_of_each_day_test()
    {
        gildedRose.updateQuality();

        assertEquals(19, items[0].quality);
        assertEquals(6, items[2].quality);
    }

	@Test
	public void the_quality_of_an_item_cannot_be_negative_test()
	{
	    for(int i = 0; i < 30; i++)
            gildedRose.updateQuality();

        assertEquals(0, items[0].quality);
	}

	@Test
    public void the_quality_of_an_item_should_never_be_more_than_fifty_test()
    {
        // With the exception of Sulfuras, Hand of Ragnaros, which can go up to 80
        for(int i = 0; i < 60; i++)
            gildedRose.updateQuality();

        assertEquals(50, items[1].quality);
    }

    @Test
    public void the_quality_degrades_twice_as_fast_for_out_of_date_items_test()
    {
        for(int i = 0; i < 10; i++)
            gildedRose.updateQuality();

        assertEquals(10, items[0].quality);

        gildedRose.updateQuality();

        assertEquals(8, items[0].quality);
    }

    @Test
    public void aged_brie_quality_increases_as_it_gets_older_test()
    {
        gildedRose.updateQuality();

        assertEquals(1, items[1].quality);
    }

	@Test
	public void sulfuras_should_not_degrade_its_quality_test()
	{
		gildedRose.updateQuality();

		assertEquals(80, items[3].quality);
	}

	@Test
	public void sulfuras_should_not_degrade_its_sell_in_value_test()
	{
		gildedRose.updateQuality();

		assertEquals(0, items[3].sellIn);
	}

	@Test
    public void backstage_passes_increase_in_quality_as_sell_in_date_approaches_test()
    {
        gildedRose.updateQuality();

        assertEquals(41, items[4].quality);
        assertEquals(42, items[5].quality);
        assertEquals(43, items[6].quality);
    }
}
