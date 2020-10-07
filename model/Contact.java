package com.blz.addressbooksystem.model;

public class Contact {
	private String firstName;
	private String lastName;
	private String address;
	private String cityName;
	private int zipNumber;
	private long phoneNumber;
	private String emailId;

	// Getters And Setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getZipNumber() {
		return zipNumber;
	}

	public void setZipNumber(int zipNumber) {
		this.zipNumber = zipNumber;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	// Parametrized Constructor
	public Contact(String firstName, String lastName, String address, String cityName, int zipNumber, long phoneNumber,
			String emailId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.cityName = cityName;
		this.zipNumber = zipNumber;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
	}

	public Contact() {
	}

	@Override
	public String toString() {
		return "First Name: " + getFirstName() + ", Last Name " + getLastName() + ", Address " + getAddress() + ", City "
				+ getCityName() + ", Phone Number " + getPhoneNumber() + ", Email " + getEmailId();
	}
}
