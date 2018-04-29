import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestZigzagConversion {

    @Test
    public void test() {
        assertEquals("PAHNAPLSIIGYIR", zigzagConvert("PAYPALISHIRING", 3));
    }

    private String zigzagConvert(String paypalishiring, int rows) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= rows; i++) {
            sb.append(paypalishiring.charAt(i));
            for (int j = 2 * rows - i - 2; j < paypalishiring.length(); j = 2 * j) {
                sb.append(paypalishiring.charAt(j));
            }
        }

        return sb.toString();
    }


}
