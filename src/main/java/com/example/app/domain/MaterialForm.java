package com.example.app.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class MaterialForm {

	@NotBlank
	@Size(max = 30)
	private String name;

	@Size(max = 30)
	private String publisher;

	@Size(max = 100)
	private String note;

	private Integer materialTypeId;

}
