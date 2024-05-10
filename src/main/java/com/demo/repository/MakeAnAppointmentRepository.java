package com.demo.repository;

import com.demo.domain.MakeAnAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakeAnAppointmentRepository extends JpaRepository<MakeAnAppointment,Long> {
}
