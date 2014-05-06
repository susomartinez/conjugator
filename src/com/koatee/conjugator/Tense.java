package com.koatee.conjugator;

public enum Tense {
	PRESENT_INDICATIVE	("Pr�sent", false, null),
	PRESENT_PERFECT		("Pass� compos�", true, PRESENT_INDICATIVE),
	PAST_IMPERFECT		("Imparfait", false, null),
	FUTURE				("Futur simple", false, null),
	PAST_PARTICIPLE		("Participe pass�", false, null),
	UNKNOWN				("Inconnu", false, null);
	
	private final String frenchName;
	private final boolean compound;
	private final Tense auxiliarTense;
	
	Tense(String frenchName, boolean compound, Tense auxiliarTense){
		this.frenchName = frenchName;
		this.compound = compound;
		this.auxiliarTense = auxiliarTense;
	}
	
	public Tense getAuxiliarTense() {
		return auxiliarTense;
	}

	public String getFrenchName() {
		return frenchName;
	}

	public boolean isCompound() {
		return compound;
	}
}
