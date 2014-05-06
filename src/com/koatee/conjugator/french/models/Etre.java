package com.koatee.conjugator.french.models;

import java.util.HashMap;
import java.util.Map;

import com.koatee.conjugator.Person;
import com.koatee.conjugator.Tense;
import com.koatee.conjugator.french.IrregularVerb;

public class Etre extends IrregularVerb {

	public Etre() {
		this.conjugations = new HashMap<Tense, Map<Person, String>>();

		HashMap<Person, String> presentConjugations = new HashMap<Person, String>();
		presentConjugations.put(Person.FIRST_SINGULAR, "suis");
		presentConjugations.put(Person.SECOND_SINGULAR, "es");
		presentConjugations.put(Person.THIRD_SINGULAR, "est");
		presentConjugations.put(Person.FIRST_PLURAL, "sommes");
		presentConjugations.put(Person.SECOND_PLURAL, "êtes");
		presentConjugations.put(Person.THIRD_PLURAL, "sont");
		this.conjugations.put(Tense.PRESENT_INDICATIVE, presentConjugations);

		HashMap<Person, String> imperfectConjugations = new HashMap<Person, String>();
		imperfectConjugations.put(Person.FIRST_SINGULAR, "étais");
		imperfectConjugations.put(Person.SECOND_SINGULAR, "étais");
		imperfectConjugations.put(Person.THIRD_SINGULAR, "était");
		imperfectConjugations.put(Person.FIRST_PLURAL, "étions");
		imperfectConjugations.put(Person.SECOND_PLURAL, "étiez");
		imperfectConjugations.put(Person.THIRD_PLURAL, "étaient");
		this.conjugations.put(Tense.PAST_IMPERFECT, imperfectConjugations);

		HashMap<Person, String> futureConjugations = new HashMap<Person, String>();
		futureConjugations.put(Person.FIRST_SINGULAR, "serai");
		futureConjugations.put(Person.SECOND_SINGULAR, "seras");
		futureConjugations.put(Person.THIRD_SINGULAR, "sera");
		futureConjugations.put(Person.FIRST_PLURAL, "serons");
		futureConjugations.put(Person.SECOND_PLURAL, "serez");
		futureConjugations.put(Person.THIRD_PLURAL, "seront");
		this.conjugations.put(Tense.FUTURE, futureConjugations);

		this.infinitive = new String("être");
		this.pastParticiple = new String("été");
		this.auxiliarVerb = new Avoir();
	}
	
}
