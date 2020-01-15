package fr.nmocs.library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "UserOptions")
public class UserOptions {

	@Id
	private Integer userId;

	@OneToOne
	@JoinColumn(name = "userId", nullable = false)
	@MapsId
	private User user;

	@Type(type = "org.hibernate.type.NumericBooleanType")
	@Column(name = "warnedBeforeLoanPeremption", columnDefinition = "tinyint(1) default 1")
	private Boolean warnedBeforeLoanPeremption;
}
