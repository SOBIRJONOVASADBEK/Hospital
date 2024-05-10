package com.demo.repository;

import com.demo.domain.Doktor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoktorRepository extends JpaRepository<Doktor,Long> {

}
