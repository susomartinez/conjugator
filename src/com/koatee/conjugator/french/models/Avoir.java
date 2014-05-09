package com.koatee.conjugator.french.models;

import java.util.HashMap;
import java.util.Map;

import com.koatee.conjugator.Conjugation;
import com.koatee.conjugator.Person;
import com.koatee.conjugator.Tense;
import com.koatee.conjugator.french.IrregularVerb;

/**
 * Avoir verb class. It is an irregular verb, often used as auxiliar verb for
 * compound tenses
 * 
 * @author susomartinez
 */
public class Avoir extends IrregularVerb {

	/**
	 * Default constructor
	 */
	public Avoir() {
		// The conjugations map containing each verb conjugation is created
		this.conjugations = new HashMap<Tense, Map<Person, String>>();

		// Présent simple
		HashMap<Person, String> presentConjugations = new HashMap<Person, String>();
		presentConjugations.put(Person.FIRST_SINGULAR, "ai");
		presentConjugations.put(Person.SECOND_SINGULAR, "as");
		presentConjugations.put(Person.THIRD_SINGULAR, "a");
		presentConjugations.put(Person.FIRST_PLURAL, "avons");
		presentConjugations.put(Person.SECOND_PLURAL, "avez");
		presentConjugations.put(Person.THIRD_PLURAL, "ont");
		this.conjugations.put(Tense.PRESENT_INDICATIVE, presentConjugations);

		// Imparfait
		HashMap<Person, String> imperfectConjugations = new HashMap<Person, String>();
		imperfectConjugations.put(Person.FIRST_SINGULAR, "avais");
		imperfectConjugations.put(Person.SECOND_SINGULAR, "avais");
		imperfectConjugations.put(Person.THIRD_SINGULAR, "avait");
		imperfectConjugations.put(Person.FIRST_PLURAL, "avions");
		imperfectConjugations.put(Person.SECOND_PLURAL, "aviez");
		imperfectConjugations.put(Person.THIRD_PLURAL, "avaient");
		this.conjugations.put(Tense.PAST_IMPERFECT, imperfectConjugations);

		// Futur simple
		HashMap<Person, String> futureConjugations = new HashMap<Person, String>();
		futureConjugations.put(Person.FIRST_SINGULAR, "aurai");
		futureConjugations.put(Person.SECOND_SINGULAR, "auras");
		futureConjugations.put(Person.THIRD_SINGULAR, "aura");
		futureConjugations.put(Person.FIRST_PLURAL, "aurons");
		futureConjugations.put(Person.SECOND_PLURAL, "aurez");
		futureConjugations.put(Person.THIRD_PLURAL, "auront");
		this.conjugations.put(Tense.FUTURE, futureConjugations);

		// Impersonal forms
		this.infinitive = new String("avoir");
		this.pastParticiple = new String("eu");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Conjugation conjugate(Tense tense, Person person) {
		String verb = new String();
		if (!tense.isCompound()) {
			// For not compound tenses, the corresponding verbal form is got out
			// of the conjugation map
			if (conjugations.containsKey(tense)) {
				verb = conjugations.get(tense).get(person);
			}
		} else {
			// As the Avoir verb is its own auxiliar, we just get the
			// corresponding forms from the conjugations map
			if (conjugations.containsKey(tense.getAuxiliarTense())) {
				verb = conjugations.get(tense.getAuxiliarTense()).get(person);
			}
			// ...and the past participle is added
			verb = verb.concat(" ");
			verb = verb.concat(getPastParticiple());
		}
		// The conjugation form is generated and returned
		Conjugation result = new Conjugation(person, verb);
		return result;
	}
}
