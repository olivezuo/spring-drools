package com.jin.rule.service;

import java.io.IOException;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.jin.rule.config.KieConfig;

public abstract class RuleService {
		
	@Autowired
	protected KieConfig kieConfig;
	
	protected String rulePackage;
	
	protected KieSession kieSession;
	
	protected StatelessKieSession statelessKieSession;

	public abstract void init() throws IOException;

	protected void initKie() throws IOException{
		kieSession = kieConfig.kieSession(rulePackage);
		
	}
	
	protected void initStatelessKie() throws IOException{
		statelessKieSession = kieConfig.statelessKieSession(rulePackage);
		
	}

		
	public String getRulePackage() {
		return rulePackage;
	}

	public void setRulePackage(String rulePackage) {
		this.rulePackage = rulePackage;
	}

	public KieSession getKieSession() {
		return kieSession;
	}
	
	public StatelessKieSession getStatelessKieSession() {
		return statelessKieSession;
	}	

}
