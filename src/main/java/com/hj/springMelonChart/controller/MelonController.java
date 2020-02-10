package com.hj.springMelonChart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hj.springMelonChart.model.Melon;
import com.hj.springMelonChart.service.MelonService;

@Controller
public class MelonController {

	@Autowired
	private MelonService melonService;

	@GetMapping({ "", "/" })
	public String melon(Model model) {

		List<Melon> charts = melonService.melonChart(0);
		model.addAttribute("charts",charts);
	
		return "melonChart";
	}

	@GetMapping("/api/melonchart/{num}")
	public ResponseEntity<?> Melonchart(@PathVariable int num) {
		int newNum=(num-1)*6;
		List<Melon> charts = melonService.melonChart(newNum);

		return  new ResponseEntity<List<Melon>>(charts,HttpStatus.OK);
	}
	@GetMapping("/insert")
	public String save(Model model) {

	melonService.melonSave();
	
		return "melonChart";
	}
	@GetMapping("/music/{title}")
	public String music(@PathVariable String title) {

	melonService.melonMusic(title);
	
		return "melonChart";
	}


}
