package com.koatee.conjugator;

public enum Tense {
	PRESENT 		("Présent", false),
	PRESENT_PERFECT	("Passé composé", true),
	PAST_IMPERFECT	("Imparfait", false),
	FUTURE			("Futur simple", false),
	UNKNOWN			("Inconnu", false);
	
	private final String frenchName;
	private final boolean compound;
	
	Tense(String frenchName, boolean compound){
		this.frenchName = frenchName;
		this.compound = compound;
	}

	public String getFrenchName() {
		return frenchName;
	}

	public boolean isCompound() {
		return compound;
	}
}
