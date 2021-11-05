package org.wj.prajumsook;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class JavaUppgift02 {

	public static void main(String[] args) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dateInput = "02/07/2021";
		LocalDate localDate = LocalDate.parse(dateInput, dateTimeFormatter);
		
		System.out.println("Input: " + dateInput);
		System.out.println("Output: " + localDate);
	}

	
}
