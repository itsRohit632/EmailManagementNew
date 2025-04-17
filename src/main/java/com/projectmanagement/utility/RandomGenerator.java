package com.projectmanagement.utility;

import java.util.UUID;

public class RandomGenerator {
	public static String generateRandomPassword() {
		return UUID.randomUUID().toString().substring(0, 8);
	}
}
