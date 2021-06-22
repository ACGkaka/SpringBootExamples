package com.demo.example;

import java.util.function.Consumer;

/**
 * <p> @Title SortExample
 * <p> @Description 排序示例
 *
 * @author ACGkaka
 * @date 2021/6/22 10:15
 */
public class SortExample {

    public static void main(String[] args) {
        // 打印输出
        int[] printArray = new int[10];
        printTest(printArray, SortExample::bubbleSort, "冒泡排序");
        printTest(printArray, SortExample::insertSort, "插入排序");
        printTest(printArray, SortExample::selectSort, "选择排序");
        printTest(printArray, SortExample::quickSort, "快速排序");
        printTest(printArray, SortExample::mergeSort, "合并排序");

        // 性能测试
        int[] timeArray = new int[10000];
        timeTest(timeArray, SortExample::bubbleSort, "冒泡排序");
        timeTest(timeArray, SortExample::insertSort, "插入排序");
        timeTest(timeArray, SortExample::selectSort, "选择排序");
        timeTest(timeArray, SortExample::quickSort, "快速排序");
        timeTest(timeArray, SortExample::mergeSort, "合并排序");
    }

    /**
     * 打印测试
     *
     * @param array 目标数组
     * @param consumer 排序算法
     * @param sortType 算法名称
     */
    private static void printTest(int[] array, Consumer<int[]> consumer, String sortType) {
        // 正确性测试
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)(Math.random() * 10000);
        }
        consumer.accept(array);
        System.out.print(String.format("%s: ", sortType));
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i != array.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println();
    }

    /**
     * 耗时测试
     *
     * @param array 目标数组
     * @param consumer 排序算法
     * @param sortType 算法名称
     */
    private static void timeTest(int[] array, Consumer<int[]> consumer, String sortType) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < array.length; j++) {
                array[j] = (int)(Math.random() * 10000);
            }
            consumer.accept(array);
        }
        long end = System.currentTimeMillis();
        System.out.println(String.format("%s：%dms", sortType, end - start));
    }

    /**
     * 【冒泡排序】
     *
     * @param array 目标数组
     */
    public static void bubbleSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < length; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * 【插入排序】
     *
     * @param array 目标数组
     */
    public static void insertSort(int[] array) {
        int insertNode;
        int j;
        for (int i = 1; i < array.length; i++) {
            insertNode = array[i];
            j = i - 1;
            while(j >= 0 && insertNode < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = insertNode;
        }
    }

    /**
     * 【选择排序】
     *
     * @param array 目标数组
     */
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int pos = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[pos] > array[j]) {
                    pos = j;
                }
            }
            if (pos != i) {
                int temp = array[i];
                array[i] = array[pos];
                array[pos] = temp;
            }
        }
    }

    /**
     * 【快速排序】
     *
     * @param array 目标数组
     */
    public static void quickSort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    /**
     * 快速排序 - 1.递归比较大小，交换顺序
     * 时间复杂度：nlogn
     *
     * @param array 目标数组
     * @param start 开始位置
     * @param end 结束位置
     */
    private static void sort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = array[start];
        int left = start;
        int right = end;
        while (left <= right) {
            while (left <= right && array[left] < pivot) {
                left++;
            }
            while (left <= right && array[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }
        sort(array, start, right);
        sort(array, left, end);
    }

    /**
     * 【合并排序】
     * 时间复杂度：nlogn
     *
     * @param array 目标数组
     */
    public static void mergeSort(int[] array) {
        int[] temp = new int[array.length];
        mergeSortImpl(array, 0, array.length - 1, temp);
    }

    /**
     * 合并排序 - 1.递归拆分、合并数组
     *
     * @param array 目标数组
     * @param start 开始位置
     * @param end 结束位置
     * @param temp 临时数组
     */
    private static void mergeSortImpl(int[] array, int start, int end, int[] temp) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSortImpl(array, start, mid, temp);
        mergeSortImpl(array, mid + 1, end, temp);
        merge(array, start, mid, end, temp);
    }

    /**
     * 合并排序 - 2.合并数组
     *
     * @param array 目标数组
     * @param start 开始位置
     * @param mid 中间位置
     * @param end 结束位置
     * @param temp 临时数组
     */
    private static void merge(int[] array, int start, int mid, int end, int[] temp) {
        int left = start;
        int right = mid + 1;
        int index = start;
        while (left <= mid && right <= end) {
            if (array[left] < array[right]) {
                temp[index++] = array[left++];
            } else {
                temp[index++] = array[right++];
            }
        }
        while (left <= mid) {
            temp[index++] = array[left++];
        }
        while (right <= end) {
            temp[index++] = array[right++];
        }
        for (index = start; index <= end; index++) {
            array[index]= temp[index];
        }
    }
}
