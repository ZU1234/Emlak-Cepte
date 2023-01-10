package com.emlakcepte.dao;

import com.emlakcepte.model.Realty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealtyRepository extends JpaRepository<Realty, Integer> {


    List<Realty> findAllByUserId(Integer id);


}
