package exercise;

import java.util.Arrays;

class SafetyList {
    // BEGIN
    private int[] list = new int[0];

    public synchronized void add(int num) {
        var extendedArr = Arrays.copyOf(list, list.length + 1);
        int length = extendedArr.length;
        extendedArr[length - 1] = num;
        list = extendedArr;
    }

    public int get(int index) {
        if (list.length == 0) {
            return -1;
        } else if (list.length >= index) {
            return -1;
        }
        return list[index];
    }

    public int getSize() {
        return list.length;
    }
    // END
}
