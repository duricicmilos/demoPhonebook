package com.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Item;
import com.demo.repository.ItemRepository;
import com.demo.service.ItemService;

@Transactional
@Service
public class JpaItemService implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Item findOne(Long id) {
		return itemRepository.findOne(id);
	}

	@Override
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

	@Override
	public Item save(Item item) {
		return itemRepository.save(item);
	}

	@Override
	public void remove(Long id) throws IllegalArgumentException {
		Item item = itemRepository.findOne(id);
		if (item == null) {
			throw new IllegalArgumentException("Tried to remove nonexistant item id:" + id);
		}
		itemRepository.delete(item);
	}

	@Override
	public List<Item> findByLastname(String string) {
		return itemRepository.findByLastnameLike("%" + string + "%");
	}

}
