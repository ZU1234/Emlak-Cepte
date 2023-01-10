package com.emlakcepte.listener;

import com.emlakcepte.model.Invoice;
import com.emlakcepte.model.Realty;
import com.emlakcepte.model.RealtyProduct;
import com.emlakcepte.model.User;
import com.emlakcepte.repository.InvoiceRepository;
import com.emlakcepte.repository.RealtyRepository;
import com.emlakcepte.repository.UsersAndProductsRepository;
import com.emlakcepte.request.RealtyStatusUpdateRequest;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private RealtyRepository realtyRepository;
    @Autowired
    private UsersAndProductsRepository usersAndProductsRepository;


    @RabbitListener(queues = "emlakcepte.notification.usersAndProducts")
    public void payment(RealtyProduct realtyProduct) {
        usersAndProductsRepository.save(realtyProduct);
    }

    @RabbitListener(queues = "emlakcepte.notification")
    public void notificationListener(User user) {
        System.out.println("User kuyruktan okundu." + user.getMail());
    }

    @RabbitListener(queues = "emlakcepte.notification.invoice")
    public void invoiceSave(Invoice invoice) {
        invoiceRepository.save(invoice);
        System.out.println("fatura kaydı tamam.");
    }

    //Ilanlanı durumunu aktif yada pasif hale getirir.
    @RabbitListener(queues = "emlakcepte.notification.status")
    public void updateRealtyStatus(RealtyStatusUpdateRequest realtyStatusUpdateRequest) {
        Realty selectedRealty = realtyRepository.findById(realtyStatusUpdateRequest.getId()).orElse(null);

        if (selectedRealty != null) {
            selectedRealty.setStatus(realtyStatusUpdateRequest.getStatus());

            realtyRepository.save(selectedRealty);
        }
    }

    @RabbitListener(queues = "emlakcepte.notification.invoice")
    public void createInvoice() {
        System.out.println("oldu");
    }


}
