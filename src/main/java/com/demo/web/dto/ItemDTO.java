package com.demo.web.dto;

import com.demo.model.Item;

public class ItemDTO {

	private Long id;

	private String firstname;

	private String lastname;

	private String number;
	
	public ItemDTO() {
		super();
	}

	public ItemDTO(Long id, String firstname, String lastname, String number) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.number = number;
	}

	public ItemDTO(Item item) {
		super();
		this.id = item.getId();
		this.firstname = item.getFirstname();
		this.lastname = item.getLastname();
		this.number = item.getNumber();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
}
