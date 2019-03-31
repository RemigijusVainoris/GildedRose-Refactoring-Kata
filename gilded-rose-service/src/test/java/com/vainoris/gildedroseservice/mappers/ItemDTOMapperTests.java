package com.vainoris.gildedroseservice.mappers;

import com.vainoris.gildedroseservice.model.Item;
import com.vainoris.gildedroseservice.rest.dto.ItemDTO;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;

import javax.management.ObjectName;

import static com.vainoris.gildedroseservice.constants.ItemConstants.AGED_BRIE;
import static org.junit.Assert.assertEquals;

public class ItemDTOMapperTests
{
    private Mapper<Item, ItemDTO> itemDTOMapper;

    @Before
    public void setUp()
    {
        itemDTOMapper = new ItemDTOMapper();
    }

    @Test
    public void item_to_item_dto_map_test()
    {
        Item item = new Item(ObjectId.get().toHexString(), AGED_BRIE, 10, 10);

        ItemDTO itemDTO = itemDTOMapper.toDTO(item);

        assertEquals(item.getId(), itemDTO.getId());
        assertEquals(AGED_BRIE, itemDTO.getName());
        assertEquals(Integer.valueOf(10), itemDTO.getSellIn());
        assertEquals(Integer.valueOf(10), itemDTO.getQuality());
    }

    @Test
    public void item_dto_to_item_map_test()
    {
        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setId(ObjectId.get().toHexString());
        itemDTO.setName(AGED_BRIE);
        itemDTO.setQuality(10);
        itemDTO.setSellIn(10);

        Item item = itemDTOMapper.fromDTO(itemDTO);

        assertEquals(itemDTO.getId(), item.getId());
        assertEquals(AGED_BRIE, item.getName());
        assertEquals(10, item.getSellIn());
        assertEquals(10, item.getQuality());
    }
}
