/**
 * 
 */
package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.example.demo.audit.AppUserEntityListener;
import com.example.demo.enums.AppUserStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author mahasarathi
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor(force = true)
@RequiredArgsConstructor(staticName = "of")
@Entity
@EntityListeners(AppUserEntityListener.class)
public class AppUser extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NonNull
	private String name;
	@NonNull
	private String email;
	private AppUserStatus status = AppUserStatus.ACTIVE;
}