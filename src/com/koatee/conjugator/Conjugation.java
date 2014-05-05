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
	
	@Override
	public boolean equals(Object other){
	    if (other == null){
	    	return false;
	    }
	    if (other == this){
	    	return true;
	    }
	    if (!(other instanceof Conjugation)){
	    	return false;
	    }
	    Conjugation otherConjugation = (Conjugation)other;
	    if ( !this.getConjugation().equalsIgnoreCase(otherConjugation.getConjugation()) ){
	    	return false;
	    }else{
	    	return true;
	    }
	}
	
	public boolean equalsIgnorePronoun(Conjugation other){
	    if (other == null){
	    	return false;
	    }
	    if (other == this){
	    	return true;
	    }
	    if ( !this.getVerbalForm().equalsIgnoreCase(other.getVerbalForm()) ){
	    	return false;
	    }else{
	    	return true;
	    }
	}
	
	public String toString(){
		return this.getConjugation();
	}
}
