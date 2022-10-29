package com.capg.micro.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.capg.micro.entity.Train;
@Repository
public interface TrainRepo extends MongoRepository<Train, Integer> {
	@Query("{from_station: ?0, to_station: ?1}")
	List<Train> findtrains(String from, String to);
	
	@Query("{train_name: /?0/i}")
	List<Train> trainByName(String name);
}
