package com.koatee.conjugator.game;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;

import com.koatee.conjugator.Conjugation;
import com.koatee.conjugator.Person;
import com.koatee.conjugator.Tense;
import com.koatee.conjugator.Verb;

public class FrenchConjugatorGame implements ConjugatorGameFacade {

	private final static String MODELS_PACKAGE = "com.koatee.conjugator.french.models.";
	private static final String DEFAULT_VERB_FILE_PATH = "verbs.properties";

	private List<Person> availablePersons;
	private List<Tense> availableTenses;
	private Properties properties = new Properties();
	private List<String> availableVerbs;
	private Random randomGenerator;

	// Question
	private Tense questionTense;
	private Person questionPerson;
	private Verb questionVerb;
	private Conjugation solution;

	// TODO: Provide different options
	// private List<String> options;

	public FrenchConjugatorGame() {
		this(null);
	}

	public FrenchConjugatorGame(List<Tense> availableTenses) {
		this.availableTenses = new ArrayList<Tense>();
		if (availableTenses == null) {
			this.availableTenses.addAll(Tense.getUsableTenses());
		} else {
			this.availableTenses.addAll(availableTenses);
		}
		this.availablePersons = Person.getUsablePersons();
		loadVerbsFromFile(null);
		randomGenerator = new Random();
	}

	public void loadVerbsFromFile(InputStream verbsFileStream) {
		if (verbsFileStream == null) {
			this.availableVerbs = new ArrayList<String>();
			properties.put("parler", "Parler");
			this.availableVerbs.add("parler");
			properties.put("qimer", "Parler");
			this.availableVerbs.add("aimer");
			properties.put("commencer", "Parler");
			this.availableVerbs.add("commencer");
			properties.put("manger", "Parler");
			this.availableVerbs.add("manger");
			properties.put("avoir", "Avoir");
			this.availableVerbs.add("avoir");
			properties.put("être", "Etre");
			this.availableVerbs.add("être");
		} else {
			// InputStream verbsFileStream =
			// this.getClass().getClassLoader().getResourceAsStream(filePath);
			// InputStream verbsFileStream =
			// this.getClass().getResourceAsStream(filePath);
			try {
				properties.load(verbsFileStream);
				// availableVerbs = new
				// ArrayList<String>(properties.stringPropertyNames());
				availableVerbs = new ArrayList(properties.keySet());
			} catch (IOException e) {
				loadVerbsFromFile(null);
			}
		}
	}

	public void newQuestion() {
		int randomNumber;
		boolean modelNotExist = true;
		while (modelNotExist) {
			try {
				randomNumber = randomGenerator.nextInt(availableVerbs.size());
				String infinitive = availableVerbs.get(randomNumber);
				String className = MODELS_PACKAGE.concat((String) properties
						.getProperty(infinitive));
				Class<?> modelClass = Class.forName(className);
				Constructor<?> modelConstructor = modelClass
						.getConstructor(String.class);
				questionVerb = (Verb) modelConstructor
						.newInstance(new Object[] { infinitive });
				modelNotExist = false;
			} catch (Exception e) {
				// Exception getting the model class for a verb
				// i.e. the model is not yet implemented, the while loop
				// continues
			}
		}
		randomNumber = randomGenerator.nextInt(availableTenses.size());
		questionTense = availableTenses.get(randomNumber);
		randomNumber = randomGenerator.nextInt(availablePersons.size());
		questionPerson = availablePersons.get(randomNumber);
		solution = questionVerb.conjugate(questionTense, questionPerson);
	}

	public void newQuestion(boolean result) {
		// TODO: Change the random method in order to get the failed verbs more
		// often
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
		String infinitive = verb.getInfinitive().toLowerCase(Locale.FRENCH);
		if (!this.availableVerbs.contains(infinitive)) {
			properties.put(infinitive, verb.getClass().getSimpleName());
			this.availableVerbs.add(infinitive);
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
