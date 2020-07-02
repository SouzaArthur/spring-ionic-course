package br.com.ultracodeultracodejpa.dto;

import java.io.Serializable;

import br.com.ultracodeultracodejpa.domain.Category;

public class CategoryDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	public CategoryDTO(Category obj) {
		id = obj.getId();
		name = obj.getName();
	}
	
	public CategoryDTO() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
