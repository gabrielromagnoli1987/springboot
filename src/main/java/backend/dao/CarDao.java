package backend.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import backend.entities.Car;


@Repository
public interface CarDao extends CrudRepository<Car, Long> {

}
