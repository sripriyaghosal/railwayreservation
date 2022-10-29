package com.capg.micro.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.capg.micro.entity.TimeTable;

@Repository
public interface TIME_TABLE extends MongoRepository<TimeTable, String>{
	@Query("{date: {$gte: ?0}}")
	public List<TimeTable> availableTrains(Date date);
	
	@Query("{date_string: ?0}")
	public TimeTable getTrain(String date_string);
	
	@Query(value="{date :{$lt: ?0}}", delete = true)
	public void deleteRecord(Date date);

	
}
