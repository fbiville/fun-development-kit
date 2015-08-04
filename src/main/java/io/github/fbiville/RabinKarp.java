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
			if (patternHash == textSliceHash && matches(pattern, text, i)) {
				return Optional.of(i);
			}
			if (i < end - 1) {
				textSliceHash = (radix * (textSliceHash - coef * text.charAt(i+1)) 
						+ text.charAt(i+1+patternLength)) % prime;
			}
		}
		return Optional.<Integer>empty();
	}

	private boolean matches(String pattern, String text, int offset) {
		for (int i = 0; i < pattern.length(); i++) {
			if (pattern.charAt(i) != text.charAt(i+offset)) {
				return false;
			}
		}
		return true;
	}

	private long hash(String text, int start, int end) {
		long result = 0;
		for (int i = start; i < end; i++) {
			result = (radix * result + text.charAt(i)) % prime;
		}
		return result;
	}
}
