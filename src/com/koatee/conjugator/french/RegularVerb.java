package com.koatee.conjugator.french;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.koatee.conjugator.Conjugation;
import com.koatee.conjugator.Person;
import com.koatee.conjugator.Tense;
import com.koatee.conjugator.Verb;
import com.koatee.conjugator.french.models.Avoir;

public class RegularVerb implements Verb {

	protected Map<Tense, Map<Person, String>> sufixes;

	protected String infinitive, pastParticiple, radical;

	private Verb auxiliarVerb = new Avoir();

	protected RegularVerb(){
	}
	
	protected RegularVerb(String infinitive) {
		setInfinitive(infinitive);
	}

	public void setInfinitive(String infinitive) {
		if (infinitive == null || infinitive.length() < 3) {
			throw new IllegalArgumentException(infinitive
					+ " is not a valid french verb");
		}
		this.infinitive = infinitive.toLowerCase(Locale.FRENCH);
		this.radical = getInfinitive().substring(0,
				getInfinitive().length() - 2);
		if (sufixes.containsKey(Tense.PAST_PARTICIPLE)) {
			this.pastParticiple = getRadical().concat(
					sufixes.get(Tense.PAST_PARTICIPLE).get(Person.IMPERSONAL));
		} else {
			this.pastParticiple = new String();
		}
	}

	@Override
	public List<Conjugation> conjugate(Tense tense) {
		ArrayList<Conjugation> result = new ArrayList<Conjugation>();
		for (Person person : Person.values()) {
			Conjugation conjugation = conjugate(tense, person);
			result.add(conjugation);
		}
		return result;
	}

	@Override
	public Conjugation conjugate(Tense tense, Person person) {
		String verb = new String();
		if (!tense.isCompound()) {
			verb = getRadical();
			if (verb != null && sufixes.containsKey(tense)) {
				verb = verb.concat(sufixes.get(tense).get(person));
			}
		} else {
			if (auxiliarVerb != null) {
				Conjugation auxiliarConjugation = auxiliarVerb.conjugate(
						tense.getAuxiliarTense(), person);
				verb = auxiliarConjugation.getVerbalForm();
				verb = verb.concat(" ");
				verb = verb.concat(getPastParticiple());
			}
		}

		Conjugation result = new Conjugation(person, verb);
		return result;
	}

	@Override
	public String getInfinitive() {
		return this.infinitive;
	}

	public String getPastParticiple() {
		return this.pastParticiple;
	}

	public String getRadical() {
		return this.radical;
	}
}
