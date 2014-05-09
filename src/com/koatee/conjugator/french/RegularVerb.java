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

/**
 * The generic class implementing a French regular verb. The conjugations are
 * generated combining the verb's radical with the corresponding suffix (on for
 * each tense/verb)
 * 
 * @author susomartinez
 */
public class RegularVerb implements Verb {

	/**
	 * The {@link Map} with every conjugation suffix. As it is a two-key (tense
	 * and person) map, it is implemented using a map of maps
	 */
	protected Map<Tense, Map<Person, String>> suffixes;

	protected String infinitive, pastParticiple, radical;

	// The default auxiliar verb is Avoir
	private Verb auxiliarVerb = new Avoir();

	/**
	 * Default constructor
	 */
	protected RegularVerb() {
	}

	/**
	 * Constructor setting the verb
	 * 
	 * @param infinitive
	 *            The verb
	 */
	protected RegularVerb(String infinitive) {
		setInfinitive(infinitive);
	}

	/**
	 * Defines the verb for this object
	 * 
	 * @param infinitive
	 *            The desired verb in infinitive form
	 * @throws IllegalArgumentException
	 *             When the verb is not a valid french regular verb or it is not
	 *             in its infinitive form. Currently this exception is sent only
	 *             if the given infinitive has less than three characters
	 */
	public void setInfinitive(String infinitive)
			throws IllegalArgumentException {
		if (infinitive == null || infinitive.length() < 3) {
			throw new IllegalArgumentException(infinitive
					+ " is not a valid french verb");
		}
		// TODO: Do more checks. The verb has to end in either -er, -ir or -re.
		// Otherwise it would not be a valid regular verb
		this.infinitive = infinitive.toLowerCase(Locale.FRENCH);
		this.radical = getRadicalFromInfinitive(getInfinitive());
		// The past participle is generated combining the radical with the
		// corresponding suffix, if it exists
		if (suffixes.containsKey(Tense.PAST_PARTICIPLE)) {
			this.pastParticiple = getRadical().concat(
					suffixes.get(Tense.PAST_PARTICIPLE).get(Person.IMPERSONAL));
		} else {
			this.pastParticiple = new String();
		}
	}

	/**
	 * Takes the verb radical out of the given infinitive
	 * 
	 * @param infinitive
	 *            The verb's infinitive
	 * @return The verb's radical. An empty string if it cannot be calculated
	 */
	private String getRadicalFromInfinitive(String infinitive) {
		String radical = new String("");
		if (infinitive != null && infinitive.length() > 2) {
			// The radical is calculated taking away the two last letters from
			// the infinitive
			radical = infinitive.substring(0, getInfinitive().length() - 2);
		}
		return radical;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Conjugation> conjugate(Tense tense) {
		ArrayList<Conjugation> result = new ArrayList<Conjugation>();
		for (Person person : Person.values()) {
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
			// If the tense is not compound, the verb is formed combining the
			// radical with the corresponding suffix applying the ortographical
			// rules
			verb = getRadical();
			if (verb != null && suffixes.containsKey(tense)) {
				verb = applyOrtographicalRules(verb,
						suffixes.get(tense).get(person));
			}
		} else {
			if (auxiliarVerb != null) {
				// For a compound tense, the auxiliar verb is conjugated
				// first...
				Conjugation auxiliarConjugation = auxiliarVerb.conjugate(
						tense.getAuxiliarTense(), person);
				verb = auxiliarConjugation.getVerbalForm();
				// ...and the past particple is added
				verb = verb.concat(" ");
				verb = verb.concat(getPastParticiple());
			}
		}
		// The conjugation form is generated and returned
		Conjugation result = new Conjugation(person, verb);
		return result;
	}

	/**
	 * This method applies some ortographical rules in order to reduce the
	 * number of verb models. Applying these rules allows to group several
	 * models into one. For instance, Commencer and Manger models can be
	 * conjugated as Parler following these rules
	 * 
	 * @param radical
	 *            Verb's radical
	 * @param suffix
	 *            Verb's suffix
	 * @return The verbal form after the ortographical rules have been applied.
	 *         If not rule is applicable, it just returns the radical
	 *         concatenated with the suffix
	 */
	private String applyOrtographicalRules(String radical, String suffix) {
		String result = new String();
		if (radical == null || suffix == null) {
			return result;
		}
		// Rule for -cer verbs (Commencer model)
		// They change the 'c' for a 'ç' before 'a' and 'o'
		if (infinitive.endsWith("cer")) {
			if (suffix.startsWith("a") || suffix.startsWith("o")) {
				radical = radical.substring(0, radical.length() - 1);
				radical = radical.concat("ç");
			}
		}
		// Rule for -ger verbs (Manger model)
		// They add a 'e' to the 'g' before 'a' and 'o' 
		if (infinitive.endsWith("ger")) {
			if (suffix.startsWith("a") || suffix.startsWith("o")) {
				radical = radical.concat("e");
			}
		}
		// The radical (modified it it was needed) is concatenated with the suffix
		result = radical.concat(suffix);
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

	/**
	 * {@inheritDoc}
	 */
	public String getRadical() {
		return this.radical;
	}
}
