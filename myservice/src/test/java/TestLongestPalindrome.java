import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.l;

public class TestLongestPalindrome {
    @Test
    public void case1() {
        Assert.assertEquals("aba", longestPalindrome("babad"));
    }

    @Test
    public void case2() {
        Assert.assertEquals("bb", longestPalindrome("cbbd"));
    }

    @Test
    public void case3() {
        Assert.assertEquals("tattarrattat", longestPalindrome("tattarrattat"));
    }

    @Test
    public void case4() {
        Assert.assertEquals("a", longestPalindrome("a"));
    }

    @Test
    public void case5() {
        Assert.assertEquals("ccc", longestPalindrome("ccc"));
    }

    @Test
    public void case6() {
        Assert.assertEquals("ccc", longestPalindrome("bccc"));
    }

    @Test
    public void case7() {
        Assert.assertEquals("abcba", longestPalindrome("abcba"));
    }

    public String longestPalindrome(String s) {
        int start = 0, end = 0, i = 0;
        for (; i < s.length(); i++) {
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expand(String s, int left, int right) {
        int r = right, l = left;
        while (r < s.length() && l >= 0 && s.charAt(r) == s.charAt(l)) {
            l--;
            r++;
        }
        return r - l - 1;
    }
}
