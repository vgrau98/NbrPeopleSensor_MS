package com.insa.projet.microservices.nbrPeople_MS.model;

import java.util.ArrayList;
import java.util.List;



/**
 * 
 * @author grau
 * This class allows to implement a database of people sensors
 *
 */
public class DataBase {
	
	private List<NbrPeopleSensor> listSensors;

	public DataBase() {
		listSensors=new ArrayList<NbrPeopleSensor>();
			}

	public List<NbrPeopleSensor> getListSensors() {
		return listSensors;
	}

	public void setListSensors(List<NbrPeopleSensor> listSensors) {
		this.listSensors = listSensors;
	}
	

}
