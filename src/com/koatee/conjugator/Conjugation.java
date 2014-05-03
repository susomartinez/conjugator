package com.koatee.conjugator;

public class Conjugation {
	
//	private Tense tenseObject;
//	private Person personObject;
//	private Verb verbObject;
	private String personString;
	private String verbalForm;
	
	public Conjugation(String person, String verb){
		this.personString = person;
		this.verbalForm = verb;
	}

	public String getPersonString() {
		return personString;
	}

	public String getVerbalForm() {
		return verbalForm;
	}
	
	public String getConjugation(){
		return personString.concat(verbalForm);
	}
}
