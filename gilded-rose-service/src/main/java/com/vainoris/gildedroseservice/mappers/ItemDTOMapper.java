package com.vainoris.gildedroseservice.mappers;

import org.springframework.stereotype.Component;

import com.vainoris.gildedroseservice.model.Item;
import com.vainoris.gildedroseservice.rest.dto.ItemDTO;

@Component
public class ItemDTOMapper implements Mapper<Item, ItemDTO>
{
	@Override
	public Item fromDTO(ItemDTO in)
	{
		if (in == null)
			return null;

		return new Item(in.getId(), in.getName(), in.getSellIn(), in.getQuality());
	}

	@Override
	public ItemDTO toDTO(Item in)
	{
		if (in == null)
			return null;

		final ItemDTO item = new ItemDTO();

        item.setId(in.getId());
		item.setName(in.getName());
		item.setQuality(in.getQuality());
		item.setSellIn(in.getSellIn());

		return item;
	}

}
