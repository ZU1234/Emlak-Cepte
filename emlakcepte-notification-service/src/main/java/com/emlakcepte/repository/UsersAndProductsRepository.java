package com.emlakcepte.repository;

import com.emlakcepte.model.ProductAndUserId;
import com.emlakcepte.model.RealtyProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersAndProductsRepository extends JpaRepository<RealtyProduct, ProductAndUserId> {

}
