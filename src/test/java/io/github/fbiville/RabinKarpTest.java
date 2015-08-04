package io.github.fbiville.fdk;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import java.util.Optional;

public class RabinKarpTest {

	RabinKarp matcher = new RabinKarp();

	@Test
	public void text_shorter_than_pattern() {
		Optional<Integer> result = matcher.firstMatch("aha", "ah");
		
		assertThat(result.isPresent()).isFalse();
	}

	@Test
	public void text_equals_pattern() {
		Optional<Integer> result = matcher.firstMatch("aha", "aha");
		
		assertThat(result.get()).isEqualTo(0);
	}

	@Test
	public void longer_text() {
		Optional<Integer> result = matcher.firstMatch("aha", "ahah");
		
		assertThat(result.get()).isEqualTo(0);
	}

	@Test
	public void looooooonger_text() {
		Optional<Integer> result = matcher.firstMatch("quick fox", "the quick fox is running around megan fox, quick!");
		
		assertThat(result.get()).isEqualTo(4);
	}
}
