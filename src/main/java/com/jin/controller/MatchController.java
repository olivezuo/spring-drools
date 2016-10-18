package com.jin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jin.service.MatchService;

@RestController
public class MatchController {
	
	@Autowired
	MatchService matchService;
	
	@RequestMapping("/match/add")
	public void assignMatch(){
		matchService.assingMatch();
	}

}
