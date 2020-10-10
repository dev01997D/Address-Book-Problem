package com.blz.addressbooksystem.runner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.blz.addressbooksystem.model.AddressBook;
import com.blz.addressbooksystem.model.AddressBookDictionary;
import com.blz.addressbooksystem.model.Contact;
import com.blz.addressbooksystem.model.SortByCity;
import com.blz.addressbooksystem.model.SortByFirstName;
import com.blz.addressbooksystem.model.SortByPhoneNumber;

public class AddressBookMain {
	private static Scanner sc = new Scanner(System.in);
	// Instantiation of AddressBookDictionary Class
	static AddressBookDictionary addressBookDictionary = new AddressBookDictionary();
	// Instantiation of AddressBook Class
	static AddressBook addressBookObj = new AddressBook();

	public static void main(String[] args) {
		// Display Welcome Message
		System.out.println("Welcome to Address Book Program");

		while (true) {
			System.out.println(
					"Please Select From Menu: \n1. Add New Address Book \n2. Print All AddressBook \n3 : Exit:");
			int select = sc.nextInt();

			if (select == 1) {
				System.out.println("Enter the Address Book Name : ");
				sc.nextLine();
				String addressBookName = sc.nextLine();
				addressBookDictionary.addAddressBook(addressBookName, addressBookObj);
				int option = 0;

				do {

					System.out.println(
							"Please Select From Menu: \n1 : Add Contact to Address Book\n2 : Update Existing Contact\n3 : Remove Contact"
									+ "\n4 : Search  a Contact from multiple AddressBook \n5 : View persons by City Name  "
									+ "\n6 : Count number of contacts in given city \n7 : Print the Contact entries in sorted manner by First Name"
									+ "\n8 : Print the contact details sorted by City Name And Phone Number \n9 : Exit");
					option = sc.nextInt();

					switch (option) {
					case 1:
						addressBookObj.addContact();
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
							addressBookObj.editGivenDetail(contactEdited);
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

					case 4:
						searchGivenContact();
						break;

					case 5:
						System.out.println("Enter the city Name:");
						String cityNameView = sc.next();
						getContactsOfGivenCity(cityNameView);
						break;

					case 6:
						System.out.println("Enter the city Name:");
						String cityNameContactCount = sc.next();
						getTotalContactsInGivenCity(cityNameContactCount);
						break;

					case 7:
						printContactSortedByFirstName();
						break;

					case 8:
						printContactSortedByCityOrPhone();
						break;
					}
				} while (option != 9);
			}
			// Printing all the Contacts from AddressBook
			else if (select == 2)
				addressBookDictionary.printAllAddressBook();
			else
				System.exit(0);
		}
	}

	// Ability to search for a contact from multiple addressBook
	private static void searchGivenContact() {
		System.out.println("Enter your First Name of Contact : ");
		String firstNameSearch = sc.next();
		System.out.println("Enter your Last Name of Contact : ");
		String lastNameSearch = sc.next();
		List<Contact> searchedContact = new ArrayList<>();
		Predicate<Contact> predicateContactSearch = (
				Contact) -> Contact.getFirstName().equalsIgnoreCase(firstNameSearch)
						&& Contact.getLastName().equalsIgnoreCase(lastNameSearch);
		for (AddressBook addbook : addressBookDictionary.getAddressBookDictionary().values()) {
			searchedContact.addAll(
					(addbook.getAddressBook()).stream().filter(predicateContactSearch).collect(Collectors.toList()));
		}
		if (searchedContact.isEmpty())
			System.out.println("No contact details found for given details.");
		else
			searchedContact.stream().forEach((Contact) -> System.out.println(Contact));
	}

	// Ability to view person by city name
	private static void getContactsOfGivenCity(String cityNameView) {
		// Using Stream API to filter the contact which satisfy the criteria
		List<Contact> contactOfGivenCity = new ArrayList<>();
		Predicate<Contact> predicateContactView = (Contact) -> Contact.getCityName().equalsIgnoreCase(cityNameView);

		for (AddressBook addbook : addressBookDictionary.getAddressBookDictionary().values()) {
			contactOfGivenCity.addAll(
					(addbook.getAddressBook()).stream().filter(predicateContactView).collect(Collectors.toList()));
		}
		System.out.println(contactOfGivenCity);
		if (contactOfGivenCity.isEmpty())
			System.out.println("No contact details found for given city details.");
		else {
			System.out.println("These are contact from the city " + cityNameView);
			contactOfGivenCity.stream().forEach((Contact) -> System.out.println(Contact));
		}
	}

	// Ability to count number of contacts in a given city
	private static void getTotalContactsInGivenCity(String cityNameContactCount) {
		long total = 0;
		Predicate<Contact> predicateContactCount = (Contact) -> Contact.getCityName()
				.equalsIgnoreCase(cityNameContactCount);
		for (AddressBook addbook : addressBookDictionary.getAddressBookDictionary().values()) {
			total = addbook.getAddressBook().stream().filter(predicateContactCount).count();
		}
		if (total != 0)
			System.out.println("Number of contacts in given city are : " + total);
		else
			System.out.println("No contact details found for given city details.");
	}

	// Printing the contact details sorted on the first name
	private static void printContactSortedByFirstName() {
		List<Contact> sortContactByName = new ArrayList<>();
		for (AddressBook addbook : addressBookDictionary.getAddressBookDictionary().values()) {
			sortContactByName.addAll((addbook.getAddressBook()).stream().collect(Collectors.toList()));
		}
		// Collection library to sort the name
		Collections.sort(sortContactByName, new SortByFirstName());
		if (sortContactByName.isEmpty())
			System.out.println("No contact details found for given city details.");
		else {
			System.out.println("Contacts details sorted on the first name are as follow : ");
			sortContactByName.stream().forEach((Contact) -> System.out.println(Contact));
		}
	}

	// Printing the contact details sorted on the City name or Phone
	private static void printContactSortedByCityOrPhone() {
		List<Contact> sortContact = new ArrayList<>();
		for (AddressBook addbook : addressBookDictionary.getAddressBookDictionary().values()) {
			sortContact.addAll((addbook.getAddressBook()).stream().collect(Collectors.toList()));
		}
		System.out.println("Enter Option 1 for sorting on city name else for Phone number enter 2 : ");
		int option = sc.nextInt();
		if (option == 1)
			Collections.sort(sortContact, new SortByCity());
		else if (option == 2)
			Collections.sort(sortContact, new SortByPhoneNumber());
		if (sortContact.isEmpty())
			System.out.println("No contact details found for given details.");
		else {
			System.out.println("Sorted Contacts details are as follow : ");
			sortContact.stream().forEach((Contact) -> System.out.println(Contact));
		}
	}
}
