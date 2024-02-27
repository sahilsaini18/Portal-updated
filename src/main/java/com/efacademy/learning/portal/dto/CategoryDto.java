package com.efacademy.learning.portal.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CategoryDto {

	@NotNull(message = "Category type cannot be null")
	private String categoryType;
}
