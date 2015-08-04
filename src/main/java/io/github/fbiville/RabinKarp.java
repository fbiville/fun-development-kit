package io.github.fbiville.fdk;

import java.util.Optional;

public class RabinKarp {

	private final int prime = 541;
	private final int radix = 10;

	public Optional<Integer> firstMatch(String pattern, String text) {
		if (text.length() < pattern.length()) {
			return Optional.<Integer>empty();
		}
		if (pattern.equals(text)) {
			return Optional.of(0);
		}
		int patternLength = pattern.length();
		long coef = ((long) Math.pow(radix, patternLength - 1)) % prime;

		long patternHash = hash(pattern, 0, patternLength);
		long textSliceHash = hash(text, 0, patternLength);

		int end = text.length() - patternLength;
		for (int i = 0; i < end; i++) {
			if (patternHash == textSliceHash) {
				String slice = text.substring(i, i+patternLength);
				if (matches(pattern, slice)) {
					return Optional.of(i);
				}
			}
			if (i < end - 1) {
				textSliceHash = hash(text, i+1, i+1+patternLength);
			}
		}
		return Optional.<Integer>empty();
	}

	private boolean matches(String slice1, String slice2) {
		return slice1.contentEquals(slice2);
	}

	private long hash(String text, int start, int end) {
		long result = 0;
		for (int i = start; i < end; i++) {
			result = (radix * result + text.charAt(i)) % prime;
		}
		return result;
	}
}
