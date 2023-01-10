package com.emlakcepte.service;

import com.emlakcepte.client.BannerServiceClient;
import com.emlakcepte.client.model.Banner;
import com.emlakcepte.configuration.EmlakcepteRealtyQueue;
import com.emlakcepte.converter.RealtyConverter;
import com.emlakcepte.dao.RealtyRepository;
import com.emlakcepte.dao.UsersAndProductsRepository;
import com.emlakcepte.enums.ProductType;
import com.emlakcepte.enums.RealtyType;
import com.emlakcepte.enums.UserType;
import com.emlakcepte.model.ProductAndUserId;
import com.emlakcepte.model.Realty;
import com.emlakcepte.model.RealtyProduct;
import com.emlakcepte.model.User;
import com.emlakcepte.request.RealtyRequest;
import com.emlakcepte.request.RealtyStatusUpdateRequest;
import com.emlakcepte.response.RealtyResponse;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RealtyService {
    private static final int MAX_INDIVIDUAL_REALTY_SIZE = 5;
    @Autowired
    private UserService userService;
    @Autowired
    private RealtyRepository realtyRepository;
    @Autowired
    private UsersAndProductsRepository usersAndProductsRepository;
    @Autowired
    private BannerServiceClient bannerServiceClient;
    @Autowired
    private RealtyConverter converter;
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private EmlakcepteRealtyQueue emlakcepteRealtyQueue;

    public RealtyResponse createRealty(RealtyRequest realtyRequest) {
        Logger logger = Logger.getLogger(RealtyService.class.getName());
        Optional<User> foundUser = userService.getById(realtyRequest.getUserId());

        //Eğer kullanıcı yok ise ilan kayıt edemez.
        if (foundUser.isEmpty()) {

            logger.log(Level.WARNING, "Kullanıcı bulunamadı. userId : {0} ",
                    realtyRequest.getUserId());

            return null;
        }

        if (UserType.INDIVIDUAL.equals(foundUser.get().getType())) {
            List<Realty> realtyList = realtyRepository.findAllByUserId(foundUser.get().getId());
            if (realtyList.size() == MAX_INDIVIDUAL_REALTY_SIZE) {

                logger.log(Level.WARNING, "Bireysel kullanıcılar en fazla 5 ilan girebilir. userID : {0} ",
                        foundUser.get().getId());
            }

        }


        Realty realty = converter.convert(realtyRequest);
        //ilan oluşturulurken varsayılan olarak durumu IN_REVIEW olarak ayarlanır.
        realty.setStatus(RealtyType.IN_REVIEW);
        realty.setUser(foundUser.get());

        //kullanıcı paket satın aldıysa ve ilan yayınlayabilir.
        //Todo son kullanma tarihini dikkate al

        RealtyProduct realtyProduct = usersAndProductsRepository.findById(new ProductAndUserId(realtyRequest.getUserId(),
                ProductType.REALTY_CREATE)).orElse(null);


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        //kullanıcının ilan yayınlamak için istenilen paketi mevcutmu diye kontrol edilir.
        if (realtyProduct != null) {


            Date recordDate;
            Date now;
            LocalDate localDate = LocalDate.now();
            try {

                recordDate = simpleDateFormat.parse(String.valueOf(realtyProduct.getPackageEndDate()));


                int month = localDate.getMonthValue();
                int day = localDate.getDayOfMonth();
                int year = localDate.getYear();

                now = simpleDateFormat.parse(day + "." + month + "." + year);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //Paket bitiş süresi kontrol edilir. eğer paketin süresi dolmadıysa ilan yayınlanır.
            if (now.getTime() < recordDate.getTime()) {
                realtyRepository.save(realty);

                System.out.println("createRealty :: " + realty);
                Banner bannerRequest = new Banner(String.valueOf(realty.getNo()), 1, "1234567890", "");
                bannerServiceClient.create(bannerRequest);

                return converter.convert(realty);

            }

        }

        return null;
    }

    public List<RealtyResponse> getAll() {
        return converter.convert(realtyRepository.findAll());
    }

    public List<RealtyResponse> getAllByProvince(String province) {

        List<Realty> realtyList = realtyRepository.findAll()
                .stream()
                .filter(realty -> realty.getProvince().equals(province))
                //.count();
                .toList();
        return converter.convert(realtyList);

    }

    public Long getAllByProvinceCount(String province) {

        return realtyRepository.findAll()
                .stream()
                .filter(realty -> realty.getProvince().equals(province))
                .count();
    }

    public List<RealtyResponse> findAllByUserId(int id) {
        return getAll().stream()
                .filter(realty -> realty.getId().equals(id))
                .toList();
    }

    //İlanın durumuna göre ilanları listeler.(örn: active,passive...)
    public List<RealtyResponse> getAllByStatus(RealtyType realtyType) {
        return getAll().stream()
                .filter(realty -> realty.getStatus().equals(realtyType))
                .toList();
    }

    public RealtyResponse findById(int id) {
        return getAll().stream().filter(realty -> id == realty.getId())
                .findAny().orElse(null);
    }

    private Realty findByNo(int no) {
        return realtyRepository.findAll().stream().filter(realty -> no == realty.getNo())
                .findAny().orElse(null);
    }

    //ilan no ya göre ilan silme işlemi yapar.
    public RealtyResponse deleteRealtyByNo(int id) {
        RealtyResponse selectRealty = findById(id);
        if (selectRealty != null) {
            realtyRepository.deleteById(id);
        }
        return selectRealty;
    }

    //ilan güncelleme işlemi yapar
    public RealtyResponse updateRealty(RealtyRequest realtyRequest) {
        Realty selectRealty = findByNo(realtyRequest.getNo());

        if (selectRealty != null) {

            selectRealty.setTitle(realtyRequest.getTitle());
            selectRealty.setCreateDate(LocalDateTime.now());
            selectRealty.setProvince(realtyRequest.getProvince());
            System.out.println(selectRealty);
            realtyRepository.save(selectRealty);
            return converter.convert(selectRealty);

        }
        return null;
    }

    //Ilan durumunu aktif yada pasif hale getirilmesi için kuyruğa atar.
    public boolean updateRealtyStatus(RealtyStatusUpdateRequest realtyStatusUpdateRequest) {
        Logger logger = Logger.getLogger(RealtyService.class.getName());
        if (!RealtyType.IN_REVIEW.equals(realtyStatusUpdateRequest.getStatus())) {
            rabbitTemplate.convertAndSend(emlakcepteRealtyQueue.getQueueName(), realtyStatusUpdateRequest);
            logger.log(Level.INFO, "[emlakcepteRealtyQueue] - status update: {0}",
                    realtyStatusUpdateRequest.getStatus());
            return true;
        }

        logger.log(Level.WARNING, "[emlakcepteRealtyQueue] - It is added to the queue only in the case of " +
                        "\"active\" and \"passive\".: {0}",
                realtyStatusUpdateRequest.getStatus());
        converter.convert(realtyRepository.findById(realtyStatusUpdateRequest.getId()).get());
        return false;
    }


}
