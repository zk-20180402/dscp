package com.sinohealth.dscp.repository;


import com.sinohealth.dscp.model.Job;
import com.sinohealth.dscp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface JobRepository extends JpaRepository<Job, Integer> {
    //自定义方法

    /*@Query(value = "SELECT c.*\n" +
            "FROM (\n" +
            "       SELECT *\n" +
            "       FROM cart_event\n" +
            "       WHERE Job_id = ?1 AND (cart_event_type = 3 OR cart_event_type = 2)\n" +
            "       ORDER BY cart_event.created_at DESC\n" +
            "       LIMIT 1\n" +
            "     ) t\n" +
            "  RIGHT JOIN cart_event c ON c.Job_id = t.Job_id\n" +
            "WHERE c.created_at BETWEEN coalesce(t.created_at, 0) AND 9223372036854775807 AND coalesce(t.id, -1) != c.id\n" +
            "ORDER BY c.created_at ASC", nativeQuery = true)
    Channel getCartEventStreamByJob(Long JobId);*/


    @Modifying
    @Query("update Job j set j.createTime=?1, j.updateTime=?2 where j.id=?3")
    Integer updateJob(Date create_time, Date update_time, Integer id);

    Job getById(Integer id);
}
