package com.koatee.conjugator;

import java.util.List;

/**
 * 
 * @author jesus
 *
 */
public interface Verb {
	public List<Conjugation> conjugate(Tense tense);
	public Conjugation conjugate(Tense tense, Person person);
	public String getInfinitive();
}
