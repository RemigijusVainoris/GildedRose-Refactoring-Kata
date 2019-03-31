package com.vainoris.gildedroseservice.documents;

import com.vainoris.gildedroseservice.model.Item;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTests
{

    private static Item item;

    @Before
    public void setUp()
    {
        item = new Item(ObjectId.get().toHexString(), "foo", 1, 10);
    }

    @Test
    public void an_item_has_a_name_test()
    {
        assertEquals("foo", item.getName());
    }

    @Test
    public void an_item_has_sell_in_value_test()
    {
        assertEquals(1, item.getSellIn());
    }

    @Test
    public void an_item_has_quality_value_test()
    {
        assertEquals(10, item.getQuality());
    }
}