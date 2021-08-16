package com.pingan.cn.dao;

import com.pingan.cn.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface EquimentDao extends JpaRepository<Equipment,Integer> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Equipment sc set sc.state=?2,sc.isApply=?3 where sc.id=?1")
    void updateStateAndIsApply(Integer id, Integer state,Integer isApply);

    @Query(value = "select * from equipment where num in ?1",nativeQuery = true)
    List<Equipment> findByNums(List<Integer> nums);
}
