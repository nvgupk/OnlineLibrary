package com.onlinelibrary.validation;

public class Validation {
	public static boolean isPositiveInteger(String s) {
		if(s == null) {
			return false;
		}
		return (s.matches("\\d+"));
	}
}
