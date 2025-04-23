package com.example.dbs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // Import Query
import org.springframework.data.repository.query.Param; // Import Param

import com.example.dbs.model.Booking;
import com.example.dbs.model.BookingApproval;
import com.example.dbs.types.ApproverRole;

public interface BookingApprovalRepository extends JpaRepository<BookingApproval, Long> {

    @Query("SELECT ba FROM BookingApproval ba WHERE ba.booking = :booking")
    List<BookingApproval> findByBooking(@Param("booking") Booking booking);

    // Find specific approval for a booking and role (useful for checking duplicates)
    @Query("SELECT ba FROM BookingApproval ba WHERE ba.booking = :booking AND ba.approverRole = :approverRole")
    Optional<BookingApproval> findByBookingAndApproverRole(Booking booking, ApproverRole approverRole);
}
