package com.koatee.conjugator.french.models;

import java.util.HashMap;
import java.util.Map;

import com.koatee.conjugator.Person;
import com.koatee.conjugator.Tense;
import com.koatee.conjugator.french.IrregularVerb;

public class Avoir extends IrregularVerb {

	public Avoir() {
		this.conjugations = new HashMap<Tense, Map<Person, String>>();

		HashMap<Person, String> presentConjugations = new HashMap<Person, String>();
		presentConjugations.put(Person.FIRST_SINGULAR, "ai");
		presentConjugations.put(Person.SECOND_SINGULAR, "as");
		presentConjugations.put(Person.THIRD_SINGULAR, "a");
		presentConjugations.put(Person.FIRST_PLURAL, "avons");
		presentConjugations.put(Person.SECOND_PLURAL, "avez");
		presentConjugations.put(Person.THIRD_PLURAL, "ont");
		this.conjugations.put(Tense.PRESENT_INDICATIVE, presentConjugations);

		HashMap<Person, String> imperfectConjugations = new HashMap<Person, String>();
		imperfectConjugations.put(Person.FIRST_SINGULAR, "avais");
		imperfectConjugations.put(Person.SECOND_SINGULAR, "avais");
		imperfectConjugations.put(Person.THIRD_SINGULAR, "avait");
		imperfectConjugations.put(Person.FIRST_PLURAL, "avions");
		imperfectConjugations.put(Person.SECOND_PLURAL, "aviez");
		imperfectConjugations.put(Person.THIRD_PLURAL, "avaient");
		this.conjugations.put(Tense.PAST_IMPERFECT, imperfectConjugations);

		HashMap<Person, String> futureConjugations = new HashMap<Person, String>();
		futureConjugations.put(Person.FIRST_SINGULAR, "aurai");
		futureConjugations.put(Person.SECOND_SINGULAR, "auras");
		futureConjugations.put(Person.THIRD_SINGULAR, "aura");
		futureConjugations.put(Person.FIRST_PLURAL, "aurons");
		futureConjugations.put(Person.SECOND_PLURAL, "aurez");
		futureConjugations.put(Person.THIRD_PLURAL, "auront");
		this.conjugations.put(Tense.FUTURE, futureConjugations);

		this.infinitive = new String("Avoir");
		this.pastParticiple = new String("eu");
	}
	
}
