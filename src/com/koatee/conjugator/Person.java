package com.koatee.conjugator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Person {
	IMPERSONAL		("Impersonnel",	 0, false),
	FIRST_SINGULAR	("Je",			 1, false),
	SECOND_SINGULAR	("Tu",			 2, false),
	THIRD_SINGULAR	("Il/Elle/On",	 3, false),
	FIRST_PLURAL	("Nous",		 1, true),
	SECOND_PLURAL	("Vous",		 2, true),
	THIRD_PLURAL	("Ils/Elles",	 3, true),
	UNKNOWN			("Inconnu",		-1, false);
	
	private final String form;
	private final int person;
	private final boolean plural;
	
	Person(String form, int person, boolean plural){
		this.form = form;
		this.person = person;
		this.plural = plural;
	}

	public String getForm() {
		return form;
	}

	public int getPerson() {
		return person;
	}

	public boolean isPlural() {
		return plural;
	}
	
	public String toString(){
		return form;
	}
	
	public static List<Person> getUsablePersons(){
		List<Person> result = new ArrayList<Person>(Arrays.asList(Person.values()));
		// Remove invalid persons
		result.remove(Person.IMPERSONAL);
		result.remove(Person.UNKNOWN);
		
		return result;
	}
}
