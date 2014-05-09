package com.koatee.conjugator;

import java.util.List;

/**
 * Interface for a verb
 * 
 * @author susomartinez
 */
public interface Verb {
    /**
     * Conjugates a tense completely, i.e. for every person
     * 
     * @param tense
     *            The tense to conjugate the verb in
     * @return A list with the conjugations for each available person
     */
    public List<Conjugation> conjugate(Tense tense);

    /**
     * Conjugates the verb in the given tense and person
     * 
     * @param tense
     *            The tense to conjugate the verb in
     * @param person
     *            The person to conjugate the verb in
     * @return The corresponding verb conjugation
     */
    public Conjugation conjugate(Tense tense, Person person);

    /**
     * Gets the infinitive
     * 
     * @return The verb's infinitive form
     */
    public String getInfinitive();

    /**
     * Gets the past participle
     * 
     * @return Ther verb's past participle form
     */
    public String getPastParticiple();
}
