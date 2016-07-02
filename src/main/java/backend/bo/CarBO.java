package backend.bo;

import java.util.List;

import backend.entities.Car;


public interface CarBO {
	
	List<Car> findAll();

    Car findOne(Long id);

    Car create(Car car);

    Car update(Car car);

    void delete(Long id);

}
