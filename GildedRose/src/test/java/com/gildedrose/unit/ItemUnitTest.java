package com.gildedrose.unit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.gildedrose.Item;

public class ItemUnitTest
{

	private static Item item;

	@Before
	public void setUp()
	{
		item = new Item("foo", 1, 10);
	}

	@Test
	public void an_item_has_a_name_test()
	{
		assertEquals("foo", item.name);
	}

	@Test
	public void an_item_has_sell_in_value_test()
	{
		assertEquals(1, item.sellIn);
	}

	@Test
	public void an_item_has_quality_value_test()
	{
		assertEquals(10, item.quality);
	}
}
