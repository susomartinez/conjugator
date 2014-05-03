package com.koatee.conjugator.french;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.koatee.conjugator.Conjugation;
import com.koatee.conjugator.Person;
import com.koatee.conjugator.Tense;
import com.koatee.conjugator.Verb;

public class RegularVerb implements Verb {
	
	protected Map<Tense, Map<Person, String>>sufixes;
	
	protected String infinitive, radical;
	
	private boolean startsWithVowel = false;
	
	public RegularVerb(String infinitive){
		if ( infinitive == null || infinitive.length() < 3 ){
			throw new IllegalArgumentException(infinitive + " is not a valid french verb");
		}
		this.infinitive = infinitive.toLowerCase(Locale.FRENCH);
		this.radical = getInfinitive().substring(0, getInfinitive().length()-2);
		if ( "aeiou".indexOf((getRadical().charAt(0))) > 0){
			this.startsWithVowel = true;
		}
	}

	@Override
	public List<Conjugation> conjugate(Tense tense) {
		ArrayList<Conjugation> result = new ArrayList<Conjugation>();
		for (Person person : Person.values()){
			Conjugation conjugation = conjugate(tense, person);
			result.add(conjugation);
		}
		return result;
	}

	@Override
	public Conjugation conjugate(Tense tense, Person person) {
		String personString = getPronoun(person);
		String verb = getRadical();
		verb = verb.concat(sufixes.get(tense).get(person));
		
		Conjugation result = new Conjugation(personString, verb);
		return result;
	}
	
	private String getPronoun(Person person){
		String result = person.getForm();
		if ( startsWithVowel && person.equals(Person.FIRST_SINGULAR)){
			result = result.replace('e', '\'');
		}else{
			result = result.concat(" ");
		}
		return result;
	}

	@Override
	public String getInfinitive() {
		return this.infinitive;
	}
	
	public String getRadical(){
		return this.radical;
	}
}
