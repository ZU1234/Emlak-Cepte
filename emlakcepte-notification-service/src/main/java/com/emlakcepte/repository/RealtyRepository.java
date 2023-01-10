package com.emlakcepte.repository;

import com.emlakcepte.model.Realty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RealtyRepository extends JpaRepository<Realty, Integer> {

}
