package com.vainoris.gildedroseservice.controllers;

import static com.vainoris.gildedroseservice.constants.ItemConstants.AGED_BRIE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vainoris.gildedroseservice.repositories.ItemRepository;
import com.vainoris.gildedroseservice.rest.dto.ItemDTO;
import com.vainoris.gildedroseservice.rest.responses.GetItemsResponse;
import com.vainoris.gildedroseservice.services.ItemService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ItemController.class, secure = false)
public class ItemControllerTests
{
	private static GetItemsResponse getItemsResponse;
	private static Set<ItemDTO> items;

	static
	{
		ItemDTO item = new ItemDTO();
		item.setId(ObjectId.get().toHexString());
		item.setName(AGED_BRIE);
		item.setQuality(10);
		item.setSellIn(10);

		items = new HashSet<>();
		items.add(item);

		getItemsResponse = new GetItemsResponse();
		getItemsResponse.setItems(items);
	}

	private Gson gson = new GsonBuilder().create();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ItemRepository itemRepository;

	@MockBean
	private ItemService itemService;

	@Test
	public void get_all_items_test() throws Exception
	{
		when(itemService.getItems()).thenReturn(items);

		mockMvc.perform(get("/api/items")).andExpect(status().isOk()).andExpect(content().json(gson.toJson(getItemsResponse)));
	}
}
