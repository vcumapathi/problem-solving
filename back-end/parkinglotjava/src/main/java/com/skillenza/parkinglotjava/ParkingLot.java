package com.skillenza.parkinglotjava;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "parkinglots")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class ParkingLot {
	
    //your code goes here
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name = "created_at")
	@NotNull
	@CreatedDate
	private Date createdAt;

	@Column(name = "updated_at")
	@NotNull
	@LastModifiedDate
	private Date updatedAt;
	
	@Column(name="parking_amount")
	@NotNull
	private Integer parkingAmount;

	@Column(name = "lot", unique = true)
	@NotNull
	private Integer lot;

	@Column(name = "parking_duration")
	@NotNull
	private Integer parkingDuration;

	@Column(name = "vehical_number", unique = true)
	@NotNull
	private Integer vehicleNumber;

	
	
	public ParkingLot(Long id, @NotNull Date createdAt, @NotNull Date updatedAt, @NotNull Integer parkingAmount,
			@NotNull Integer lot, @NotNull Integer parkingDuration, @NotNull Integer vehicleNumber) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.parkingAmount = parkingAmount;
		this.lot = lot;
		this.parkingDuration = parkingDuration;
		this.vehicleNumber = vehicleNumber;
	}

	public ParkingLot() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getLot() {
		return lot;
	}

	public void setLot(Integer lot) {
		this.lot = lot;
	}

	public Integer getParkingDuration() {
		return parkingDuration;
	}

	public void setParkingDuration(Integer parkingDuration) {
		this.parkingDuration = parkingDuration;
	}

	public Integer getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(Integer vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public Integer getParkingAmount() {
		return parkingAmount;
	}

	public void setParkingAmount(Integer parkingAmount) {
		this.parkingAmount = parkingAmount;
	}

	@Override
	public String toString() {
		return "ParkingLot [id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", parkingAmount="
				+ parkingAmount + ", lot=" + lot + ", parkingDuration=" + parkingDuration + ", vehicleNumber=" + vehicleNumber
				+ "]";
	}
	
    
}
