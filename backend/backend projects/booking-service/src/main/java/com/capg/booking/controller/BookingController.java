package com.capg.booking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.booking.entity.TrainBooking;
import com.capg.booking.service.BookingService;

@RestController
@RequestMapping("trainBooking")
public class BookingController {

	@Autowired
	private BookingService book_service;
	
	@GetMapping("findTicket/PNR/{pnr}")
	public TrainBooking find_ticket(@PathVariable("pnr") String pnr)
	{
		return book_service.getTicket(pnr);
	}
	
	@GetMapping("findUserSpecificTickets/{user_id}")
	public List<TrainBooking> fetchtickets(@PathVariable("user_id") int user_id)
	{
		return book_service.findUserIdTickets(user_id);
	}
	
	@PostMapping("bookTicket")
	public TrainBooking book_a_ticket(@RequestBody TrainBooking ticket)
	{
		return book_service.bookTicket(ticket);
	}
	
	@DeleteMapping("deleteTicket/PNR/{pnr}")
	public String delete_a_ticket(@PathVariable("pnr") String pnr)
	{
		return book_service.deleteTicket(pnr);
	}
}