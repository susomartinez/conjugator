package com.koatee.conjugator.game;

import java.io.InputStream;

import com.koatee.conjugator.Conjugation;
import com.koatee.conjugator.Person;
import com.koatee.conjugator.Tense;
import com.koatee.conjugator.Verb;

/**
 * It defines the interface between a game viewer (android app, console app...) and the verbs' structure. It serves as a unique access point to the verbs 
 * 
 * @author susomartinez
 */
public interface ConjugatorGameFacade {

	/**
	 * This method loads the verbs which are going to be used in the game from a properties file
	 * @param verbsFileStream The file stream to read the properties file
	 */
	public void loadVerbsFromFile(InputStream verbsFileStream);
	
	/**
	 * Generates a new question, i.e. a random verb, tense and person
	 */
	public void newQuestion();
	
	/**
	 * Generates a new question, i.e. a random verb, tense and person taking into account the last result
	 * 
	 * @param result How the player did in the last round: <b>true</b> if he succeeded, <b>false</b> if he did not
	 */
	public void newQuestion(boolean result);
	
	/**
	 * Gets the current round's verb
	 * @return The current verb
	 */
	public Verb getVerb();

	/**
	 * Gets the current's round tense
	 * @return The current tense
	 */
	public Tense getTense();

	/**
	 * Gets the current round's person
	 * @return The current person
	 */
	public Person getPerson();

	/**
	 * Gets the current round's conjugation (i.e. the game solution)
	 * @return The current conjugation
	 */
	public Conjugation getConjugation();
	
	/**
	 * It adds a new tense to the available ones
	 * @param tense The tense to be added
	 */
	public void addTense(Tense tense);
	
	/**
	 * It removes a tense from the available ones
	 * @param tense The tense to be removed
	 */
	public void removeTense(Tense tense);

	/**
	 * It adds a new verb to the available ones
	 * @param verb The verb to be added
	 */
	public void addVerb(Verb verb);

	/**
	 * It removes a verb from the available ones
	 * @param verb The verb to be removed
	 */
	public void removeVerb(Verb verb);
}
