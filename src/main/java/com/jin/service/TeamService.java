package com.jin.service;

import javax.annotation.PostConstruct;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.domain.Athlete;
import com.jin.domain.Team;


@Service
public class TeamService {

	private static final Logger logger = LoggerFactory.getLogger(TeamService.class);
	
	@Autowired 
	KieSession kieSession;
	
	@Autowired
	StatelessKieSession statelessKieSession;
	
	//@Autowired
	//KieContainer kieContainer;
	
	private Athlete athlete;
	private Team team;
	
	@PostConstruct
	public void init() {
		athlete = new Athlete();
		athlete.setAge(8);
		team = new Team();
		team.setName("peewee");
		logger.info("At beginning the Team has " + team.getMembers().size() + " members");
		
		assignTeam();
	}

	public void assignTeam() {
		//KieSession kieSession = kieContainer.newKieSession();
		//athlete = new Athlete();
		athlete.setAge(9);

		kieSession.insert(athlete);
		kieSession.insert(team);
		int ruleCount = kieSession.fireAllRules();
		logger.info("The total rules been checked: " +  ruleCount);
		logger.info("The Team has " + team.getMembers().size() + " members");
		//kieSession.getObjects();
		
		//kieSession.destroy();
		
		//statelessKieSession.

	}
	
	
}
