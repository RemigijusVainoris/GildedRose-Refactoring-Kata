package com.vainoris.gildedroseservice.services;

import static com.vainoris.gildedroseservice.constants.ItemConstants.AGED_BRIE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.vainoris.gildedroseservice.mappers.ItemDTOMapper;
import com.vainoris.gildedroseservice.model.Item;
import com.vainoris.gildedroseservice.repositories.ItemRepository;
import com.vainoris.gildedroseservice.rest.dto.ItemDTO;

public class ItemServiceTests
{
	@Mock
	private ItemRepository itemRepository;

	@Mock
	private ItemDTOMapper itemDTOMapper;

	private ItemService itemService;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		itemService = new ItemServiceImpl(itemRepository, itemDTOMapper);
	}

	@Test
	public void get_all_items_test()
	{
		Item item = new Item(ObjectId.get().toHexString(), AGED_BRIE, 10, 10);

		List<Item> items = new ArrayList<>();

		items.add(item);

		when(itemRepository.findAll()).thenReturn(items);

		Set<ItemDTO> itemSet = itemService.getItems();

		assertEquals(1, itemSet.size());
	}
}
