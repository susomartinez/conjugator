package com.koatee.conjugator.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.koatee.conjugator.Conjugation;
import com.koatee.conjugator.Person;
import com.koatee.conjugator.Tense;
import com.koatee.conjugator.Verb;
import com.koatee.conjugator.french.models.Parler;

public class FrenchConjugatorGame implements ConjugatorGameFacade{

	private static final String DEFAULT_VERB_FILE_PATH = null;
	
	private List<Person> availablePersons;
	private List<Tense> availableTenses;
	private List<Verb> availableVerbs;
	private Random randomGenerator;

	// Question
	private Tense questionTense;
	private Person questionPerson;
	private Verb questionVerb;
	private Conjugation solution;

	// private List<String> options;
	
	public FrenchConjugatorGame(){
		this(null);
	}

	public FrenchConjugatorGame(List<Tense> availableTenses) {
		this.availableTenses = new ArrayList<Tense>();
		if (availableTenses == null) {
			this.availableTenses.addAll(Arrays.asList(Tense.values()));
		} else {
			this.availableTenses.addAll(availableTenses);
		}
		this.availablePersons = Arrays.asList(Person.values());
		this.availableVerbs = new ArrayList<Verb>();
		loadVerbsFromFile(DEFAULT_VERB_FILE_PATH);
		randomGenerator = new Random();
	}
	
	public void loadVerbsFromFile(String filePath){
		// TODO: Create the file and get the verbs from it
		this.availableVerbs.add(new Parler());
	}

	public void newQuestion() {
		int randomNumber = randomGenerator.nextInt(availableVerbs.size());
		questionVerb = availableVerbs.get(randomNumber);
		randomNumber = randomGenerator.nextInt(availableTenses.size());
		questionTense = availableTenses.get(randomNumber);
		randomNumber = randomGenerator.nextInt(availablePersons.size());
		questionPerson = availablePersons.get(randomNumber);
		solution = questionVerb.conjugate(questionTense, questionPerson);
	}
	
	public void newQuestion(boolean result) {
		// TODO: Change the random method in order to get the failed verbs more often
		newQuestion();
	}

	public Tense getTense() {
		return questionTense;
	}

	public Person getPerson() {
		return questionPerson;
	}

	public Verb getVerb() {
		return questionVerb;
	}
	
	public Conjugation getConjugation() {
		return solution;
	}

	
	public void addVerb(Verb verb) {
		if (!this.availableVerbs.contains(verb)) {
			this.availableVerbs.add(verb);
		}
	}

	public void removeVerb(Verb verb) {
		this.availableVerbs.remove(verb);
	}
	
	public void addTense(Tense tense) {
		if (!this.availableTenses.contains(tense)) {
			this.availableTenses.add(tense);
		}
	}

	public void removeTense(Tense tense) {
		this.availableTenses.remove(tense);
	}

}
