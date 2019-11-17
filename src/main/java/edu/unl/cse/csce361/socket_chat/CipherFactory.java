package edu.unl.cse.csce361.socket_chat;

import java.util.Scanner;

public class CipherFactory {

	public static Cipher createCipher(String name, String[] keys) {
		System.out.println("Please enter the cipher method you want to implement");
		Scanner scan = new Scanner(System.in);
		name = scan.nextLine();
		if(name.equalsIgnoreCase("Caesar")) {
			System.out.println("Please enter shift for the caesar cipher");
			keys[0] = scan.nextLine();
			int shift = Integer.parseInt(keys[0]);
		    return new CaesarCipher(shift);
		}
		else if(name.equalsIgnoreCase("Xor")) {
            
			return new XorCipher();
		}
		else {
			return createCipher();
		}
		
	}
	public static Cipher createCipher() {
		return new NullCipher();
	}
	
	public static void main(String[] args) {
		String name = "Caesar";
		String[] keys = new String[5];
	 
	Cipher ci = createCipher(name, keys);
	System.out.println(ci.decipher("Mjdt")); 
	
	}
	
}
