package com.capg.booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.booking.entity.TrainBooking;
import com.capg.booking.repository.BookingRepo;

@Service
public class BookingService {

	@Autowired
	private BookingRepo book_repo;
	
	public TrainBooking bookTicket(TrainBooking booking)
	{
		return book_repo.save(booking);
	}
	
	public TrainBooking getTicket(String pnr)
	{
		return book_repo.findById(pnr).get();
	}
	
	public String deleteTicket(String pnr)
	{
		book_repo.deleteById(pnr);
		return "Ticket with PNR "+pnr+" is deleted";
	}
	
	public List<TrainBooking> findUserIdTickets(int id)
	{
		return book_repo.ticketByUserId(id);
	}
}