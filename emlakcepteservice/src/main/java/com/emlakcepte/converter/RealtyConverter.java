package com.emlakcepte.converter;

import com.emlakcepte.enums.RealtyType;
import com.emlakcepte.model.Realty;
import com.emlakcepte.request.RealtyRequest;
import com.emlakcepte.response.RealtyResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class RealtyConverter {

    public RealtyResponse convert(Realty realty) {
        RealtyResponse response = new RealtyResponse();
        response.setId(realty.getId());
        response.setNo(realty.getNo());
        response.setCreateDate(realty.getCreateDate());
        response.setProvince(realty.getProvince());
        response.setStatus(realty.getStatus());
        response.setTitle(realty.getTitle());
        response.setUserId(realty.getUser().getId());
        return response;
    }

    public Realty convert(RealtyRequest realtyRequest) {
        Realty realty = new Realty();
        realty.setNo(realtyRequest.getNo());
        realty.setCreateDate(LocalDateTime.now());
        realty.setStatus(RealtyType.PASSIVE);
        realty.setTitle(realtyRequest.getTitle());
        realty.setProvince(realtyRequest.getProvince());
        return realty;
    }

    public List<RealtyResponse> convert(List<Realty> realtyList) {
        return realtyList.stream().map(this::convert).toList();

    }

}


