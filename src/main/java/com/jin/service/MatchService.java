package com.jin.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jin.domain.Match;
import com.jin.domain.Team;
import com.jin.rule.service.ComplexRuleService;

@Service
public class MatchService {
	private static final Logger logger = LoggerFactory.getLogger(MatchService.class);
	
	@Autowired
	protected ComplexRuleService complexRuleService;
	
	public void assingMatch(){
		
		Team teamA = new Team("Retriever","peewee");
		Team teamB = new Team("Grayhound","peewee");
		Match match = new Match();
		
		List<Object> collection = new ArrayList<Object>();
		collection.add(teamA);
		collection.add(teamB);
		collection.add(match);
		
		complexRuleService.getStatelessKieSession().execute(collection);
		
		logger.info("The match " +  match.getMatch());
		
	}
	
	
}
