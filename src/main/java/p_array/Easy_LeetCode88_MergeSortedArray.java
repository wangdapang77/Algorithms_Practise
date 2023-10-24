package p_array;

import java.util.Arrays;

/**
 * url: https://leetcode.cn/problems/merge-sorted-array/
 *
 *
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 *
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 *
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 *
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 * 示例 2：
 *
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * 解释：需要合并 [1] 和 [] 。
 * 合并结果是 [1] 。
 * 示例 3：
 *
 * 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
 * 输出：[1]
 * 解释：需要合并的数组是 [] 和 [1] 。
 * 合并结果是 [1] 。
 * 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
 *
 *
 * 提示：
 *
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[j] <= 109
 *
 *
 * 进阶：你可以设计实现一个时间复杂度为 O(m + n) 的算法解决此问题吗？
 */
public class Easy_LeetCode88_MergeSortedArray {

    /**
     * 简单解法
     * Arrays.sort 底层为快排算法 时间复杂度O(nlogn)
     * 所以此算法的时间复杂度为O((n+m)log(n+m))
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        // m + n = nums1的总长度
        for (int i = 0; i < n; i++) {
            nums1[m + i] = nums2[i];
        }

        Arrays.sort(nums1);
    }

    /**
     * 正向双指针
     * 此算法的时间复杂度为O(n+m)
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {

        int[] nums1_temp = new int[m+n];
        System.arraycopy(nums1, 0, nums1_temp, 0, m);

        // 指针1 指向nums1
        int p1 = 0;

        // 指针2 指向nums2
        int p2 = 0;

        // 总共循环 m + n 次 能填满nums1
        for (int i = 0; i < m+n; i++) {


            if (p1 == m) {
                // p1 == m 说明 p1的指针已经到最后一位了 则接下去使用nums2填充数据
                nums1[i] = nums2[p2];
                p2++;
            } else if(p2 == n) {

                // p2 == n 说明 p2的指针已经到最后一位了 则接下去使用nums1_temp填充数据
                nums1[i] = nums1_temp[p1];
                p1++;
            } else if (nums1_temp[p1] <= nums2[p2]) {

                // 以上都没进入 说明 两个指针取了两个数组的数据 需要进行大小比较 更小的数据进行填充
                nums1[i] = nums1_temp[p1];
                p1++;
            } else {
                nums1[i] = nums2[p2];
                p2++;
            }
        }
    }

    /**
     * 反向双指针
     * 此算法的时间复杂度为O(n+m)
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge3(int[] nums1, int m, int[] nums2, int n) {

        // 指针1 指向nums1
        int p1 = m - 1;

        // 指针2 指向nums2
        int p2 = n - 1;

        int lastIndex = m + n - 1;

        // 倒序的开头 是nums1的最后一位
        for (int i = lastIndex; i >= 0; i--) {

            if (p1 == -1) {
                // p1 == -1 说明 p1的指针已经处理完最后一位了 则接下去使用nums2填充数据
                nums1[i] = nums2[p2--];
            } else if(p2 == -1) {

                // p2 == n 说明 p2的指针已经处理完最后一位了 则接下去使用nums1填充数据
                nums1[i] = nums1[p1--];
            } else if (nums1[p1] > nums2[p2]) {

                // 以上都没进入 说明 两个指针取了两个数组的数据 需要进行大小比较 更大的数据进行填充
                nums1[i] = nums1[p1--];
            } else {
                nums1[i] = nums2[p2--];
            }
        }
    }

    public static void main(String[] args) {

        int[] nums1 = new int[3];

        nums1[0] = 1;
        nums1[1] = 2;
        nums1[2] = 3;

        int p = 0;
        for (int i = 0; i < 3; i++) {
            System.err.println(nums1[p++]);
        }
    }
}
