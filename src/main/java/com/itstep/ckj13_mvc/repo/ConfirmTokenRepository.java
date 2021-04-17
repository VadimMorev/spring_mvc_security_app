package com.itstep.ckj13_mvc.repo;

import com.itstep.ckj13_mvc.entity.ConfirmToken;
import com.itstep.ckj13_mvc.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmTokenRepository extends CrudRepository<ConfirmToken, Integer> {
    ConfirmToken findByUser(User user);

    ConfirmToken findByValue(String value);
    void removeConfirmTokenById(int id);
}
