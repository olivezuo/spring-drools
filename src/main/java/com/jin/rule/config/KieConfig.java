package com.jin.rule.config;

import java.io.IOException;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.event.process.DefaultProcessEventListener;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.jin.rule.event.TrackingAgendaEventListener;


@Configuration
public class KieConfig {

	private static final Logger logger = LoggerFactory.getLogger(KieConfig.class);
	private static final String RULES_BASE = "rules/";
	
	@Bean
	public TrackingAgendaEventListener trackingAgendaEventListener() {
		return new TrackingAgendaEventListener();
	}
	
	//@Bean
	public KieFileSystem kieFileSystem(String rulePackage) throws IOException {
	    KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
	    for (Resource file : getRuleFiles(rulePackage)) {
	        kieFileSystem.write(ResourceFactory.newFileResource(file.getFile()));
	    }        
	    return kieFileSystem;
	}

	private Resource[] getRuleFiles(String rulePackage) throws IOException {
	    ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
	    return resourcePatternResolver.getResources("classpath*:" + RULES_BASE + rulePackage + "/**/*.*");
	}

	//@Bean
	public KieContainer kieContainer(String rulePackage) throws IOException {
	    final KieRepository kieRepository = getKieServices().getRepository();

	    kieRepository.addKieModule(new KieModule() {
	        public ReleaseId getReleaseId() {
	            return kieRepository.getDefaultReleaseId();
	        }
	    });

	    KieBuilder kieBuilder = getKieServices().newKieBuilder(kieFileSystem(rulePackage)); 
	    kieBuilder.buildAll();

	    return getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());
	}

	private KieServices getKieServices() {
	    return KieServices.Factory.get();
	}

	//@Bean
	public KieBase kieBase(String rulePackage) throws IOException {
	    return kieContainer(rulePackage).getKieBase();
	}

	//@Bean
	public KieSession kieSession(String rulePackage) throws IOException {
	   KieSession kieSession = kieContainer(rulePackage).newKieSession();
	   if (logger.isDebugEnabled()) {
		   //kieSession.addEventListener(trackingAgendaEventListener());
		   kieSession.addEventListener(new DebugAgendaEventListener());
		   kieSession.addEventListener(new DebugRuleRuntimeEventListener());
		   kieSession.addEventListener(new DefaultProcessEventListener());
	   }
	   
	   return kieSession;
	}

	//@Bean
	public StatelessKieSession statelessKieSession(String rulePackage) throws IOException {
		StatelessKieSession statelessKieSession = kieContainer(rulePackage).newStatelessKieSession();
		if (logger.isDebugEnabled()) {
			statelessKieSession.addEventListener(new DebugAgendaEventListener());
			statelessKieSession.addEventListener(new DebugRuleRuntimeEventListener());
			statelessKieSession.addEventListener(new DefaultProcessEventListener());
		}
		
		return statelessKieSession;
	}

	
	@Bean
	public KModuleBeanFactoryPostProcessor kiePostProcessor() {
	    return new KModuleBeanFactoryPostProcessor();
	}}
