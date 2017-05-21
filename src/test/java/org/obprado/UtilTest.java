package org.obprado;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class UtilTest {

    @Test
    public void shouldGetTheIdAtTheEndOfTheUri() {
        String URI = "/quotes/3";
        Util util = new Util();
        String extracted = util.extractId(URI);
        assertThat(extracted).isEqualTo("3");
    }
}