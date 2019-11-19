package fr.nmocs.library.business.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.nmocs.library.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationQueue {

	private List<User> reservers = new ArrayList<>();

	private List<User> borrowers = new ArrayList<>();

	private Boolean isAvailable = false;

	private Integer queueMaxSize = 0;

	private Boolean isReservable = false;

	private Date soonestAvailabilityDate = new Date();

	private Date latestAvailabilityDate = new Date();

}
