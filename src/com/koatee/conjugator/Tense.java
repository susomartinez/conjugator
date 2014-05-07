package com.koatee.conjugator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Enumeration representing the different verbal tenses
 * 
 * @author susomartinez
 * 
 */
public enum Tense {
    PRESENT_INDICATIVE  ("Présent", false, null),
    PRESENT_PERFECT     ("Passé composé", true, PRESENT_INDICATIVE),
    PAST_IMPERFECT      ("Imparfait", false, null),
    FUTURE              ("Futur simple", false, null),
    PAST_PARTICIPLE     ("Participe passé", false, null),
    UNKNOWN             ("Inconnu", false, null); // Used to avoid null values

    /** The french name for the tense */
    private final String frenchName;
    /** Indicates if is a compound tense */
    private final boolean compound;
    /** Tense for the auxiliar verb (if compound) */
    private final Tense auxiliarTense;

    /**
     * Constructor with all fields
     * 
     * @param frenchName
     *            The french name for the tense
     * @param compound
     *            Indicates if is a compound tense
     * @param auxiliarTense
     *            Tense for the auxiliar verb (if compound)
     */
    Tense(String frenchName, boolean compound, Tense auxiliarTense) {
        this.frenchName = frenchName;
        this.compound = compound;
        this.auxiliarTense = auxiliarTense;
    }

    /**
     * Gets the auxiliar verb's tense
     * 
     * @return Tense for the auxiliar verb if it's compound, null if not
     */
    public Tense getAuxiliarTense() {
        return auxiliarTense;
    }

    /**
     * Gets the french name of the tense
     * 
     * @return The french name of the tense
     */
    public String getFrenchName() {
        return frenchName;
    }

    /**
     * Indicates if is a compound tense
     * 
     * @return <b>true</b> if is compound, <b>false</b> otherwise
     */
    public boolean isCompound() {
        return compound;
    }

    /**
     * Gets the actual 'conjugable' tenses
     * 
     * @return List of the tenses which can be conjuged
     */
    public static List<Tense> getUsableTenses() {
        List<Tense> allTenses = new ArrayList<Tense>(Arrays.asList(Tense.values()));
        // The not valid values are removed
        allTenses.remove(Tense.PAST_PARTICIPLE);
        allTenses.remove(Tense.UNKNOWN);

        return allTenses;
    }
}
