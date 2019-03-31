package com.vainoris.gildedroseservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vainoris.gildedroseservice.rest.responses.GetItemsResponse;
import com.vainoris.gildedroseservice.services.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemController
{
	private final ItemService itemService;

	public ItemController(ItemService itemService)
	{
		this.itemService = itemService;
	}

	@GetMapping
	public ResponseEntity<GetItemsResponse> getAllItems()
	{
		GetItemsResponse response = new GetItemsResponse();

		response.getItems().addAll(itemService.getItems());

		return ResponseEntity.ok(response);
	}
}
