package com.vainoris.gildedroseservice.services;

import java.util.List;
import java.util.Set;

import com.vainoris.gildedroseservice.model.Item;
import com.vainoris.gildedroseservice.rest.dto.ItemDTO;

public interface ItemService
{
	Set<ItemDTO> getItems();

	List<Item> saveItemDocuments(List<Item> items);

	List<Item> getItemDocuments();
}
