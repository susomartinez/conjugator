package com.koatee.conjugator;

/**
 * Conjugation class which stores the information of a conjugation for a given verb, tense and person.
 * 
 * It stores separatedly the personal pronoun and the verbal form in order to ease the information access
 * 
 * @author susomartinez
 * 
 */
public class Conjugation {

    // private Tense tenseObject;
    // private Person personObject;
    // private Verb verbObject;
    private String personString;
    private String verbalForm;

    /**
     * Conjugation constructor
     * 
     * @param person
     *            The personal pronoun as a string (including the apostrophe or a space)
     * @param verb
     *            The conjugated verb
     */
    public Conjugation(Person person, String verb) {
        this.personString = getPronoun(person, verb);
        this.verbalForm = verb;
    }

    /**
     * This method calculates the correct pronoun form. Basically it adds an apostrophe it the verbal form starts by
     * vowel
     * 
     * @param person
     *            The personal pronoun
     * @param verb
     *            The conjugated verb
     * @return The correct pronoun ready to be concatenated to the verbal form (with an apostrophe or a space after it)
     */
    private String getPronoun(Person person, String verb) {
        String result = person.getForm();
        if (startsWithVowel(verb) && person.equals(Person.FIRST_SINGULAR)) {
            result = result.replace('e', '\'');
        } else {
            result = result.concat(" ");
        }
        return result;
    }

    /**
     * This method calculates if a word starts with a vowel
     * 
     * @param word
     *            The given word
     * @return <b>true</b> if the word starts with a vowel, <b>false</b> otherwise
     */
    private boolean startsWithVowel(String word) {
        boolean result = false;
        if (word != null && word.length() > 0) {
            if ("aeiou".indexOf(word.charAt(0)) > 0) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Gets the correct personal pronoun for this conjugation
     * 
     * @return The pronoun followed by a space or an apostrophe
     */
    public String getPersonString() {
        return personString;
    }

    /**
     * Gets the conjugation of the verb
     * 
     * @return The conjugated verbal form
     */
    public String getVerbalForm() {
        return verbalForm;
    }

    /**
     * Gets the whole conjugation as a string. The result equals getPersonString() + getVerbalForm()
     * 
     * @return The whole conjugation as a string (pronoun + verb)
     */
    public String getConjugation() {
        return personString.concat(verbalForm);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Conjugation)) {
            return false;
        }
        Conjugation otherConjugation = (Conjugation) other;
        if (!this.getConjugation().equalsIgnoreCase(
                otherConjugation.getConjugation())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Compares two conjugations ignoring the personal pronoun
     * 
     * @param other
     *            The Conjugation object to compare with
     * @return <b>true</b> if the verbal form of the conjugation is equal, <b>false</b> otherwise
     */
    public boolean equalsIgnorePronoun(Conjugation other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!this.getVerbalForm().equalsIgnoreCase(other.getVerbalForm())) {
            return false;
        } else {
            return true;
        }
    }

    public String toString() {
        return this.getConjugation();
    }
}
