package edu.unl.cse.csce361.socket_chat;

import java.util.Scanner;

public class CaesarCipher implements Cipher {

	@Override
	public String encipher(String plaintext) {
	 
		int shift = 5;
		shift = shift%26;
		String ciphertext = "";
		int lengthPaintext = plaintext.length();
		
		return ciphertext;
	}

	@Override
	public String decipher(String ciphertext) {
		// TODO Auto-generated method stub
		return null;
	}
	public CaesarCipher(){
		
	}
	public static String encipher2(String plaintext)
	{
		int shift = 5;
		return plaintext;
		
	}


}
