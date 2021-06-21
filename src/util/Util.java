package util;

public class Util {
	// Byte[] to byte[]
	public static byte[] toPrimitives(Byte[] oBytes) {
		byte[] bytes = null;
		try {
			bytes = new byte[oBytes.length];
			for (int i = 0; i < oBytes.length; i++) {
				bytes[i] = oBytes[i];
			}
		} catch (Exception e) {
			return null;
		}

		return bytes;
	}

	// byte[] to Byte[]
	public static Byte[] toObjects(byte[] bytesPrim) {
		Byte[] bytes = null;
		try {
			bytes = new Byte[bytesPrim.length];
			int i = 0;
			for (byte b : bytesPrim) {
				bytes[i++] = b; // Autoboxing
			}
		} catch (Exception e) {
			return null;
		}

		return bytes;
	}
}
