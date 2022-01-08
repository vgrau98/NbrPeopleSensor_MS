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


@RestController
public class NbrPeopleSensorRessource {
	
	public DataBase db = new DataBase();

	@PostMapping(path = "init/{n}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<NbrPeopleSensor> initDataBase(@PathVariable int n) {
		db.initDataBase(n);
		return db.getListSensors();
	}

	@GetMapping("/list")
	public List<NbrPeopleSensor> getListSensors() {
		for (int i = 0; i < db.getListSensors().size(); i++) {
			System.out.println(db.getListSensors().get(i));
		}
		return db.getListSensors();
	}
	
	@PostMapping(path="/addSensor", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void addSensor(@RequestBody NbrPeopleSensor sensor) {
		
		db.getListSensors().add(sensor);
		
	}

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
