package com.jin.rule.service;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SimpleRuleService extends RuleService {

	private static final Logger logger = LoggerFactory.getLogger(SimpleRuleService.class);
	@PostConstruct
	@Override
	public void init() throws IOException {
		this.setRulePackage("com/jin/simple");
		this.initKie();
		this.kieSession.setGlobal("logger", logger);
	}

	
}
