package io.github.fbiville.fdk;

import java.util.Optional;

public class RabinKarp {

	private final int prime = 541;
	private final int radix = 65536;

	public Optional<Integer> firstMatch(String pattern, String text) {
		if (text.length() < pattern.length()) {
			return Optional.<Integer>empty();
		}
		if (pattern.equals(text)) {
			return Optional.of(0);
		}
		int patternLength = pattern.length();

		long patternHash = hash(pattern, 0, patternLength);
		long textSliceHash = hash(text, 0, patternLength);

		int end = text.length() - patternLength;
		for (int i = 0; i < end; i++) {
			if (patternHash == textSliceHash && matches(pattern, text, i)) {
				return Optional.of(i);
			}
			if (i < end - 1) {
				textSliceHash = nextTextSliceHash(text, textSliceHash, i, patternLength);
			}
		}
		return Optional.<Integer>empty();
	}

	long hash(String text, int start, int end) {
		long result = 0;
		for (int i = start; i < end; i++) {
			result = (radix * result + text.charAt(i)) % prime;
		}
		return result;
	}

	long nextTextSliceHash(String text, long previousHash, int startPosition, int patternLength) {
		long coef = ((long) Math.pow(radix, patternLength - 1)) % prime;
		previousHash = (previousHash + prime - coef * text.charAt(startPosition +1) % prime) % prime;
		previousHash = (previousHash * radix + text.charAt(startPosition +1+patternLength)) % prime;
		return previousHash;
	}

	private static boolean matches(String pattern, String text, int offset) {
		for (int i = 0; i < pattern.length(); i++) {
			if (pattern.charAt(i) != text.charAt(i+offset)) {
				return false;
			}
		}
		return true;
	}
}
