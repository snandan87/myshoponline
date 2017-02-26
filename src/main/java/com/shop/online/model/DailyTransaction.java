package com.shop.online.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the daily_transaction database table.
 * 
 */
@Entity
@Table(name="daily_transaction")
@NamedQuery(name="DailyTransaction.findAll", query="SELECT d FROM DailyTransaction d")
public class DailyTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="trans_amount")
	private BigDecimal transAmount;

	@Temporal(TemporalType.DATE)
	@Column(name="trans_date")
	private Date transDate;

	@Column(name="trans_description")
	private String transDescription;

	@Column(name="trans_metal")
	private String transMetal;

	@Column(name="trans_mode")
	private String transMode;

	@Column(name="trans_stat")
	private byte[] transStat;

	@Column(name="trans_type")
	private String transType;

	@Column(name="trans_weight")
	private BigDecimal transWeight;

	//bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name="trans_customer_id")
	@JsonIgnore
	private UserDetail userDetail;

	public DailyTransaction() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getTransAmount() {
		return this.transAmount;
	}

	public void setTransAmount(BigDecimal transAmount) {
		this.transAmount = transAmount;
	}

	public Date getTransDate() {
		return this.transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}

	public String getTransDescription() {
		return this.transDescription;
	}

	public void setTransDescription(String transDescription) {
		this.transDescription = transDescription;
	}

	public String getTransMetal() {
		return this.transMetal;
	}

	public void setTransMetal(String transMetal) {
		this.transMetal = transMetal;
	}

	public String getTransMode() {
		return this.transMode;
	}

	public void setTransMode(String transMode) {
		this.transMode = transMode;
	}

	public byte[] getTransStat() {
		return this.transStat;
	}

	public void setTransStat(byte[] transStat) {
		this.transStat = transStat;
	}

	public String getTransType() {
		return this.transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public BigDecimal getTransWeight() {
		return this.transWeight;
	}

	public void setTransWeight(BigDecimal transWeight) {
		this.transWeight = transWeight;
	}

	public UserDetail getUserDetail() {
		return this.userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

}