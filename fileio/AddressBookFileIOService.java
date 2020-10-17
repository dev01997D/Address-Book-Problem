package com.blz.addressbooksystem.fileio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.blz.addressbooksystem.model.Contact;
public class AddressBookFileIOService {

	public static String CONTACT_FILE_NAME = "E:\\Eclipse-Workspace-java-developer\\AddressBookSystem\\src\\com\\blz\\addressbooksystem\\fileio\\ContactDetails.txt";

	// Writing the contact details to each line
	public void writeContactToFile(List<Contact> contactList) {
		StringBuffer contactBuffer = new StringBuffer();
		contactList.forEach(employee -> {
			String contactDataString = employee.toString().concat("\n");
			contactBuffer.append(contactDataString);
		});
		try {
			Files.write(Paths.get(CONTACT_FILE_NAME), contactBuffer.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Reading all the details of contact line by line
	public void readData(List<Contact> contactList) {
		try {
			Files.lines(new File(CONTACT_FILE_NAME).toPath()).map(line -> line.trim())
					.forEach(line -> System.out.println(line));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
