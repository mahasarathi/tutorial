/**
 * 
 */
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AppUserHistory;

/**
 * @author mahasarathi
 *
 */
@Repository
public interface AppUserHistoryRepository extends JpaRepository<AppUserHistory, Integer> {
	
}
