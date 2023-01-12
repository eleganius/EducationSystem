package com.example.app.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Material {

	private Integer id;

	@NotBlank
	@Size(max = 30)
	private String name;

	@Size(max = 30)
	private String publisher;

	@Size(max = 100)
	private String note;

	private MaterialType materialType;

	private Date created;

	private String status;

}
