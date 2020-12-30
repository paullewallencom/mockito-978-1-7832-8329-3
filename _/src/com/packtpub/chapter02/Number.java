package com.packtpub.chapter02;

import java.util.Random;

public class Number {

	public static String next() {
		return ""+ (9831501137L + new Random().nextLong());
	}
}
