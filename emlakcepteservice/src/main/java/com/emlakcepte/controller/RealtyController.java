package com.emlakcepte.controller;


import com.emlakcepte.converter.RealtyConverter;
import com.emlakcepte.enums.RealtyType;
import com.emlakcepte.request.RealtyRequest;
import com.emlakcepte.request.RealtyStatusUpdateRequest;
import com.emlakcepte.response.RealtyResponse;
import com.emlakcepte.service.RealtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/realtyes")
public class RealtyController {
    @Autowired
    private RealtyService realtyService;
    @Autowired
    private RealtyConverter converter;

    //Tüm ilanları getirir.
    @GetMapping
    public ResponseEntity<List<RealtyResponse>> getAll() {
        return ResponseEntity.ok(realtyService.getAll());
    }

    //ilan oluşturur.
    @PostMapping
    public ResponseEntity<RealtyResponse> create(@RequestBody RealtyRequest realtyRequest) {
        Logger logger = Logger.getLogger(RealtyController.class.getName());
        RealtyResponse realtyResponse = realtyService.createRealty(realtyRequest);
        logger.log(Level.INFO, "realty created : {0}", realtyResponse);
        return ResponseEntity.ok(realtyResponse);
    }

    //Kullanıcıya ait ilanları listeler
    @GetMapping(value = "/{id}")
    public ResponseEntity<List<RealtyResponse>> getAllByUserId(@PathVariable int userId) {
        List<RealtyResponse> realtyes = realtyService.findAllByUserId(userId);
        return ResponseEntity.ok(realtyes);
    }


    //İlanın durumuna göre ilanları listeler.(örn: active,passive...)--->
    @GetMapping(value = "/status")
    public ResponseEntity<List<RealtyResponse>> getAllPassiveRealtyes(@RequestBody RealtyType realtyType) {
        List<RealtyResponse> realtyResponses = realtyService.getAllByStatus(realtyType);
        return ResponseEntity.ok(realtyResponses);
    }

    //ilan silme işlemi yapar database üzerindende siler. ilan no ile gerçekleştirilir.
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<RealtyResponse> deleteRealty(@PathVariable int id) {
        RealtyResponse realtyResponse = realtyService.deleteRealtyByNo(id);
        return ResponseEntity.ok(realtyResponse);
    }

    //Ilan güncelleme işlemi yapar
    @PutMapping
    public ResponseEntity<RealtyResponse> update(@RequestBody RealtyRequest realtyRequest) {
        RealtyResponse realtyResponse = realtyService.updateRealty(realtyRequest);

        return ResponseEntity.ok(realtyResponse);
    }

    //Ilanlanı durumunu aktif yada pasif hale getirir.
    @PutMapping(value = "/status")
    public ResponseEntity<Boolean> updateRealtyStatus(@RequestBody RealtyStatusUpdateRequest realtyStatusUpdateRequest) {
        return ResponseEntity.ok(realtyService.updateRealtyStatus(realtyStatusUpdateRequest));


    }

}
