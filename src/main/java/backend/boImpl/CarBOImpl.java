package backend.boImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import backend.bo.CarBO;
import backend.dao.CarDao;
import backend.entities.Car;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CarBOImpl implements CarBO {
	
	@Autowired
	private CarDao carDao;
	

	public CarDao getCarDao() {
		return carDao;
	}

	public void setCarDao(CarDao carDao) {
		this.carDao = carDao;
	}

	@Override
	public List<Car> findAll() {		
		return (List<Car>) carDao.findAll();
	}

	@Override
	public Car findOne(Long id) {		
		return carDao.findOne(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED , readOnly = false)
	public Car create(Car car) {
		
		// Ensure the entity object to be created does NOT exist in the
        // repository. Prevent the default behavior of save() which will update
        // an existing entity if the entity matching the supplied id exists.
		
        if (car.getId() != null) {
            // Cannot create Greeting with specified ID value
            return null;
        }

        Car savedCar = carDao.save(car);
        
        
        // ilustrate transtaction rollback
        if(savedCar.getId() == 4L) {
        	throw new RuntimeException("Roll me back");
        }
        
        return savedCar;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED , readOnly = false)
	public Car update(Car car) {
		
		// Ensure the entity object to be updated exists in the repository to
        // prevent the default behavior of save() which will persist a new
        // entity if the entity matching the id does not exist
		
        Car carToUpdate = findOne(car.getId());
        if (carToUpdate == null) {
            // Cannot update Greeting that hasn't been persisted
            return null;
        }

        carToUpdate.setModel(car.getModel());
        carToUpdate.setBrand(car.getBrand());        
        return carDao.save(carToUpdate);
        
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED , readOnly = false)
	public void delete(Long id) {		
		carDao.delete(id);
	}
	
}
