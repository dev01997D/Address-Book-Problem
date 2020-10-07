package com.blz.addressbooksystem.model;

import java.util.Comparator;

public class SortByCity implements Comparator<Contact>{

	@Override
	public int compare(Contact c1, Contact c2) {
		return c1.getCityName().compareTo(c2.getCityName());
	}
}
