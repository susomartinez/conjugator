package com.koatee.conjugator.french.models;

import java.util.HashMap;
import java.util.Map;

import com.koatee.conjugator.Person;
import com.koatee.conjugator.Tense;
import com.koatee.conjugator.french.RegularVerb;

public class Choisir extends RegularVerb{

	public Choisir(String infinitive) {
		super();
		
		this.sufixes = new HashMap<Tense, Map<Person, String>>();
		
		HashMap<Person, String> presentSufixes = new HashMap<Person, String>();
		presentSufixes.put(Person.FIRST_SINGULAR, "is");
		presentSufixes.put(Person.SECOND_SINGULAR, "is");
		presentSufixes.put(Person.THIRD_SINGULAR, "it");
		presentSufixes.put(Person.FIRST_PLURAL, "issons");
		presentSufixes.put(Person.SECOND_PLURAL, "issez");
		presentSufixes.put(Person.THIRD_PLURAL, "issent");
		this.sufixes.put(Tense.PRESENT_INDICATIVE, presentSufixes);
		
		HashMap<Person, String> imperfectSufixes = new HashMap<Person, String>();
		imperfectSufixes.put(Person.FIRST_SINGULAR, "issais");
		imperfectSufixes.put(Person.SECOND_SINGULAR, "issais");
		imperfectSufixes.put(Person.THIRD_SINGULAR, "issait");
		imperfectSufixes.put(Person.FIRST_PLURAL, "issions");
		imperfectSufixes.put(Person.SECOND_PLURAL, "issiez");
		imperfectSufixes.put(Person.THIRD_PLURAL, "issaient");
		this.sufixes.put(Tense.PAST_IMPERFECT, imperfectSufixes);
		
		HashMap<Person, String> futureSufixes = new HashMap<Person, String>();
		futureSufixes.put(Person.FIRST_SINGULAR, "irai");
		futureSufixes.put(Person.SECOND_SINGULAR, "iras");
		futureSufixes.put(Person.THIRD_SINGULAR, "ira");
		futureSufixes.put(Person.FIRST_PLURAL, "irons");
		futureSufixes.put(Person.SECOND_PLURAL, "irez");
		futureSufixes.put(Person.THIRD_PLURAL, "iront");
		this.sufixes.put(Tense.FUTURE, futureSufixes);
		
		HashMap<Person, String> pastParticipleSufix = new HashMap<Person, String>();
		pastParticipleSufix.put(Person.IMPERSONAL, "i");
		this.sufixes.put(Tense.PAST_PARTICIPLE, pastParticipleSufix);
		
		setInfinitive(infinitive);
	}
	
	public Choisir(){
		this("Choisir");
	}

}
