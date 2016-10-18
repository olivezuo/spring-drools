package com.jin.domain;

public class Match {
	
	private Team teamA;
	private Team teamB;
	
	public Team getTeamA() {
		return teamA;
	}
	public void setTeamA(Team teamA) {
		this.teamA = teamA;
	}

	public Team getTeamB() {
		return teamB;
	}
	
	public void setTeamB(Team teamB) {
		this.teamB = teamB;
	}
	
	public String getMatch() {
		if ((null != teamA) && (null != teamB)){
			return "We have a match for team " + teamA.getName() +" and team " + teamB.getName();
		}
		return null;
	}

	
	
}
