package com.koatee.conjugator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Tense {
	PRESENT_INDICATIVE	("Présent", false, null),
	PRESENT_PERFECT		("Passé composé", true, PRESENT_INDICATIVE),
	PAST_IMPERFECT		("Imparfait", false, null),
	FUTURE				("Futur simple", false, null),
	PAST_PARTICIPLE		("Participe passé", false, null),
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
	
	public static List<Tense> getUsableTenses(){
		List<Tense> allTenses = new ArrayList<Tense>(Arrays.asList(Tense.values()));
		// The not valid values are removed
		allTenses.remove(Tense.PAST_PARTICIPLE);
		allTenses.remove(Tense.UNKNOWN);
		
		return allTenses;
	}
}
