package com.jin.rule.service;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ComplexRuleService extends RuleService {

	private static final Logger logger = LoggerFactory.getLogger(ComplexRuleService.class);
	@PostConstruct
	@Override
	public void init() throws IOException {
		this.setRulePackage("com/jin/complex");
		//this.initKie();
		this.initStatelessKie();
		this.statelessKieSession.setGlobal("logger", logger);
	}

}
