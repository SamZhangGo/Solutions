package samzhanggo.leetcode.practice.hard.findmediansortedarrays;

/**
 * @author: siming.zhang
 * 2021/5/22 14:55
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(solution.findMedianSortedArrays(nums1, nums2));
        nums1 = new int[]{};
        nums2 = new int[]{2, 3};
        System.out.println(solution.findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{3, 4};
        nums2 = new int[]{};
        System.out.println(solution.findMedianSortedArrays(nums1, nums2));
        nums1 = new int[]{3};
        nums2 = new int[]{-2, -1};
        System.out.println(solution.findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{2, 3, 4, 5};
        nums2 = new int[]{};
        System.out.println(solution.findMedianSortedArrays(nums1, nums2));

    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int remindCount = (nums1.length + nums2.length) % 2 == 0 ? 2 : 1;

        int indexStart1 = 0;
        int indexEnd1 = nums1.length - 1;
        int indexStart2 = 0;
        int indexEnd2 = nums2.length - 1;

        while (indexEnd1 - indexStart1 > 0) {
            if (indexEnd2 - indexStart2 < 0 && indexEnd1 - indexStart1 + 1 == remindCount) {
                break;
            }
            if (indexEnd2 - indexStart2 < 0) {
                indexEnd1--;
                indexStart1++;
            } else {
                if (nums1[indexStart1] <= nums2[indexStart2]) {
                    indexStart1++;
                } else {
                    indexStart2++;
                }
                if (nums1[indexEnd1] >= nums2[indexEnd2]) {
                    indexEnd1--;
                } else {
                    indexEnd2--;
                }
            }
        }
        while (indexEnd2 - indexStart2 > 0) {
            if (indexEnd1 - indexStart1 < 0 && indexEnd2 - indexStart2 + 1 == remindCount) {
                break;
            }
            if (indexEnd1 - indexStart1 < 0) {
                indexEnd2--;
                indexStart2++;
            } else {
                if (nums2[indexStart2] <= nums1[indexStart1]) {
                    indexStart2++;
                } else {
                    indexStart1++;
                }
                if (nums2[indexEnd2] >= nums1[indexEnd1]) {
                    indexEnd2--;
                } else {
                    indexEnd1--;
                }
            }
        }
        double average = 0;
        for (int i = indexStart1; i <= indexEnd1; i++) {
            average += nums1[i];
        }
        for (int i = indexStart2; i <= indexEnd2; i++) {
            average += nums2[i];
        }
        average = average / remindCount;
        return average;
    }
}
