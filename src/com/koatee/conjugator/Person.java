package com.koatee.conjugator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enumeration with the different personal forms
 * 
 * @author susomartinez
 * 
 */
public enum Person {
    IMPERSONAL("Impersonnel", 0, false), // Used for participles
    FIRST_SINGULAR("Je", 1, false), SECOND_SINGULAR("Tu", 2, false), THIRD_SINGULAR("Il/Elle/On", 3, false), FIRST_PLURAL(
            "Nous", 1, true), SECOND_PLURAL("Vous", 2, true), THIRD_PLURAL("Ils/Elles", 3, true), UNKNOWN("Inconnu",
            -1, false); // Used to avoid null values

    /** The personal pronoun */
    private final String form;
    /** Number of the person */
    private final int person;
    /** Indicates if the person is singular or plural */
    private final boolean plural;

    /**
     * Constructor with all the fields
     * 
     * @param form
     *            String representing the personal pronoun
     * @param person
     *            Number of the person as an int
     * @param plural
     *            <b>true</b> if it is plural, <b>false</b> otherwise (singular, impersonal, unknown...)
     */
    Person(String form, int person, boolean plural) {
        this.form = form;
        this.person = person;
        this.plural = plural;
    }

    /**
     * Gets the personal pronoun
     * 
     * @return Personal pronoun corresponding this person
     */
    public String getForm() {
        return form;
    }

    /**
     * Gets the person number
     * 
     * @return The person number from 1 to 3, 0 if it is impersonal, -1 if it is an unkown form
     */
    public int getPerson() {
        return person;
    }

    /**
     * Gets if the person is singular/plural
     * 
     * @return <b>true</b> if it is plural, <b>false</b> otherwise (singular, impersonal, unknown...)
     */
    public boolean isPlural() {
        return plural;
    }

    public String toString() {
        return form;
    }

    /**
     * Returns a list of all 'usable' (i.e. conjugable) persons
     * 
     * @return List of persons which can be conjugated
     */
    public static List<Person> getUsablePersons() {
        List<Person> result = new ArrayList<Person>(Arrays.asList(Person.values()));
        // Remove invalid persons
        result.remove(Person.IMPERSONAL);
        result.remove(Person.UNKNOWN);

        return result;
    }
}
