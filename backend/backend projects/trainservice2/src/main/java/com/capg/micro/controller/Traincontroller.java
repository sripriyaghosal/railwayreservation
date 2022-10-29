package com.capg.micro.controller;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.micro.entity.Req;
import com.capg.micro.entity.TimeTable;
import com.capg.micro.entity.Train;
import com.capg.micro.repository.TIME_TABLE;
import com.capg.micro.repository.TrainRepo;
@RestController
@RequestMapping("trainSearch")
public class Traincontroller {

	@Autowired
	private TrainRepo t_repo;
    @Autowired
    private TIME_TABLE tt;
	@PostMapping("/saveTrain")
	public Train saveTrain(@RequestBody Train train) {
		Train train1 = t_repo.save(train);
		return train1;
	}
	@GetMapping("/getById/{id}")
	public Train getById(@PathVariable("id") int id)
	{
		return t_repo.findById(id).get();
	}
	@GetMapping("/findByTrainName/{name}")
	public List<Train> getName(@PathVariable("name") String name)
	{
		return t_repo.trainByName(name);
	}
	@GetMapping("/getTrains/getExactTrain/{date}/{from}/{to}")
	public TimeTable getExactTrains(@PathVariable("date") String date,
			@PathVariable("from") String from, @PathVariable("to") String to) throws ParseException
	{
		TimeTable timetable = tt.getTrain(date);
			List<Train> trainList = new ArrayList<Train>();
			for(Train tr: timetable.getTrains())
			{
				if(tr.getFrom_station().equalsIgnoreCase(from) && tr.getTo_station().equalsIgnoreCase(to))
				{
					trainList.add(tr);
				}
			}
			timetable.setTrains(trainList);
		return timetable;
	}
	@GetMapping("/getTrainByStations/{fromStation}/{toStation}")
	public List<Train> getTrains(@PathVariable("fromStation") String from, @PathVariable("toStation") String to) {
		try {
			List<Train> list = t_repo.findtrains(from, to);
			return list;
		} catch (NullPointerException Ex) {
			System.out.println(Ex.getMessage());
		}
		return null;
	}
	
	@PutMapping("updateById/{id}")
	public Train updateTrain(@PathVariable("id") int id, @RequestBody Train train0)
	{
		Train oldTr = t_repo.findById(id).get();
		oldTr.setFrom_station(train0.getFrom_station());
		oldTr.setTo_station(train0.getTo_station());
		oldTr.setTotal_Seat_ac1(train0.getTotal_Seat_ac1());
		oldTr.setTotal_Seat_ac2(train0.getTotal_Seat_ac2());
		oldTr.setTotal_Seat_ac3(train0.getTotal_Seat_ac3());
		oldTr.setTotal_Seat_sleeper(train0.getTotal_Seat_sleeper());
		oldTr.setTrain_name(train0.getTrain_name());
		t_repo.save(oldTr);
		return oldTr;
	}
	
	@DeleteMapping("/delete/{id}")
	public String deletebyid(@PathVariable("id") int id)
	{
		t_repo.deleteById(id);
		return "Deleted if found";
	}
// time table controller starts here
	@GetMapping("/getTrains/DateFromTo/{date}/{from}/{to}")
	public List<TimeTable> getTrainFrom(@PathVariable("date") String date,
			@PathVariable("from") String from, @PathVariable("to") String to) throws ParseException
	{
		// SimpleDateFormat.parse method converts the date from string to date type
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = formatter.parse(date);
		//tt here means a variable of train repository
		List<TimeTable> timetables = tt.availableTrains(date1);
		for(int i = 0; i < timetables.size(); i++)
		{
			List<Train> trainList = new ArrayList<Train>();
			for(Train tr: timetables.get(i).getTrains())
			{
				if(tr.getFrom_station().equalsIgnoreCase(from) && tr.getTo_station().equalsIgnoreCase(to))
				{
					trainList.add(tr);
				}
			}
			timetables.get(i).setTrains(trainList);
		}
		return timetables;
	}
	@PostMapping("/addTrainsToTable")
	public String modifyTable(@RequestBody Req request) throws ParseException
	{
		String datestring = request.getDatestring();
        System.out.println(datestring);
        Date today = new Date();
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//String today_string = dateFormat.format(today);
		  tt.deleteRecord(today);
		//System.out.println(delMsg);
		Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DATE,4);
        Date addedDate = c.getTime();
        System.out.println("Day is: "+addedDate.getDay());
       String NewDate = dateFormat.format(addedDate);
        System.out.println(addedDate);
        List<TimeTable> ttlist = tt.availableTrains(addedDate);
        if(ttlist.isEmpty())
        {
        	//Add the new Record
        	String days[] = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
        	String day_of_week = days[addedDate.getDay()];
        	List<Train> list1 = t_repo.findAll();
        	List<Train> list2 = new ArrayList<Train>();
        	for(Train trn : list1)
        	{
        		if(trn.getTrain_run_day().contains(day_of_week))
        		{
        			list2.add(trn);
        		}
        	}
        	TimeTable timeTable = new TimeTable();
        	
        	timeTable.setDate(NewDate);
        	timeTable.setTrains(list2);
        	timeTable.setDate_string(NewDate);
        	tt.save(timeTable);
        	
        	return "Time Table updated";
        }
        return "No changes required";
	}

@PutMapping("/changeSeat/Train/{today}")
public String updateSeats(@PathVariable("today") String today,@RequestBody Train train2) throws ParseException
{
	DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//	Date date1 = formatter.parse(today);
	TimeTable tt1 = tt.getTrain(today);
	List<Train> tlist = tt1.getTrains();
	for(Train train : tlist)
	{
		if(train.getTrain_id() == train2.getTrain_id())
		{
			train.setTotal_Seat_ac1(train2.getTotal_Seat_ac1());
			train.setTotal_Seat_ac2(train2.getTotal_Seat_ac2());
			train.setTotal_Seat_ac3(train2.getTotal_Seat_ac3());
			train.setTotal_Seat_sleeper(train2.getTotal_Seat_sleeper());
			break;
		}
	}
	tt1.setTrains(tlist);
	tt.save(tt1);
	return train2.getTrain_name()+"'s seat count for "+today+" is updated";
}
}

