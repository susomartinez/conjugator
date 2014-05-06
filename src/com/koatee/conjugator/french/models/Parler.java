package com.koatee.conjugator.french.models;

import java.util.HashMap;
import java.util.Map;

import com.koatee.conjugator.Person;
import com.koatee.conjugator.Tense;
import com.koatee.conjugator.french.RegularVerb;

public class Parler extends RegularVerb{

	public Parler(String infinitive) {
		super();
		
		this.sufixes = new HashMap<Tense, Map<Person, String>>();
		
		HashMap<Person, String> presentSufixes = new HashMap<Person, String>();
		presentSufixes.put(Person.FIRST_SINGULAR, "e");
		presentSufixes.put(Person.SECOND_SINGULAR, "es");
		presentSufixes.put(Person.THIRD_SINGULAR, "e");
		presentSufixes.put(Person.FIRST_PLURAL, "ons");
		presentSufixes.put(Person.SECOND_PLURAL, "ez");
		presentSufixes.put(Person.THIRD_PLURAL, "ent");
		this.sufixes.put(Tense.PRESENT_INDICATIVE, presentSufixes);
		
		HashMap<Person, String> imperfectSufixes = new HashMap<Person, String>();
		imperfectSufixes.put(Person.FIRST_SINGULAR, "ais");
		imperfectSufixes.put(Person.SECOND_SINGULAR, "ais");
		imperfectSufixes.put(Person.THIRD_SINGULAR, "ait");
		imperfectSufixes.put(Person.FIRST_PLURAL, "ions");
		imperfectSufixes.put(Person.SECOND_PLURAL, "iez");
		imperfectSufixes.put(Person.THIRD_PLURAL, "aient");
		this.sufixes.put(Tense.PAST_IMPERFECT, imperfectSufixes);
		
		HashMap<Person, String> futureSufixes = new HashMap<Person, String>();
		futureSufixes.put(Person.FIRST_SINGULAR, "erai");
		futureSufixes.put(Person.SECOND_SINGULAR, "eras");
		futureSufixes.put(Person.THIRD_SINGULAR, "era");
		futureSufixes.put(Person.FIRST_PLURAL, "erons");
		futureSufixes.put(Person.SECOND_PLURAL, "erez");
		futureSufixes.put(Person.THIRD_PLURAL, "eront");
		this.sufixes.put(Tense.FUTURE, futureSufixes);
		
		HashMap<Person, String> pastParticipleSufix = new HashMap<Person, String>();
		pastParticipleSufix.put(Person.IMPERSONAL, "Ž");
		this.sufixes.put(Tense.PAST_PARTICIPLE, pastParticipleSufix);
		
		setInfinitive(infinitive);
	}
	
	public Parler(){
		this("Parler");
	}

}
