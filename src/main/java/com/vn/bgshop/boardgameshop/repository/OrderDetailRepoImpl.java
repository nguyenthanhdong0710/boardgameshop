package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.OrderDetail;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OrderDetailRepoImpl implements OrderDetailRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrderDetail> findAll() {
        String query = "select od from OrderDetail od";
        TypedQuery<OrderDetail> orderDetailTypedQuery = entityManager.createQuery(query, OrderDetail.class);
        return orderDetailTypedQuery.getResultList();
    }

    @Override
    public List<OrderDetail> findByGameId(int id) {
        String query = "select od from OrderDetail od where od.game.id ='" + id + "'";
        TypedQuery<OrderDetail> orderDetailTypedQuery = entityManager.createQuery(query, OrderDetail.class);
        return orderDetailTypedQuery.getResultList();
    }

    @Override
    public List<OrderDetail> findByOrderId(int id) {
        String query = "select od from OrderDetail od where od.order.id ='" + id + "'";
        TypedQuery<OrderDetail> orderDetailTypedQuery = entityManager.createQuery(query, OrderDetail.class);
        return orderDetailTypedQuery.getResultList();
    }

    @Override
    public OrderDetail findByBothId(int orderId, int gameId) {
        String query = "select od from OrderDetail od where od.order.id ='" + orderId + "' and  od.game.id = '"+gameId+"'";
        TypedQuery<OrderDetail> orderDetailTypedQuery = entityManager.createQuery(query, OrderDetail.class);
        return orderDetailTypedQuery.getResultList().isEmpty()?null:orderDetailTypedQuery.getResultList().get(0);
    }

    @Override
    public void save(OrderDetail model) {
        OrderDetail orderDetail = findByBothId(model.getOrder().getId(),model.getGame().getId());
            if (orderDetail != null) {
                entityManager.merge(model);
                return;
            }else {
                //add new
                entityManager.persist(model);
            }
    }


    @Override
    public void remove(int userId, int gameId) {
        String query = "delete from OrderDetail od where od.order.id ='" + userId + "' and  od.game.id = '"+gameId+"'";
        entityManager.createQuery(query).executeUpdate();
    }

    @Override
    public void removeAllByOrder(int orderId) {
        String query = "delete from OrderDetail od where od.order.id ='" + orderId + "'";
        entityManager.createQuery(query).executeUpdate();
    }


    @Override
    public void remove(int id) {
    }

    @Override
    public void update(int id, OrderDetail model) {

    }


    @Override
    public OrderDetail findById(int id) {
        return null;
    }

}

