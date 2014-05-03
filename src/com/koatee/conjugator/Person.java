package com.koatee.conjugator;

public enum Person {
	FIRST_SINGULAR	("Je", 1, false),
	SECOND_SINGULAR	("Tu", 2, false),
	THIRD_SINGULAR	("Il/Elle/On", 3, false),
	FIRST_PLURAL	("Nous", 1, true),
	SECOND_PLURAL	("Vous", 2, true),
	THIRD_PLURAL	("Ils/Elles", 3, true);
	
	private final String form;
	private final int person;
	private final boolean plural;
	
	Person(String form, int person, boolean plural){
		this.form = form;
		this.person = person;
		this.plural = plural;
	}

	public String getForm() {
		return form;
	}

	public int getPerson() {
		return person;
	}

	public boolean isPlural() {
		return plural;
	}
	
	public String toString(){
		return form;
	}
}
