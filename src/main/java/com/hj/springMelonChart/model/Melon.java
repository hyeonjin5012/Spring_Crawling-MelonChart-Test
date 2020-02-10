package com.hj.springMelonChart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Melon {
	private int ranking;
	private String title;
	private String singer;
	private String photo;
	private String musicLink;
}
