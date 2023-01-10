package com.emlakcepte.service;

import com.emlakcepte.client.PaymentServiceClient;
import com.emlakcepte.configuration.EmlakcepteEmailQueue;
import com.emlakcepte.configuration.EmlakcepteInvoiceQueue;
import com.emlakcepte.configuration.RabbitMQConfiguration;
import com.emlakcepte.controller.UserController;
import com.emlakcepte.converter.UserConverter;
import com.emlakcepte.dao.InvoiceRepository;
import com.emlakcepte.dao.UserRepository;
import com.emlakcepte.dao.UsersAndProductsRepository;
import com.emlakcepte.model.Invoice;
import com.emlakcepte.model.ProductAndUserId;
import com.emlakcepte.model.RealtyProduct;
import com.emlakcepte.model.User;
import com.emlakcepte.request.PaymentRequest;
import com.emlakcepte.request.UserRequest;
import com.emlakcepte.response.UserResponse;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {
    protected final int REALTY_PRODUCT_AMOUNT = 10;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Autowired
    private RabbitMQConfiguration rabbitMQConfiguration;
    @Autowired
    private EmlakcepteEmailQueue emlakcepteEmailQueue;
    @Autowired
    private EmlakcepteInvoiceQueue emlakcepteInvoiceQueue;
    @Autowired
    private UserConverter converter;
    @Autowired
    private PaymentServiceClient paymentServiceClient;

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private UsersAndProductsRepository usersAndProductsRepository;

    //user oluşturur.
    public UserResponse createUser(UserRequest userRequest) {

        User savedUser = userRepository.save(converter.convert(userRequest));

        Logger logger = Logger.getLogger(UserController.class.getName());
        logger.log(Level.INFO, "[createUser] - user created: {0}", savedUser);

        rabbitTemplate.convertAndSend(rabbitMQConfiguration.getQueueName(), userRequest);


        logger.log(Level.INFO, "[createUser] - userRequest:{0}, sent to: {1}", new Object[]{userRequest.getMail(),
                rabbitMQConfiguration.getQueueName()});

        return converter.convert(savedUser);
    }

    //Tüm userları listeler
    public List<UserResponse> getAll() {
        return converter.convert(userRepository.findAll());
    }

    //Email e göre kullanıcıyı getirir.
    public UserResponse getByEmail(String email) {
        return converter.convert(userRepository.findByMail(email));
    }

    public Optional<User> getById(Integer userId) {
        return userRepository.findById(userId);
    }

    public Invoice payment(PaymentRequest paymentRequest) {
        System.out.println("Invoice :: " + paymentRequest.toString());
        //usersAndProductsRepository.save(converter.convertRealtyProduct(paymentRequest));
        return paymentServiceClient.payment(paymentRequest);

    }

    protected void createInvoice(PaymentRequest paymentRequest) {
        Logger logger = Logger.getLogger(UserService.class.getName());
        /*kuyruğa atar. henüz database'e kayıt yapılmadı notification servise bu işlemi kuyruktan okuyarak
         yapacak.**/
        System.out.println(emlakcepteInvoiceQueue.getQueueName() + "------");
        rabbitTemplate.convertAndSend(emlakcepteInvoiceQueue.getQueueName(), paymentRequest);
        logger.log(Level.INFO, "[invoice] fatura kuyruğa eklendi");
    }

    public List<Invoice> getAllInvoice() {
        return invoiceRepository.findAll();
    }


    public List<RealtyProduct> getProductByUser(ProductAndUserId productAndUserId) {
        return usersAndProductsRepository.findAllById(Collections.singleton(productAndUserId));
    }
}


