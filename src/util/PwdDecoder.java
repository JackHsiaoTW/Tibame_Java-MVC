package util;

import java.math.BigInteger;

public class PwdDecoder {
	private static BigInteger key = new BigInteger("吳神太神啦!".getBytes());

	public static void main(String[] args) {
		String pwd = "srhkddro哈jgaoaodfsamf4t我jwDPDwfpdpgfmo";
//		String pwd = "123";
		PwdDecoder XX = new PwdDecoder();
		System.out.println("加密前:" + pwd);
		System.out.println("加密後:" + XX.encoder(pwd));
		System.out.println("解密後:" + XX.decoder(XX.encoder(pwd)));
	}

	public String encoder(String pwd) {
		BigInteger password = new BigInteger(pwd.getBytes());
		BigInteger encoderPassword = password.multiply(key);
		return encoderPassword.toString();
	}

	public String decoder(String input) {
		BigInteger encoderPassword = new BigInteger(input);
		byte[] decoderPassword = encoderPassword.divide(key).toByteArray();
		return new String(decoderPassword);
	}

}
