public static class Utils {
	private static String stringTimes(String s, double d) {
		int rounded = Math.ceil(d);
		return new String(new char[rounded]).replace("\0", s)).substring(0, Math.round(s.length() * d));
	}
}