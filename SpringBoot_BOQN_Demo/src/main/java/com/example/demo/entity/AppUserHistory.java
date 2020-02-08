/**
 * 
 */
package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.enums.Action;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author mahasarathi
 *
 */
@Data
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(force = true)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AppUserHistory {

    @Id
    @GeneratedValue
    private Integer id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "app_user_id", foreignKey = @ForeignKey(name = "FK_app_user_history"))
    private AppUser appUser;

    @CreatedBy
    private String modifiedBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Action action;

}
