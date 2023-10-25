package arrays;

/**
 * url: https://leetcode.cn/problems/move-zeroes/description/
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 示例 2:
 *
 * 输入: nums = [0]
 * 输出: [0]
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 *
 * 进阶：你能尽量减少完成的操作次数吗？
 */
public class Easy_LeetCode283_MoveZeroes {

    /**
     * 双指针处理 循环2次
     * 第一次遍历非0的元素 全部往左边移动 遍历完成后剩下的全部为非0元素 即addIndex的长度的元素
     * 第二次遍历 从addIndex到数组结束 剩下的位置全部用0
     * @param nums
     */
    public void moveZeroes(int[] nums) {

        int length = nums.length;

        // 添加元素的指针
        int addIndex = 0;

        // 遍历数组的指针
        for (int i = 0; i < length; i++) {

            if (nums[i] == 0) {
                continue;
            }

            nums[addIndex++] = nums[i];
        }

        // 所有非0元素添加完后 剩下的位置全部0来填充
        int loopCount = length - addIndex;
        for (int i = 0; i < loopCount; i++) {
            nums[addIndex++] = 0;
        }
    }


    /**
     * 双指针处理 循环1次
     * @param nums
     */
    public void moveZeroes2(int[] nums) {

        int length = nums.length;

        // 添加元素的指针
        int addIndex = 0;
        // 遍历元素的指针
        int searchIndex = 0;

        // 遍历数组的指针
        while (searchIndex < length) {

            // 当遍历到元素不是0时 和 添加的元素互换 searchIndex的值永远大于等于addIndex的值
            if (nums[searchIndex] != 0) {

                int temp = nums[searchIndex];
                nums[searchIndex] = nums[addIndex];
                nums[addIndex++] = temp;
            }

            searchIndex++;
        }
    }

}
