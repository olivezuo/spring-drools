package com.jin.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.jin.domain.Athlete;
import com.jin.domain.Team;

public class MatchService {

	@Autowired
	public KieContainer kieContainer;
	
	private Athlete athlete;
	private Team team;
	
	public void findGameForAthlete(){
		
		KieSession kieSession = kieContainer.newKieSession("MatchSession");
		kieSession.insert(athlete);
		kieSession.insert(team);
	}
	
	public void init() {
		
	}
	
}
