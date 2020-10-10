package com.blz.addressbooksystem.model;

import java.util.Comparator;

public class SortByFirstName implements Comparator<Contact> {
	@Override
	public int compare(Contact c1, Contact c2) {
		return c1.getFirstName().compareTo(c2.getFirstName());
	}
}
