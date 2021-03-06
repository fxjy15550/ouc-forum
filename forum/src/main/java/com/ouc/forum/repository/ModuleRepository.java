package com.ouc.forum.repository;

import com.ouc.forum.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sunhaojie
 * @version 1.0
 * @date 2020/9/8 12:05
 */
@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {
}
