package com.vainoris.gildedroseservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Item
{

	@Id
	private String id;

	private String name;
	private int sellIn;
	private int quality;

	public Item(String id, String name, int sellIn, int quality)
	{
		this.id = id;
		this.name = name;
		this.sellIn = sellIn;
		this.quality = quality;
	}

	@Override
	public String toString()
	{
		return this.name + ", " + this.sellIn + ", " + this.quality;
	}
}
