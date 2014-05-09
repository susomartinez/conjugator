package com.koatee.conjugator.french;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.koatee.conjugator.Conjugation;
import com.koatee.conjugator.Person;
import com.koatee.conjugator.Tense;
import com.koatee.conjugator.Verb;

/**
 * The generic class implementing a French irregular verb. As it does not exist
 * an "algorythm" to conjugate these verbs it just stores every verbal form in a
 * {@link Map} and recovers it from there
 * 
 * @author susomartinez
 */
public class IrregularVerb implements Verb {

	/**
	 * The {@link Map} with every conjugation form. As it is a two-key (tense
	 * and person) map, it is implemented using a map of maps
	 */
	protected Map<Tense, Map<Person, String>> conjugations;

	protected String infinitive, pastParticiple;

	/** The verb used in order to generate the compound tenses */
	protected Verb auxiliarVerb;

	/**
	 * Default constructor
	 */
	protected IrregularVerb() {
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Conjugation> conjugate(Tense tense) {
		ArrayList<Conjugation> result = new ArrayList<Conjugation>();
		// The conjugation for all the tense is formed joining every single
		// person conjugation
		for (Person person : Person.getUsablePersons()) {
			Conjugation conjugation = conjugate(tense, person);
			result.add(conjugation);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public Conjugation conjugate(Tense tense, Person person) {
		String verb = new String();
		if (!tense.isCompound()) {
			// For not compound tenses, the corresponding verbal form is got out
			// of the conjugation map
			if (conjugations.containsKey(tense)) {
				verb = conjugations.get(tense).get(person);
			}
		} else {
			// For compound tenses, first the auxiliar verb conjugation is taken
			Conjugation auxiliarConjugation;
			auxiliarConjugation = auxiliarVerb.conjugate(
					tense.getAuxiliarTense(), person);
			verb = auxiliarConjugation.getVerbalForm();
			// ...and the past participle is added
			verb = verb.concat(" ");
			verb = verb.concat(getPastParticiple());
		}
		// The conjugation form is generated and returned
		Conjugation result = new Conjugation(person, verb);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getInfinitive() {
		return this.infinitive;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getPastParticiple() {
		return this.pastParticiple;
	}

}
