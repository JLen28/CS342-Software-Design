import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HLThreadingTest {

	@Test
	void test() {
		HLThreading nuGame = new HLThreading();
		nuGame.init();
		String[] s1 = new String[] {"b","b","b","b","b","b","b","b","b"};
		assertEquals(s1,nuGame.getBoard());
	}

}
