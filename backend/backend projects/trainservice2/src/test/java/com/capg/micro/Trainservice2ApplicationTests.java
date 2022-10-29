package com.capg.micro;

//import static org.assertj.core.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.micro.entity.Train;
import com.capg.micro.repository.TrainRepo;

@SpringBootTest
class Trainservice2ApplicationTests {
	@Autowired
	private TrainRepo t_repo;
	@Test
	void contextLoads() {
		
			Optional<Train> train = t_repo.findById(13172);
			System.out.println(train.get().getTrain_id()+" "+train.get().getTrain_name());
			assertEquals(train.get().getTrain_id(),13172);
			System.out.println("Train 13172 present, Test Case 1 passed");
		
	}
	@Test
	void checkStations()
	{
		List<Train> lst = t_repo.findtrains("Sealdah", "New Jalpaiguri");
		for(Train train:lst)
		{
			assertTrue(train.getFrom_station().equals("Sealdah") && train.getTo_station().equals("New Jalpaiguri"));
		}
		System.out.println("Test Case 2 - Passed");
	}
	@Test
	void checkNotThere()
	{
		Optional<Train> train = t_repo.findById(12345);
		//System.out.println(train.get());
		System.out.println(train.isEmpty());
		assertTrue(train.isEmpty());
		System.out.println("Test Case not present check passed");

	}
	@Test
	void checkbyTrainName() {
		List<Train> trains = t_repo.trainByName("Nebula");
		//System.out.println(trains);
		for(Train train:trains)
		{
			System.out.println(train);
			assertTrue(train.getTrain_name().contains("Nebula"));
			
		}
		System.out.println("Test Case passed - Nebula Express present");
	}
}
