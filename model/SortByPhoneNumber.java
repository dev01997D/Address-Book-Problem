package com.blz.addressbooksystem.model;

import java.util.Comparator;

public class SortByPhoneNumber implements Comparator<Contact>{
	@Override
	public int compare(Contact c1, Contact c2) {
		return (int) (c1.getPhoneNumber()-c2.getPhoneNumber());
	}
}
