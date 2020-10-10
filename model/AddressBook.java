package com.blz.addressbooksystem.model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Predicate;

public class AddressBook {
	private static Scanner SC = new Scanner(System.in);
	AddressBookDictionary addBookDict = new AddressBookDictionary();

	private ArrayList<Contact> addressBook = new ArrayList<Contact>();

	public ArrayList<Contact> getAddressBook() {
		return this.addressBook;
	}

	public void setAddressBook(ArrayList<Contact> addressBook) {
		this.addressBook = addressBook;
	}

	public void addContactToAddressBook(Contact contact) {
		addressBook.add(contact);
	}

	// Add a contact into Given AddressBook
	public Contact addContact() {
		System.out.println("Enter First Name : ");
		String firstName = SC.next();
		System.out.println("Enter Last Name : ");
		String lastName = SC.next();
		SC.nextLine();
		System.out.println("Enter Address : ");
		String address = SC.nextLine();
		System.out.println("Enter City : ");
		String city = SC.nextLine();
		System.out.println("Enter Zip Code: ");
		int zipCode = SC.nextInt();
		System.out.println("Enter Phone Number : ");
		Long phoneNo = SC.nextLong();
		System.out.println("Enter Email ID : ");
		String email = SC.next();
		Contact contactObj = new Contact(firstName, lastName, address, city, zipCode, phoneNo, email);
		// Use of predicate to check for duplicate entry
		Predicate<Contact> isDuplicate = (Contact) -> Contact.getFirstName().equalsIgnoreCase(firstName)
				&& Contact.getLastName().equalsIgnoreCase(lastName);
		boolean duplicateCheck = (getAddressBook()).stream().anyMatch(isDuplicate);
		if (duplicateCheck == true) {
			System.out.println("Duplicate Entry. Try Again");
		} else {
			addContactToAddressBook(contactObj);
			System.out.println("Contact added!");
		}
		return contactObj;
	}

	// Edit a contact of Given AddressBook
	public Contact editContact(String firstName, String lastName) {
		Contact contactEdit = null;
		for (Contact contact : addressBook) {
			if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
				contactEdit = contact;
				break;
			}
		}
		return contactEdit;
	}

	// Edit a contact of Given AddressBook
	public void editGivenDetail(Contact contactEdited) {
		System.out.println("Select the Option you want to Update\n1 : First Name\n2 : Last Name\n3 : Address\n"
				+ "4 : City\n5 : Zip\n6 : Phone Number\n7 : Email:");
		int choice = SC.nextInt();

		switch (choice) {
		case 1:
			System.out.println("Enter the new First Name to Update : ");
			String newFirstName = SC.next();
			contactEdited.setFirstName(newFirstName);
			break;
		case 2:
			System.out.println("Enter the new Last Name to Update : ");
			String newLastName = SC.next();
			contactEdited.setLastName(newLastName);
			break;
		case 3:
			System.out.println("Enter the new Address to Update : ");
			SC.nextLine();
			String newAddress = SC.nextLine();
			contactEdited.setAddress(newAddress);
			break;
		case 4:
			System.out.println("Enter the new City to Update : ");
			SC.nextLine();
			String newCity = SC.nextLine();
			contactEdited.setCityName(newCity);
			break;
		case 6:
			System.out.println("Enter the new Zip to Update : ");
			int newZip = SC.nextInt();
			contactEdited.setZipNumber(newZip);
			break;
		case 7:
			System.out.println("Enter the new Phone Number to Update : ");
			Long newPhoneNo = SC.nextLong();
			contactEdited.setPhoneNumber(newPhoneNo);
			break;
		case 8:
			System.out.println("Enter the new Email to Update : ");
			String newEmail = SC.next();
			contactEdited.setEmailId(newEmail);
			break;
		default:
			System.out.println("Enter Valid Number");
			break;
		}
	}

	public boolean removeContact(String firstName, String lastName) {
		boolean remove = false;
		for (Contact contact : addressBook) {
			if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
				addressBook.remove(contact);
				remove = true;
				break;
			}
		}
		return remove;
	}
}
