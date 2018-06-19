package com.demo.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Item;
import com.demo.service.ItemService;
import com.demo.web.dto.ItemDTO;

@RestController
@RequestMapping(value = "api/items")
public class ApiItemController {

	@Autowired
	private ItemService itemService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ItemDTO>> getItems(@RequestParam(value = "lastname", required = false) String lastname) {
		List<Item> items = null;

		if (lastname == null) {
			items = itemService.findAll();
		} else {
			items = itemService.findByLastname(lastname);
		}

		List<ItemDTO> itemsDTO = new ArrayList<>();
		for (Item item : items) {
			itemsDTO.add(new ItemDTO(item));
		}

		HttpHeaders httpHeaders = new HttpHeaders();

		return new ResponseEntity<>(itemsDTO, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ItemDTO> getItem(@PathVariable Long id) {
		Item item = itemService.findOne(id);
		if (item == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new ItemDTO(item), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ItemDTO> deleteItem(@PathVariable Long id) {
		Item item = itemService.findOne(id);
		if (item == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		itemService.remove(id);

		return new ResponseEntity<>(new ItemDTO(item), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/JSON")
	public ResponseEntity<ItemDTO> saveItem(@RequestBody ItemDTO itemDTO) {
		Item item = new Item();
		item.setFirstname(itemDTO.getFirstname());
		item.setLastname(itemDTO.getLastname());
		item.setNumber(itemDTO.getNumber());

		Item itemSaved = itemService.save(item);
		return new ResponseEntity<>(new ItemDTO(itemSaved), HttpStatus.CREATED);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/JSON")
	public ResponseEntity<ItemDTO> editItem(@RequestBody ItemDTO itemDTO, @PathVariable Long id) {
		if (itemDTO.getId() == null || itemDTO.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Item item = itemService.findOne(id);
		if (item != null) {
			item.setFirstname(itemDTO.getFirstname());
			item.setLastname(itemDTO.getLastname());
			item.setNumber(itemDTO.getNumber());

			Item itemSaved = itemService.save(item);
			return new ResponseEntity<>(new ItemDTO(itemSaved), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
