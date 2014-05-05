package com.koatee.conjugator.french.models;

import java.util.HashMap;
import java.util.Map;

import com.koatee.conjugator.Person;
import com.koatee.conjugator.Tense;
import com.koatee.conjugator.french.RegularVerb;

public class Parler extends RegularVerb{

	public Parler(String infinitive) {
		super(infinitive);
		this.sufixes = new HashMap<Tense, Map<Person, String>>();
		
		HashMap<Person, String> presentSufixes = new HashMap<Person, String>();
		presentSufixes.put(Person.FIRST_SINGULAR, "e");
		presentSufixes.put(Person.SECOND_SINGULAR, "es");
		presentSufixes.put(Person.THIRD_SINGULAR, "e");
		presentSufixes.put(Person.FIRST_PLURAL, "ons");
		presentSufixes.put(Person.SECOND_PLURAL, "ez");
		presentSufixes.put(Person.THIRD_PLURAL, "ent");
		this.sufixes.put(Tense.PRESENT, presentSufixes);
		
		HashMap<Person, String> imparfaitSufixes = new HashMap<Person, String>();
		imparfaitSufixes.put(Person.FIRST_SINGULAR, "ais");
		imparfaitSufixes.put(Person.SECOND_SINGULAR, "ais");
		imparfaitSufixes.put(Person.THIRD_SINGULAR, "ait");
		imparfaitSufixes.put(Person.FIRST_PLURAL, "ions");
		imparfaitSufixes.put(Person.SECOND_PLURAL, "iez");
		imparfaitSufixes.put(Person.THIRD_PLURAL, "aient");
		this.sufixes.put(Tense.PAST_IMPERFECT, imparfaitSufixes);
		
		HashMap<Person, String> futurSufixes = new HashMap<Person, String>();
		futurSufixes.put(Person.FIRST_SINGULAR, "erai");
		futurSufixes.put(Person.SECOND_SINGULAR, "eras");
		futurSufixes.put(Person.THIRD_SINGULAR, "era");
		futurSufixes.put(Person.FIRST_PLURAL, "erons");
		futurSufixes.put(Person.SECOND_PLURAL, "erez");
		futurSufixes.put(Person.THIRD_PLURAL, "eront");
		this.sufixes.put(Tense.FUTURE, futurSufixes);
	}
	
	public Parler(){
		this("Parler");
	}

}
