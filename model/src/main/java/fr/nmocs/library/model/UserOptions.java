package fr.nmocs.library.model;

import javax.persistence.Entity;
import javax.persistence.Id;
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

	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean warnedBeforeLoanPeremption;
}
