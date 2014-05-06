package com.koatee.conjugator;

public class Conjugation {

	// private Tense tenseObject;
	// private Person personObject;
	// private Verb verbObject;
	private String personString;
	private String verbalForm;

	public Conjugation(Person person, String verb) {
		this.personString = getPronoun(person, verb);
		this.verbalForm = verb;
	}

	private String getPronoun(Person person, String verb) {
		String result = person.getForm();
		if (startsWithVowel(verb) && person.equals(Person.FIRST_SINGULAR)) {
			result = result.replace('e', '\'');
		} else {
			result = result.concat(" ");
		}
		return result;
	}

	private boolean startsWithVowel(String word) {
		boolean result = false;
		if (word != null && word.length() > 0) {
			if ("aeiou".indexOf(word.charAt(0)) > 0) {
				result = true;
			}
		}
		return result;
	}

	public String getPersonString() {
		return personString;
	}

	public String getVerbalForm() {
		return verbalForm;
	}

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
