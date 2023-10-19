/**
 * 
 */
package com.example.ticketingsystem.common;

import java.util.Random;

/**
 * 
 */
public class CommonUtil {

	public static String generateId(String letter) {
		
		int num = 1000 + new Random().nextInt(9000);
		return (letter + num);
    }
	
	public static int generateSecurityCode() {
		
		return (1000 + new Random().nextInt(9000));
    }
	
}
