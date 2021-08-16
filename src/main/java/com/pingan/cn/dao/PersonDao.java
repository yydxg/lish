package com.pingan.cn.dao;

import com.pingan.cn.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao extends JpaRepository<Person,String> {
//    @Query("select o from Order o where o.stateType = 'wait_open' or o.stateType = 'wait_pickup' order by o.orderTime desc ")
//    List<Person> findByOnGoing();

    List<Person> findByCurrentCity(String currentCity);
}
