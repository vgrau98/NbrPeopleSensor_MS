package com.insa.projet.microservices.nbrPeople_MS.controller;

import java.util.List;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insa.projet.microservices.nbrPeople_MS.model.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




/**
 * 
 * @author grau
 * Expose resources for managing a database of people sensors each with an ID, a room and a list of values representing the history. 
 */
@RestController
public class NbrPeopleSensorRessource {
	
	public DataBase db = new DataBase();


	/**
	 * @return The list of people sensors
	 */
	@GetMapping("/list")
	public List<NbrPeopleSensor> getListSensors() {
		for (int i = 0; i < db.getListSensors().size(); i++) {
			System.out.println(db.getListSensors().get(i));
		}
		return db.getListSensors();
	}
	
	
	/**
	 * Add a sensor in the database
	 * @param sensor, see the NbrPeopleSensor class
	 */
	@PostMapping(path="/addSensor", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void addSensor(@RequestBody NbrPeopleSensor sensor) {
		
		db.getListSensors().add(sensor);
		
	}

	
	/**
	 * Retrieve a sensor according to its ID
	 * @param id
	 * @return The sensor, or null if the id doesn't exist
	 */
	@GetMapping("/id/{id}")
	public NbrPeopleSensor getNbrPeopleSensorID(@PathVariable("id") int id) {

		int index = -1;
		NbrPeopleSensor sensor=null;
		for (int i = 0; i < db.getListSensors().size(); i++) {
			if (db.getListSensors().get(i).getId() == id) {
				index = i;
			}
		}
		
		if(index != -1) {
			sensor=db.getListSensors().get(index);
		}
		return sensor;
	}
	
	/**
	 * 
	 * @param id
	 * @param timestamp
	 * @return true if the value has already been measured, or false otherwise
	 */
	@GetMapping("/isMeasured/{id}/{timestamp}")
	public boolean alreadyMeasured(@PathVariable ("id") int id,@PathVariable ("timestamp") long timestamp) {
		
		boolean measured=false;
		NbrPeopleSensor sensor = getNbrPeopleSensorID(id);
		for(NbrPeopleValue v : sensor.getValues()) {
			if(v.getTimestamp()==timestamp) {
				measured=true;
			}
		}
		return measured;
	}

	
	/**
	 * 
	 * @param room
	 * @return a sensor according to its room
	 */
	@GetMapping("/room/{room}")
	public NbrPeopleSensor getNbrPeopleSensorRoom(@PathVariable("room") int room) {

		int index = -1;
		for (int i = 0; i < db.getListSensors().size(); i++) {
			if (db.getListSensors().get(i).getRoom() == room) {
				index = i;
			}
		}
		return db.getListSensors().get(index);
	}

	
	/**
	 * 
	 * @param id, The sensor to which we want to add the value
	 * @param value, see NbrPeopleValue class
	 */
	@PostMapping(path = "addValue/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void addValueSensor(@PathVariable("id") int id, @RequestBody NbrPeopleValue value) {
		int index = -1;
		for (int i = 0; i < db.getListSensors().size(); i++) {
			if (db.getListSensors().get(i).getId() == id) {
				index = i;
			}
		}

		db.getListSensors().get(index).addValue(value);
	}

}
