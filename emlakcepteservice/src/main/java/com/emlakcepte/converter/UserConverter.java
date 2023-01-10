package com.emlakcepte.converter;

import com.emlakcepte.model.Invoice;
import com.emlakcepte.model.RealtyProduct;
import com.emlakcepte.model.User;
import com.emlakcepte.request.PaymentRequest;
import com.emlakcepte.request.UserRequest;
import com.emlakcepte.response.PaymentResponse;
import com.emlakcepte.response.UserResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Component
public class UserConverter {
    public UserResponse convert(User user) {
        UserResponse response = new UserResponse();
        response.setEmail(user.getMail());
        response.setId(user.getId());
        response.setName(user.getName());
        response.setType(user.getType());
        return response;
    }

    public User convert(UserRequest userRequest) {
        User user = new User();
        user.setMail(userRequest.getMail());
        user.setName(userRequest.getName());
        user.setPassword(userRequest.getPassword());
        user.setCreateDate(LocalDateTime.now());
        user.setType(userRequest.getType());
        return user;
    }

    public List<UserResponse> convert(List<User> userList) {
        return userList.stream().map(this::convert).toList();
    }

    public PaymentResponse convert(PaymentRequest paymentRequest) {
        System.out.println("paymentConvert :: "+paymentRequest.toString());
        PaymentResponse response=new PaymentResponse();

        response.setUserId(paymentRequest.getUserId());
        response.setPiece(paymentRequest.getPiece());
        response.setPrice(paymentRequest.getPrice());
        response.setProduct(paymentRequest.getProductType());
        return response;
    }
    public RealtyProduct convertRealtyProduct(PaymentRequest paymentRequest) {
        RealtyProduct response = new RealtyProduct();
        response.setUserId(paymentRequest.getUserId());
        response.setProductType(paymentRequest.getProductType());

        LocalDate localDate = LocalDate.now();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();
        int year = localDate.getYear();

        response.setPackageEndDate(day + "." + month + "." + year);
        return response;
    }


}
