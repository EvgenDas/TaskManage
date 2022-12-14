package com.prog.demo.repository;
import com.prog.demo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;


import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByStatus(@Param("status") String status);
    @Query("select a from Task a where a.estimate_date = :estimate_date")
    List<Task> findByDate(@Param("estimate_date")Date estimate_date);





}
