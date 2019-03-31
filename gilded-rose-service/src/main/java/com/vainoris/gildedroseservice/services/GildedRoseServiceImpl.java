package com.vainoris.gildedroseservice.services;

import java.util.List;
import java.util.concurrent.Executors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vainoris.gildedroseservice.constants.ItemConstants;
import com.vainoris.gildedroseservice.factories.QualityStrategyFactory;
import com.vainoris.gildedroseservice.model.Item;
import com.vainoris.gildedroseservice.strategies.QualityStrategy;

@Service
public class GildedRoseServiceImpl implements GildedRoseService
{
	private final QualityStrategyFactory qualityStrategyFactory;
	private final ItemService itemService;

	public GildedRoseServiceImpl(QualityStrategyFactory qualityStrategyFactory, ItemService itemService)
	{
		this.qualityStrategyFactory = qualityStrategyFactory;
		this.itemService = itemService;
	}

	@Override
	public void updateQuality()
	{
		Executors.newSingleThreadExecutor().submit(this::processQuality);
	}

	public void processQuality()
	{
		List<Item> items = itemService.getItemDocuments();

		items.stream().filter(item -> !ItemConstants.SULFURAS_HAND_OF_RAGNAROS.equalsIgnoreCase(item.getName())).forEach(item ->
		{
			item.setSellIn(item.getSellIn() - 1);

			QualityStrategy strategy = qualityStrategyFactory.getQualityStrategy(item.getName());
			item.setQuality(strategy.getQualityInRange(strategy.getQuality(item.getQuality(), item.getSellIn())));
		});

		itemService.saveItemDocuments(items);
	}
}