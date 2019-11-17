package edu.unl.cse.csce361.socket_chat;
public class CaesarCipher implements Cipher {
    private String plaintext;
    private String ciphertext;
    private int shift;
	@Override
	public String encipher(String plaintext2) {
	    this.plaintext = plaintext2;
		shift = shift%26;
		
		String ciphertext = "";
		
		int lengthPaintext = plaintext.length();
		int i =0;
		while(i<lengthPaintext) {
			char plainTextChar = plaintext.charAt(i);
			if(Character.isLowerCase(plainTextChar)) {
			char c = (char)(plainTextChar+shift);
			if(c>'z') {
				ciphertext = ciphertext + (char)(plainTextChar-(26-shift));
			}
			else {
				ciphertext = ciphertext +c;
			}
			}
			else if(Character.isUpperCase(plainTextChar)) {
				char c = (char)(plainTextChar+shift);
				if(c>'Z') {
					ciphertext = ciphertext + (char)(plainTextChar-(26-shift));
				}
				else {
					ciphertext = ciphertext +c;
				}
			}
			i++;
		}
		return ciphertext;
	}

	@Override
	public String decipher(String ciphertext2) {
		// TODO Auto-generated method stub
		 this.ciphertext = ciphertext2;
			shift = shift%26;
			
			String plaintext = "";
			
			int lengthCiphertext = ciphertext.length();
			int i =0;
			while(i<lengthCiphertext) {
				char plainCipherChar = ciphertext.charAt(i);
				if(Character.isLowerCase(plainCipherChar)) {
				char c = (char)(plainCipherChar-shift);
				
				if(c < 'a') {
					plaintext = plaintext + (char)(plainCipherChar+(26-shift));
				}
				else {
					plaintext = plaintext + c;
				}
				}
				else if(Character.isUpperCase(plainCipherChar)) {
					char c = (char)(plainCipherChar-shift);
					
					if(c < 'A') {
						plaintext = plaintext + (char)(plainCipherChar+(26-shift));
					}
					else {
						plaintext = plaintext + c;
					}
				}
				i++;
			}
			return plaintext;
	}
	public CaesarCipher(int shift){
		 this.shift = shift;
	}
//  public static void main(String[] args) {
//	  Cipher c = new CaesarCipher(5);
//	  String decipher = c.encipher("Heyo");
//      System.out.println(decipher);
//  }
//  


}
