package com.koatee.conjugator.game;

import java.io.InputStream;

import com.koatee.conjugator.Conjugation;
import com.koatee.conjugator.Person;
import com.koatee.conjugator.Tense;
import com.koatee.conjugator.Verb;

public interface ConjugatorGameFacade {

	public void loadVerbsFromFile(InputStream verbsFileStream);
	
	public void newQuestion();
	
	public void newQuestion(boolean result);
	
	public Verb getVerb();

	public Tense getTense();

	public Person getPerson();

	public Conjugation getConjugation();
	
	public void addTense(Tense tense);
	
	public void removeTense(Tense tense);

	public void addVerb(Verb verb);

	public void removeVerb(Verb verb);
}
