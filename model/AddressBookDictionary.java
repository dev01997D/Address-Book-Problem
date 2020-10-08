package com.blz.addressbooksystem.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class AddressBookDictionary {

	private HashMap<String, AddressBook> addressBookDictionary = new HashMap<String, AddressBook>();

	public HashMap<String, AddressBook> getAddressBookDictionary() {
		return this.addressBookDictionary;
	}

	public void setAddressBookDictionary(HashMap<String, AddressBook> addressBookDictionary) {
		this.addressBookDictionary = addressBookDictionary;
	}

	public void addAddressBook(String name, AddressBook addressBook) {
		addressBookDictionary.put(name, addressBook);
	}

	//Method to print all the addressBook and the contacts in these
	public void printAllAddressBook() {

		Iterator<Entry<String, AddressBook>> itr = addressBookDictionary.entrySet().iterator();
		if (addressBookDictionary.isEmpty())
			System.out.println("No contact details has been added in any Address Book yet!.");
		else {
			while (itr.hasNext()) {
				Map.Entry<String, AddressBook> entry = itr.next();
				System.out.println("Key = " + entry.getKey());
				List<Contact> contact = entry.getValue().getAddressBook().stream().collect(Collectors.toList());
				for (Contact c : contact) {
					System.out.println(c);
				}
			}
		}
	}
}