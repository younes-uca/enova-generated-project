package ma.enova.rth.common.util;

public class UniqueID {
	private UniqueID(){}
	private static long current = System.currentTimeMillis();

	static public synchronized long get() {
		return current++;
	}
}