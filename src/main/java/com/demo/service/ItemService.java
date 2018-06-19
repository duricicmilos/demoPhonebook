package com.demo.service;

import java.util.List;

import com.demo.model.Item;

public interface ItemService {

	Item findOne(Long id);

	List<Item> findAll();

	Item save(Item item);

	void remove(Long id) throws IllegalArgumentException;

	List<Item> findByLastname(String string);

}
