package com.jin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jin.service.TeamService;

@RestController
public class TeamController {

	@Autowired
	TeamService teamService;
	
	@RequestMapping("/team/add")
	public void addTeam() {
		teamService.assignTeam();
	}
}
