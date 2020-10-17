package com.blz.addressbooksystem.model;

/**
 * @author devsa
 *
 */
public class Contact {
	private String firstName;
	private String lastName;
	private String address;
	private String cityName;
	private long zipNumber;
	private long phoneNumber;
	private String emailId;

	// Parameterized Constructor
	public Contact(String firstName, String lastName, String address, String cityName, long zipNumber, long phoneNumber,
			String emailId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.cityName = cityName;
		this.zipNumber = zipNumber;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public String getCityName() {
		return cityName;
	}

	public String getEmailId() {
		return emailId;
	}

	// Getters And Setters
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public long getZipNumber() {
		return zipNumber;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setZipNumber(long zipNumber) {
		this.zipNumber = zipNumber;
	}

	@Override
	public String toString() {
		return "First Name: " + getFirstName() + " | Last Name " + getLastName() + " | Address " + getAddress()
				+ " | City " + getCityName() + " | Phone Number " + getPhoneNumber() + " | Email " + getEmailId();
	}

}
