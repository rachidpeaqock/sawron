package com.tenor.tsf.gs.dao;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.Logger;

import com.tenor.tsf.gs.db.ReservationDB;
import com.tenor.tsf.gs.entity.Reservation;
import com.tenor.tsf.gs.entity.Salle;
import com.tenor.tsf.gs.exceptions.AllreadyExistException;
import com.tenor.tsf.gs.exceptions.AllreadyReservedException;
import com.tenor.tsf.gs.exceptions.DateExpection;
import com.tenor.tsf.gs.exceptions.EmptyFieldException;
import com.tenor.tsf.gs.exceptions.NotFoundException;

public class ReservationDao implements AbstractDAO<Reservation> {
	private static Long count = 0l;
	private static Logger LOGGER = Logger.getLogger(ReclamationDao.class);

	public Reservation create(Reservation resv) {
		Validate.notNull(resv, "object given is null");
		Reservation reservation = getById(resv.getId());
		LOGGER.info( reservation);
		if (reservation != null)
			throw new AllreadyExistException("reseravation "+resv.getId()+" Allreday exist");
		else if (resv.getSalle() == null)
			throw new EmptyFieldException("salle given is null");
		else if (resv.getUser() == null)
			throw new EmptyFieldException("usr given is null");
		else if (resv.getDateDebut() == null)
			throw new EmptyFieldException("debut date is null ");
		else if (resv.getDateFin() == null)
			throw new EmptyFieldException("fin date is null");
		else if (resv.getDateDebut().isBefore(LocalDate.now()) || resv.getDateFin().isBefore(LocalDate.now()))
			throw new DateExpection("one of dates are before current date");
		else if (resv.getDateFin().isBefore(resv.getDateDebut()))
			throw new DateExpection("fin date cant be before debt date");
		else if (getResBySalleAndDate(resv.getSalle(), resv.getDateDebut(), resv.getDateFin()) != null)
			throw new AllreadyReservedException("this reservation wanted is unavailable");

		count += 1L;
		resv.setId(count);
		ReservationDB.reservations.add(resv);
		reservation= this.getById(resv.getId());
		LOGGER.info( reservation);

		return reservation;

	}

	public void update(Reservation resv) {
		Validate.notNull(resv, "object given is null");
		Reservation reservation = getById(resv.getId());
		if (reservation == null)
			throw new NotFoundException("reseravation "+resv.getId()+"not found");
		LOGGER.info( reservation);
		int index = ReservationDB.reservations.indexOf(reservation);
		ReservationDB.reservations.set(index, resv);
		reservation=getById(resv.getId());
		LOGGER.info( reservation);

	}

	public void delete(Reservation resv) {
		Validate.notNull(resv, "object given is null");
		Reservation reservation = getById(resv.getId());
		if (reservation == null)
			throw new NotFoundException("reseravation "+resv.getId()+"not found");
		LOGGER.info( reservation);
		ReservationDB.reservations.remove(reservation);
	}

	public List<Reservation> getAll() {
		return ReservationDB.reservations;
	}

	public Reservation getById(Long Id) {
		Reservation reservation = null;
		for (Reservation res : ReservationDB.reservations) {
			if (res.getId() == Id) {
				reservation = res;
			}
		}
		LOGGER.info( reservation);
		return reservation;
	}

	public Reservation getByUserId(Long Id) {
		Reservation reservation = null;
		for (Reservation res : ReservationDB.reservations) {
			if (res.getUser().getId() == Id) {
				reservation = res;
			}
		}
		LOGGER.info( reservation);

		return reservation;
	}

	public Reservation getByUserAndSalle(Long user, Long salle) {
		Reservation reservation = null;
		for (Reservation res : ReservationDB.reservations) {
			if (res.getUser().getId() == user && res.getSalle().getId() == salle) {
				reservation = res;
			}
		}
		LOGGER.info( reservation);

		return reservation;
	}

	public Reservation getResBySalleAndDate(Salle salle, LocalDate dateDebut, LocalDate dateFin) {
		Reservation reservation = null;
		for (Reservation res : ReservationDB.reservations) {
			if ((((res.getDateDebut().isEqual(dateDebut) || res.getDateDebut().isBefore(dateDebut))
					&& res.getDateFin().isAfter(dateDebut))) || res.getDateFin().isAfter(dateFin)) {
				if (res.getSalle().getId() == salle.getId())
					reservation = res;
			}
		}
		LOGGER.info( reservation);
		return reservation;
	}
}
