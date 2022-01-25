package com.insa.projet.microservices.nbrPeople_MS.model;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author grau
 * This class allows to implement a people sensor
 */
public class NbrPeopleSensor {

	private int id;
	private int room;
	private List<NbrPeopleValue> values;

	public NbrPeopleSensor(int id, int room) {
		super();
		this.id = id;
		this.room = room;
		this.values = new ArrayList<NbrPeopleValue>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public List<NbrPeopleValue> getValues() {
		return values;
	}

	public void addValue(NbrPeopleValue value) {
		this.values.add(value);
	}

}
