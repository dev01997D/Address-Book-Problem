package com.blz.addressbooksystem.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.blz.addressbooksystem.model.AddressBook;
import com.blz.addressbooksystem.model.AddressBookDictionary;
import com.blz.addressbooksystem.model.Contact;

public class AddressBookMain {

	public static void main(String[] args) {

		// Display Welcome Message
		System.out.println("Welcome to Address Book Program");

		Scanner sc = new Scanner(System.in);

		// Instantiation of AddressBookDictionary Class
		AddressBookDictionary addressBookDictionary = new AddressBookDictionary();

		while (true) {
			System.out.println("Please Select From Menu: \n1. Add New Address Book \n2. Exit:");
			int select = sc.nextInt();

			if (select == 1) {
				System.out.println("Enter the Address Book Name : ");
				sc.nextLine();
				String addressBookName = sc.nextLine();

				// Instantiation of AddressBook Class
				AddressBook addressBookObj = new AddressBook();

				addressBookDictionary.addAddressBook(addressBookName, addressBookObj);

				int option = 0;

				do {

					System.out.println(
							"Please Select From Menu: \n1 : Add Contact to Address Book\n2 : Update Existing Contact\n3 : Remove Contact"
									+ "\n4 : Search  a Contact from multiple AddressBook \n5 : View persons by City Name  "
									+ "\n6 : Conunt number of contacts in given city \n7 : Print contact details\n8 : Exit");
					option = sc.nextInt();

					switch (option) {
					// To Take Console Input for Contact Details
					case 1:
						System.out.println("Enter First Name : ");
						String firstName = sc.next();
						System.out.println("Enter Last Name : ");
						String lastName = sc.next();
						sc.nextLine();
						System.out.println("Enter Address : ");
						String address = sc.nextLine();
						System.out.println("Enter City : ");
						String city = sc.nextLine();
						System.out.println("Enter Zip Code: ");
						int zipCode = sc.nextInt();
						System.out.println("Enter Phone Number : ");
						Long phoneNo = sc.nextLong();
						System.out.println("Enter Email ID : ");
						String email = sc.next();

						// Use of predicate to check for duplicate entry
						Predicate<Contact> isDuplicate = (Contact) -> Contact.getFirstName().equalsIgnoreCase(firstName)
								&& Contact.getLastName().equalsIgnoreCase(lastName);
						boolean duplicateCheck = (addressBookObj.getAddressBook()).stream().anyMatch(isDuplicate);
						if (duplicateCheck == true) {
							System.out.println("Duplicate Entry. Try Again");
						} else {
							// Instantiation of Contact Class
							Contact contactObj = new Contact(firstName, lastName, address, city, zipCode, phoneNo,
									email);
							addressBookObj.addContactToAddressBook(contactObj);
							System.out.println("Contact added!");
						}
						break;

					case 2: // Console input to take First and Last Names to Update Contact
						System.out.println("Enter your First Name to Edit your Contact : ");
						String firstNameEdit = sc.next();
						System.out.println("Enter your Second Name to Edit your Contact : ");
						String lastNameEdit = sc.next();

						// Updating Contact
						Contact contactEdited = addressBookObj.editContact(firstNameEdit, lastNameEdit);
						if (contactEdited == null) {
							System.out.println("Contact Not Available. Please try Again");
						} else {

							System.out.println(
									"Select the Option you want to Update\n1 : First Name\n2 : Last Name\n3 : Address\n"
											+ "4 : City\n5 : Zip\n6 : Phone Number\n7 : Email:");
							int choice = sc.nextInt();

							switch (choice) {
							case 1:
								System.out.println("Enter the new First Name to Update : ");
								String newFirstName = sc.next();
								contactEdited.setFirstName(newFirstName);
								break;
							case 2:
								System.out.println("Enter the new Last Name to Update : ");
								String newLastName = sc.next();
								contactEdited.setLastName(newLastName);
								break;
							case 3:
								System.out.println("Enter the new Address to Update : ");
								sc.nextLine();
								String newAddress = sc.nextLine();
								contactEdited.setAddress(newAddress);
								break;
							case 4:
								System.out.println("Enter the new City to Update : ");
								sc.nextLine();
								String newCity = sc.nextLine();
								contactEdited.setCityName(newCity);
								break;
							case 6:
								System.out.println("Enter the new Zip to Update : ");
								int newZip = sc.nextInt();
								contactEdited.setZipNumber(newZip);
								break;
							case 7:
								System.out.println("Enter the new Phone Number to Update : ");
								Long newPhoneNo = sc.nextLong();
								contactEdited.setPhoneNumber(newPhoneNo);
								break;
							case 8:
								System.out.println("Enter the new Email to Update : ");
								String newEmail = sc.next();
								contactEdited.setEmailId(newEmail);
								break;
							default:
								System.out.println("Enter Valid Number");
								break;
							}
						}
						break;

					case 3: // Console input to take First and Last Names to Remove Contact
						System.out.println("Enter your First Name of Contact : ");
						String removeFirstName = sc.next();
						System.out.println("Enter your Last Name to Contact : ");
						String removeLastName = sc.next();
						boolean remove = addressBookObj.removeContact(removeFirstName, removeLastName);
						if (remove)
							System.out.println("Contact Sucessfully Removed");
						else
							System.out.println("Contact not Available. Please Try Again!");
						break;

					// Ability to search for a contact from multiple addressBook
					case 4:
						System.out.println("Enter your First Name of Contact : ");
						String firstNameSearch = sc.next();
						System.out.println("Enter your Last Name of Contact : ");
						String lastNameSearch = sc.next();

						List<Contact> contactListSearch = new ArrayList<>();
						Predicate<Contact> predicateContactSearch = (
								Contact) -> Contact.getFirstName().equalsIgnoreCase(firstNameSearch)
										&& Contact.getLastName().equalsIgnoreCase(lastNameSearch);
						for (AddressBook addbook : addressBookDictionary.getAddressBookDictionary().values()) {
							contactListSearch.addAll((addbook.getAddressBook()).stream().filter(predicateContactSearch)
									.collect(Collectors.toList()));
						}
						if (contactListSearch.isEmpty())
							System.out.println("No contact details found for given details.");
						else {
							contactListSearch.stream()
									.forEach((Contact) -> System.out.println("First Name" + Contact.getFirstName()
											+ " Last Name " + Contact.getLastName() + " City Name "
											+ Contact.getCityName() + " Phone Number " + Contact.getPhoneNumber()));
						}
						break;

					// Ability to view person by city name
					case 5:
						System.out.println("Enter the city Name:");
						String cityNameView = sc.next();

						// Using List to store the filter contact. Using Stream API to filter the
						// contact which satisfy the criteria
						List<Contact> contactListView = new ArrayList<>();
						Predicate<Contact> predicateContactView = (Contact) -> Contact.getCityName()
								.equalsIgnoreCase(cityNameView);
						for (AddressBook addbook : addressBookDictionary.getAddressBookDictionary().values()) {
							contactListView.addAll((addbook.getAddressBook()).stream().filter(predicateContactView)
									.collect(Collectors.toList()));
						}
						if (contactListView.isEmpty())
							System.out.println("No contact details found for given city details.");
						else {
							contactListView.stream()
									.forEach((Contact) -> System.out.println("First Name" + Contact.getFirstName()
											+ " Last Name " + Contact.getLastName() + " Phone Number "
											+ Contact.getPhoneNumber()));
						}
						break;
						
					//Ability to count number of contacts in a given city
					case 6:
						long total = 0;
						System.out.println("Enter the city Name:");
						String cityNameContactCount = sc.next();
						Predicate<Contact> predicateContactCount = (Contact) -> Contact.getCityName()
								.equalsIgnoreCase(cityNameContactCount);
						for (AddressBook addbook : addressBookDictionary.getAddressBookDictionary().values()) {
							total = addbook.getAddressBook().stream().filter(predicateContactCount).count();
						}
						if (total != 0)
							System.out.println("Number of contacts in given city are : " + total);
						else
							System.out.println("No contact details found for given city details.");
						break;

					// Printing all the Contacts from AddressBook
					default:
						List<Contact> contactList = new ArrayList<>();
						for (AddressBook addbook : addressBookDictionary.getAddressBookDictionary().values()) {
							contactList.addAll((addbook.getAddressBook()).stream().collect(Collectors.toList()));
						}
						if (contactList.isEmpty())
							System.out.println("No contact details has been added in any Address Book yet!.");
						else {
							contactList.stream()
									.forEach((Contact) -> System.out.println("First Name" + Contact.getFirstName()
											+ " Last Name " + Contact.getLastName() + " Phone Number "
											+ Contact.getPhoneNumber()));
						}
						break;
					}
				} while (option != 8);
			} else
				System.exit(0);
		}
	}
}