import org.junit.Assert;
import org.junit.Test;

public class TestFindMedianSortedArrays {

    @Test
    public void testFindMedianSortedArrays() {
        int[] nums1 = new int[2];
        nums1[0] = 1;
        nums1[1] = 3;
        int[] nums2 = new int[2];
        nums2[0] = 2;
        nums2[1] = 4;
        Assert.assertEquals(2.5, findMedianSortedArrays(nums1, nums2), 1);
    }

    @Test
    public void testFindMedianSortedArrays2() {
        int[] nums1 = new int[2];
        nums1[0] = 1;
        nums1[1] = 5;
        int[] nums2 = new int[2];
        nums2[0] = 2;
        nums2[1] = 4;
        Assert.assertEquals(3, findMedianSortedArrays(nums1, nums2), 0.1);
    }


    @Test
    public void testFindMedianSortedArrays3() {
        int[] nums1 = new int[2];
        nums1[0] = 1;
        nums1[1] = 3;
        int[] nums2 = new int[1];
        nums2[0] = 2;
        Assert.assertEquals(2, findMedianSortedArrays(nums1, nums2), 0.1);
    }

    @Test
    public void testFindMedianSortedArrays4() {
        int[] nums1 = new int[1];
        nums1[0] = 1;
        int[] nums2 = new int[1];
        nums2[0] = 2;
        Assert.assertEquals(1.5, findMedianSortedArrays(nums1, nums2), 0.1);
    }

    @Test
    public void testFindMedianSortedArrays5() {
        int[] nums1 = new int[2];
        nums1[0] = 1;
        nums1[1] = 2;
        int[] nums2 = new int[2];
        nums2[0] = 3;
        nums2[1] = 4;
        Assert.assertEquals(2.5, findMedianSortedArrays(nums1, nums2), 0.1);
    }

    private double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length, l2 = nums2.length, total = l1 + l2, odd = total % 2, i = 0, j = 0;
        int[] newnum = new int[total];
        for (int k = 0; k < total; k++) {
            if (i >= l1) {
                newnum[k] = nums2[j];
                j++;
                continue;
            }
            if (j >= l2) {
                newnum[k] = nums1[i];
                i++;
                continue;
            }
            if (nums1[i] < nums2[j]) {
                newnum[k] = nums1[i];
                i++;
            } else {
                newnum[k] = nums2[j];
                j++;
            }
        }
        return odd == 0 ? (double) (newnum[total / 2] + newnum[total / 2 - 1]) / 2 : newnum[total / 2];
    }
}
