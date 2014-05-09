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

/**
 * Implements the game facade for the french verbs
 * 
 * @author susomartinez
 */
public class FrenchConjugatorGame implements ConjugatorGameFacade {

	/** Package where the model verbs are */
	private final static String MODELS_PACKAGE = "com.koatee.conjugator.french.models.";

	/** Available persons, tenses and verbs to play with */
	private List<Person> availablePersons;
	private List<Tense> availableTenses;
	private List<String> availableVerbs;
	
	/** Properties object which relates each verb with its verb model in order to instantiate them */
	private Properties properties = new Properties();
	
	/** The random number generator */
	private Random randomGenerator;

	/** The person, tense, verb and conjugation for the current game round */
	private Person questionPerson;
	private Tense questionTense;
	private Verb questionVerb;
	private Conjugation solution;

	// TODO: Provide different options
	// private List<String> options;

	/**
	 * Default constructor
	 */
	public FrenchConjugatorGame() {
		this(null);
	}

	/**
	 * Gets an instance out of a list of given tenses
	 * 
	 * @param availableTenses The list of tenses which will be available in the game
	 */
	public FrenchConjugatorGame(List<Tense> availableTenses) {
		// Load the tenses
		this.availableTenses = new ArrayList<Tense>();
		if (availableTenses == null) {
			this.availableTenses.addAll(Tense.getUsableTenses());
		} else {
			this.availableTenses.addAll(availableTenses);
		}
		// Load the persons
		this.availablePersons = Person.getUsablePersons();
		// TODO: Include the verb properties file input stream as a parameter for the constructor
		// Load the verbs
		loadVerbsFromFile(null);
		// Creates the random generator
		randomGenerator = new Random();
	}

	/**
	 * Loads the list of available verbs from a properties file which relates each verb with their verb model
	 * @param verbsFileStream The input stream corresponding to the verbs' properties file
	 */
	public void loadVerbsFromFile(InputStream verbsFileStream) {
		if (verbsFileStream == null) {
			// If no file is given, the game is initialized with some verb's models as an example
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
			try {
				properties.load(verbsFileStream);
				availableVerbs = new ArrayList(properties.keySet());
			} catch (IOException e) {
				loadVerbsFromFile(null);
			}
		}
	}

	/**
	 * It generates a new person, tense and verb to play with
	 */
	public void newQuestion() {
		int randomNumber;
		// As there are still some verbs' models which have not yet been implemented, a new random verb is generated until we found one which has an existing model
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
		// Random tense
		randomNumber = randomGenerator.nextInt(availableTenses.size());
		questionTense = availableTenses.get(randomNumber);
		// Random person
		randomNumber = randomGenerator.nextInt(availablePersons.size());
		questionPerson = availablePersons.get(randomNumber);
		// The verb is conjugated in order to obtain the game's solution
		solution = questionVerb.conjugate(questionTense, questionPerson);
	}

	/**
	 * It generates a new question
	 */
	public void newQuestion(boolean result) {
		// TODO: Change the random method in order to get the failed verbs more
		// often
		newQuestion();
	}

	/**
	 * {@inheritDoc}
	 */
	public Tense getTense() {
		return questionTense;
	}

	/**
	 * {@inheritDoc}
	 */
	public Person getPerson() {
		return questionPerson;
	}

	/**
	 * {@inheritDoc}
	 */
	public Verb getVerb() {
		return questionVerb;
	}

	/**
	 * {@inheritDoc}
	 */
	public Conjugation getConjugation() {
		return solution;
	}

	/**
	 * {@inheritDoc}
	 */
	public void addVerb(Verb verb) {
		String infinitive = verb.getInfinitive().toLowerCase(Locale.FRENCH);
		if (!this.availableVerbs.contains(infinitive)) {
			properties.put(infinitive, verb.getClass().getSimpleName());
			this.availableVerbs.add(infinitive);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeVerb(Verb verb) {
		this.availableVerbs.remove(verb);
	}

	/**
	 * {@inheritDoc}
	 */
	public void addTense(Tense tense) {
		if (!this.availableTenses.contains(tense)) {
			this.availableTenses.add(tense);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeTense(Tense tense) {
		this.availableTenses.remove(tense);
	}

}
