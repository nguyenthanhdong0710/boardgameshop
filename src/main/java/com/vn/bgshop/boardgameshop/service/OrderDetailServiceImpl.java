package com.vn.bgshop.boardgameshop.service;

import com.vn.bgshop.boardgameshop.entity.OrderDetail;
import com.vn.bgshop.boardgameshop.repository.OrderDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    @Qualifier("orderDetailRepoImpl")
    private OrderDetailRepo OrderDetailRepo;


    @Override
    public List<OrderDetail> findByGameId(int id) {
        return OrderDetailRepo.findByGameId(id);
    }

    @Override
    public List<OrderDetail> findByOrderId(int id) {
        return OrderDetailRepo.findByOrderId(id);
    }


    @Override
    public List<OrderDetail> findAll() {
        return OrderDetailRepo.findAll();
    }



    @Override
    public OrderDetail findByBothId(int orderId, int gameId) {
        return OrderDetailRepo.findByBothId(orderId,gameId);
    }

    @Override
    public void save(OrderDetail model) {
        OrderDetailRepo.save(model);
    }

    @Override
    public void remove(int orderId, int gameId) {
        OrderDetailRepo.remove(orderId,gameId);
    }

    @Override
    public void removeAllByOrder(int orderId) {

    }


    @Override
    public void remove(int id) {

    }

    @Override
    public void update(int id, OrderDetail model) {

    }


}
