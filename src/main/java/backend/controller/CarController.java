package backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import backend.bo.CarBO;
import backend.entities.Car;


@RestController
public class CarController {
	
	@Autowired
	private CarBO carBO;
	
	
	public CarBO getCarBO() {
		return carBO;
	}


	public void setCarBO(CarBO carBO) {
		this.carBO = carBO;
	}


	@RequestMapping(
			value = "/api/cars", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Car>> getCars() {
		
		List<Car> cars = carBO.findAll();
		return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(
			value = "/api/cars/{id}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Car> getCar(@PathVariable("id") Long id) {
		
		Car car = carBO.findOne(id);
		if(car == null) {
			return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Car>(car, HttpStatus.OK);
		
	}
	
	
	@RequestMapping(
			value = "/api/cars", 
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Car> createCar(@RequestBody Car car) {
		
		Car savedCar = carBO.create(car);
		
		if(savedCar == null){			
			return new ResponseEntity<Car>(HttpStatus.CONFLICT);
		}
		else {
			return new ResponseEntity<Car>(savedCar, HttpStatus.CREATED);
		}
		
	}
	
	
	@RequestMapping(
            value = "/api/cars/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {

        Car updatedCar = carBO.update(car);
        if (updatedCar == null) {
            return new ResponseEntity<Car>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Car>(updatedCar, HttpStatus.OK);
    }
	

    @RequestMapping(
            value = "/api/cars/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> deleteCar(@PathVariable("id") Long id, @RequestBody Car car) {

        carBO.delete(id);
        return new ResponseEntity<Car>(HttpStatus.NO_CONTENT);
        
    }

}
