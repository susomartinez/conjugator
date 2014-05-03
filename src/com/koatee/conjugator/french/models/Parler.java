package com.koatee.conjugator.french.models;

import java.util.HashMap;
import java.util.Map;

import com.koatee.conjugator.Person;
import com.koatee.conjugator.Tense;
import com.koatee.conjugator.french.RegularVerb;

public class Parler extends RegularVerb{

	public Parler(String infinitive) {
		super(infinitive);
		HashMap<Person, String> presentSufixes = new HashMap<Person, String>();
		presentSufixes.put(Person.FIRST_SINGULAR, "e");
		presentSufixes.put(Person.SECOND_SINGULAR, "es");
		presentSufixes.put(Person.THIRD_SINGULAR, "e");
		presentSufixes.put(Person.FIRST_PLURAL, "ons");
		presentSufixes.put(Person.SECOND_PLURAL, "ez");
		presentSufixes.put(Person.THIRD_PLURAL, "ent");
		
		this.sufixes = new HashMap<Tense, Map<Person, String>>();
		this.sufixes.put(Tense.PRESENT, presentSufixes);
	}
	
	public Parler(){
		this("Parler");
	}

}
