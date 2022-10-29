package com.capg.micro.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Train")
public class Train {

	@Id
	@Field(name = "train_id")
	private int train_id;
	private int total_Seat_sleeper;
	private int total_Seat_ac3;
	private int total_Seat_ac2;
	private int total_Seat_ac1;
	private String train_name;
	private String from_station;
	private String to_station;
	private List<String> train_run_day;
	public Train(int train_id, int total_Seat_sleeper, int total_Seat_ac3, int total_Seat_ac2, int total_Seat_ac1,
			String train_name, String from_station, String to_station, List<String> train_run_day) {
		super();
		this.train_id = train_id;
		this.total_Seat_sleeper = total_Seat_sleeper;
		this.total_Seat_ac3 = total_Seat_ac3;
		this.total_Seat_ac2 = total_Seat_ac2;
		this.total_Seat_ac1 = total_Seat_ac1;
		this.train_name = train_name;
		this.from_station = from_station;
		this.to_station = to_station;
		this.train_run_day = train_run_day;
	}
public Train(){
	
}
public int getTrain_id() {
	return train_id;
}
public void setTrain_id(int train_id) {
	this.train_id = train_id;
}
public int getTotal_Seat_sleeper() {
	return total_Seat_sleeper;
}
public void setTotal_Seat_sleeper(int total_Seat_sleeper) {
	this.total_Seat_sleeper = total_Seat_sleeper;
}
public int getTotal_Seat_ac3() {
	return total_Seat_ac3;
}
public void setTotal_Seat_ac3(int total_Seat_ac3) {
	this.total_Seat_ac3 = total_Seat_ac3;
}
public int getTotal_Seat_ac2() {
	return total_Seat_ac2;
}
public void setTotal_Seat_ac2(int total_Seat_ac2) {
	this.total_Seat_ac2 = total_Seat_ac2;
}
public int getTotal_Seat_ac1() {
	return total_Seat_ac1;
}
public void setTotal_Seat_ac1(int total_Seat_ac1) {
	this.total_Seat_ac1 = total_Seat_ac1;
}
public String getTrain_name() {
	return train_name;
}
public void setTrain_name(String train_name) {
	this.train_name = train_name;
}
public String getFrom_station() {
	return from_station;
}
public void setFrom_station(String from_station) {
	this.from_station = from_station;
}
public String getTo_station() {
	return to_station;
}
public void setTo_station(String to_station) {
	this.to_station = to_station;
}
public List<String> getTrain_run_day() {
	return train_run_day;
}
public void setTrain_run_day(List<String> train_run_day) {
	this.train_run_day = train_run_day;
}
@Override
public String toString() {
	return "Train [train_id=" + train_id + ", total_Seat_sleeper=" + total_Seat_sleeper + ", total_Seat_ac3="
			+ total_Seat_ac3 + ", total_Seat_ac2=" + total_Seat_ac2 + ", total_Seat_ac1=" + total_Seat_ac1
			+ ", train_name=" + train_name + ", from_station=" + from_station + ", to_station=" + to_station
			+ ", train_run_day=" + train_run_day + "]";
}

}