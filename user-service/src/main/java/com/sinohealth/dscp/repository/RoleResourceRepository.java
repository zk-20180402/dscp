package com.sinohealth.dscp.repository;


import com.sinohealth.dscp.model.RoleResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface RoleResourceRepository extends JpaRepository<RoleResource, Integer> {
    //自定义方法

    /*@Query(value = "SELECT c.*\n" +
            "FROM (\n" +
            "       SELECT *\n" +
            "       FROM cart_event\n" +
            "       WHERE user_id = ?1 AND (cart_event_type = 3 OR cart_event_type = 2)\n" +
            "       ORDER BY cart_event.created_at DESC\n" +
            "       LIMIT 1\n" +
            "     ) t\n" +
            "  RIGHT JOIN cart_event c ON c.user_id = t.user_id\n" +
            "WHERE c.created_at BETWEEN coalesce(t.created_at, 0) AND 9223372036854775807 AND coalesce(t.id, -1) != c.id\n" +
            "ORDER BY c.created_at ASC", nativeQuery = true)
    Channel getCartEventStreamByUser(Long userId);*/

    @Modifying
    @Query("update RoleResource rr set rr.createTime=?1, rr.updateTime=?2 where rr.id=?3")
    Integer updateRoleResource(Date create_time, Date update_time, Integer id);

    RoleResource getById(Integer id);

    List<RoleResource> getByRoleId(Integer roleId);
}
