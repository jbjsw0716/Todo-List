package com.keduit.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoVO {
	private Long tno;
	private String title;
	private String writer;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dueDate;
	private boolean finished;
}
