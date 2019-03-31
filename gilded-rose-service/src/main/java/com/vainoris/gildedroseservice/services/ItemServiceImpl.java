package com.vainoris.gildedroseservice.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.vainoris.gildedroseservice.mappers.Mapper;
import com.vainoris.gildedroseservice.model.Item;
import com.vainoris.gildedroseservice.repositories.ItemRepository;
import com.vainoris.gildedroseservice.rest.dto.ItemDTO;

@Service
public class ItemServiceImpl implements ItemService
{
	private final ItemRepository itemRepository;
	private final Mapper<Item, ItemDTO> itemDTOMapper;

	public ItemServiceImpl(ItemRepository itemRepository, Mapper<Item, ItemDTO> itemDTOMapper)
	{
		this.itemRepository = itemRepository;
		this.itemDTOMapper = itemDTOMapper;
	}

	@Override
	public Set<ItemDTO> getItems()
	{
		Set<ItemDTO> items = new HashSet<>();

		getItemDocuments().forEach(item -> items.add(itemDTOMapper.toDTO(item)));

		return items;
	}

	@Override
	public List<Item> saveItemDocuments(List<Item> items)
	{
		return itemRepository.saveAll(items);
	}

	@Override
	public List<Item> getItemDocuments()
	{
		return itemRepository.findAll();
	}

}
