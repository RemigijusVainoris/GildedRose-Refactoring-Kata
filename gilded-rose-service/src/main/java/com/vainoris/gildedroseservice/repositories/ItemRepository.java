package com.vainoris.gildedroseservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vainoris.gildedroseservice.model.Item;

public interface ItemRepository extends MongoRepository<Item, String>
{
}
