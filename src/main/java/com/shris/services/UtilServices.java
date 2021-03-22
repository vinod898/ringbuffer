package com.shris.services;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UtilServices {

	final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	final String nums = "0123456789";

	final Random rand = new Random();

	public String randomIdentifier(String lexicon) {

		final Set<String> identifiers = new HashSet<String>();

		StringBuilder builder = new StringBuilder();
		while (builder.toString().length() == 0) {
			int length = rand.nextInt(5) + 5;
			for (int i = 0; i < length; i++) {
				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			}
			if (identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}
		return builder.toString();
	}

	public String getRandName() {

		return randomIdentifier(letters);
	}

	public String getRandId() {
		return randomIdentifier(nums);
	}

	public String getRandAddress() {
		return randomIdentifier(letters);
	}

}
