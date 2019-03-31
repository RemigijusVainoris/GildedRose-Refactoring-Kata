package com.vainoris.gildedroseservice.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO
{
    private String id;
    private String name;
    private Integer quality;
    private Integer sellIn;
}
