package com.vainoris.gildedroseservice.rest.responses;

import java.util.HashSet;
import java.util.Set;

import com.vainoris.gildedroseservice.rest.dto.ItemDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetItemsResponse
{
	Set<ItemDTO> items = new HashSet<>();
}
