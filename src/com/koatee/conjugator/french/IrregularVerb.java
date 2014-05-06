package com.koatee.conjugator.french;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.koatee.conjugator.Conjugation;
import com.koatee.conjugator.Person;
import com.koatee.conjugator.Tense;
import com.koatee.conjugator.Verb;

public class IrregularVerb implements Verb {

	protected Map<Tense, Map<Person, String>> conjugations;

	protected String infinitive, pastParticiple;

	protected Verb auxiliarVerb;

	protected IrregularVerb() {
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
			if (conjugations.containsKey(tense)) {
				verb = conjugations.get(tense).get(person);
			}
		} else {
			Conjugation auxiliarConjugation;
			if (auxiliarVerb != null) {
				auxiliarConjugation = auxiliarVerb.conjugate(
						tense.getAuxiliarTense(), person);
				verb = auxiliarConjugation.getVerbalForm();
			} else {
				// Exceptional case for Avoir, which is its own auxiliar
				verb = conjugations.get(tense.getAuxiliarTense()).get(person);
			}
			verb = verb.concat(" ");
			verb = verb.concat(getPastParticiple());
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

}
