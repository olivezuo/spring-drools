package com.jin.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.domain.Athlete;
import com.jin.domain.Team;
import com.jin.rule.service.SimpleRuleService;


@Service
public class TeamService {

	private static final Logger logger = LoggerFactory.getLogger(TeamService.class);
	
	@Autowired 
	SimpleRuleService simpleRuleService;
	
	private Athlete athlete;
	private Team team;
	
	@PostConstruct
	public void init() {
		athlete = new Athlete();
		athlete.setAge(8);
		team = new Team();
		team.setLevel("peewee");
		logger.info("At beginning the Team has " + team.getMembers().size() + " members");
		
		assignTeam();
	}

	public void assignTeam() {
		//KieSession kieSession = kieContainer.newKieSession();
		athlete = new Athlete();
		athlete.setAge(9);

		simpleRuleService.getKieSession().insert(athlete);
		simpleRuleService.getKieSession().insert(team);
		int ruleCount = simpleRuleService.getKieSession().fireAllRules();
		logger.info("The total rules been checked: " +  ruleCount);
		logger.info("The Team has " + team.getMembers().size() + " members");
		//kieSession.getObjects();
		
		//kieSession.destroy();
		
		//statelessKieSession.

	}
	
	
}
