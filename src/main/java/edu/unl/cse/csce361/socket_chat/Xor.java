package edu.unl.cse.csce361.socket_chat;

import java.lang.reflect.Array;
import java.util.Scanner;

public class Xor implements Cipher {

	private String keyForXor;
	
	@Override
	public String encipher(String plaintext) {
	String ciphertext ="";
			int i =0;
		    int j = 0;
			while(i<plaintext.length()) {
				int Xortemp = plaintext.charAt(i) ^ keyForXor.charAt(j);
				ciphertext = ciphertext + String.format("%02x", (byte)Xortemp);
				j++;
				if(j >= keyForXor.length()) {
					j=0;
				}
				
				i++;
			}
		
    return ciphertext;
	}

	@Override
	public String decipher(String ciphertext2) {
		 String plaintext = "";
         int s = 0;
         while(s<ciphertext2.length()-1) {
        	 String text = ciphertext2.substring(s,(s+2));
        	 int point = Integer.parseInt(text,16);
        	 plaintext = plaintext + (char)point;
        	 s = s+2;
         }
         String finalPlaintext = "";
         int i = 0;
         int j = 0;
         while(i<plaintext.length()) {
				int Xortemp = plaintext.charAt(i) ^ keyForXor.charAt(j);
				finalPlaintext = finalPlaintext + (char)Xortemp;
				j++;
				if(j >= keyForXor.length()) {
					j=0;
				}
				
				i++;
			}
         
		return finalPlaintext;
	}
	
	public Xor(String keys){ 
		this.keyForXor = keys;
	}
	
}
