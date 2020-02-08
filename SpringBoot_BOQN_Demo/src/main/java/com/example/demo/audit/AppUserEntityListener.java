/**
 * 
 */
package com.example.demo.audit;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.example.demo.entity.AppUser;
import com.example.demo.entity.AppUserHistory;
import com.example.demo.enums.Action;
import com.example.demo.utils.BeanUtil;

/**
 * @author mahasarathi
 *
 */
public class AppUserEntityListener {

	@PrePersist
	public void prePersist(AppUser target) {
		perform(target, Action.INSERTED);
	}

	@PreUpdate
	public void preUpdate(AppUser target) {
		perform(target, Action.UPDATED);
	}

	@PreRemove
	public void preRemove(AppUser target) {
		perform(target, Action.DELETED);
	}

	@Transactional(value = TxType.MANDATORY)
	private void perform(AppUser target, Action action) {
		EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
		entityManager.persist(AppUserHistory.of(target, action));
	}

}
