package com.vn.bgshop.boardgameshop.repository;

import com.vn.bgshop.boardgameshop.entity.CartDetail;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CartDetailRepoImpl implements CartDetailRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CartDetail> findAll() {
        String query = "select cd from CartDetail cd";
        TypedQuery<CartDetail> cartDetailTypedQuery = entityManager.createQuery(query, CartDetail.class);
        return cartDetailTypedQuery.getResultList();
    }

    @Override
    public List<CartDetail> findByGameId(int id) {
        String query = "select cd from CartDetail cd where cd.game.id ='" + id + "'";
        TypedQuery<CartDetail> cartDetailTypedQuery = entityManager.createQuery(query, CartDetail.class);
        return cartDetailTypedQuery.getResultList();
    }

    @Override
    public List<CartDetail> findByUserId(int id) {
        String query = "select cd from CartDetail cd where cd.user.id ='" + id + "'";
        TypedQuery<CartDetail> cartDetailTypedQuery = entityManager.createQuery(query, CartDetail.class);
        return cartDetailTypedQuery.getResultList();
    }

    @Override
    public CartDetail findByBothId(int userId, int gameId) {
        String query = "select cd from CartDetail cd where cd.user.id ='" + userId + "' and  cd.game.id = '"+gameId+"'";
        TypedQuery<CartDetail> cartDetailTypedQuery = entityManager.createQuery(query, CartDetail.class);
        return cartDetailTypedQuery.getResultList().isEmpty()?null:cartDetailTypedQuery.getResultList().get(0);
    }

    @Override
    public void save(CartDetail model) {
        CartDetail cartDetail = findByBothId(model.getUser().getId(),model.getGame().getId());
            if (cartDetail != null) {
                entityManager.merge(model);
                return;
            }else {
                //add new
                entityManager.persist(model);
            }
    }


    @Override
    public void remove(int userId, int gameId) {
        String query = "delete from CartDetail cd where cd.user.id ='" + userId + "' and  cd.game.id = '"+gameId+"'";
        entityManager.createQuery(query).executeUpdate();
    }

    @Override
    public void removeAllByUser(int userId) {
        String query = "delete from CartDetail cd where cd.user.id ='" + userId + "'";
        entityManager.createQuery(query).executeUpdate();
    }

    @Override
    public void remove(int id) {
    }

    @Override
    public void update(int id, CartDetail model) {

    }


    @Override
    public CartDetail findById(int id) {
        return null;
    }

}

