package edu.unl.cse.csce361.socket_chat;

import java.util.Scanner;

public class CipherFactory {

	public static Cipher createCipher(String name, String[] keys) {
	    
		if(name.equalsIgnoreCase("Caesar")) {
			int shift = Integer.parseInt(keys[0]);
		    return new CaesarCipher(shift);
		}
		else if(name.equalsIgnoreCase("Xor")) {
            String keysForXor = keys[0];
			return new Xor(keysForXor);
		}
		else {
			
			return createCipher();
		}
		
	}
	public static Cipher createCipher() {
		return new NullCipher();
	}
	
	
}
