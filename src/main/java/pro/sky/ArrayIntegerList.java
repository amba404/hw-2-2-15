package pro.sky;

import java.util.Arrays;

public class ArrayIntegerList implements IntegerList {
    private static final int DEFAULT_LENGTH = 8;
    private Integer[] storage;
    private int size;

    public ArrayIntegerList(int size) {
        this.size = 0;
        storage = new Integer[size];
    }

    public ArrayIntegerList() {
        this(ArrayIntegerList.DEFAULT_LENGTH);
    }

    @Override
    public Integer add(Integer item) {
        checkNotNull(item);
        checkNewSize(size + 1);
        storage[size] = item;
        size++;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        if (index == size) {
            return add(item);
        }
        checkNotNull(item);
        checkIndex(index);
        checkNewSize(size + 1);
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkNotNull(item);
        checkIndex(index);
        storage[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkNotNull(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new ElementNotFoundException();
        }
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);
        Integer res = storage[index];
        System.arraycopy(storage, index + 1, storage, index, size - index);
        size--;
        storage[size] = null;
        return res;
    }

    @Override
    public boolean contains(Integer item) {
        checkNotNull(item);
        return binSearch(item) > -1;
    }

    private int binSearch(Integer item) {
        Integer[] arr = Arrays.copyOf(storage, size);
        sort(arr);
        if (size > 0 && (item < arr[0] || item > arr[arr.length - 1])) {
            return -1;
        }
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            Integer elemM = arr[m];
            if (item.equals(elemM)) {
                return m;
            } else if (item < elemM) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }

    @Override
    public int indexOf(Integer item) {
        checkNotNull(item);
        for (int i = 0; i < size; i++) {
            if (item.equals(storage[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        checkNotNull(item);
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(storage[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return storage[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        checkNotNull(otherList);
        if (this == otherList) {
            return true;
        } else if (otherList.size() != size) {
            return false;
        } else {
            for (int i = 0; i < size; i++) {
                if (!storage[i].equals(otherList.get(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        storage = new Integer[DEFAULT_LENGTH];
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(storage, size);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexWrongException("Index out of bounds");
        }
    }

    private void grow(int newSize) {
        Integer[] newList = new Integer[(int) (Math.max(storage.length * 1.618, newSize))];
        System.arraycopy(storage, 0, newList, 0, size);
        storage = newList;
    }

    private void checkNewSize(int newSize) {
        if (newSize > storage.length) {
            grow(newSize);
        }
    }

    private void checkNotNull(Object object) {
        if (object == null) {
            throw new NullParameterException("Expected parameter cannot be null");
        }
    }

    private Integer[] sort(Integer[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int middle = arr.length / 2;
        Integer[] arrL = sort(Arrays.copyOfRange(arr, 0, middle));
        Integer[] arrR = sort(Arrays.copyOfRange(arr, middle, arr.length));
        Integer[] arrC = new Integer[arr.length];

        int n = 0, k = 0, m = 0;
        while (n < arrL.length && m < arrR.length) {
            if (arrL[n] <= arrR[m]) {
                arrC[k++] = arrL[n++];
            } else {
                arrC[k++] = arrR[m++];
            }
        }

        while (n < arrL.length) {
            arrC[k++] = arrL[n++];
        }

        while (m < arrR.length) {
            arrC[k++] = arrR[m++];
        }

        System.arraycopy(arrC, 0, arr, 0, arr.length);
        return arr;
    }

}
