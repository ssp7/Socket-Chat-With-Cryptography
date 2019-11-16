package edu.unl.cse.csce361.socket_chat;

import java.util.Scanner;

public class CipherFactory {

	public static Cipher createCipher(String name, String[] keys) {
		System.out.println("Please enter the cipher method you want to implement");
		Scanner scan = new Scanner(System.in);
		name = scan.nextLine();
		if(name.equalsIgnoreCase("Caesar")) {
		     return new CaesarCipher();
		}
		else if(name.equalsIgnoreCase("Xor")) {
            
			return new XorCipher();
		}
		else {
			return new NullCipher();
		}
		
	}
	public static Cipher create() {
		return new NullCipher();
	}
}
