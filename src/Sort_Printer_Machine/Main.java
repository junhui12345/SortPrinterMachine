package Sort_Printer_Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();

        SortPrinter bubbleSortPrinter = new SortPrinter();
        bubbleSortPrinter.selection(new BubbleSort());
        for (int i = 0; i < 10; i++) {
            int randomNumber = random.nextInt(100) + 1;
            bubbleSortPrinter.insert(randomNumber);
        }

        System.out.println("버블소트");
        System.out.print("정렬 전 : ");
        bubbleSortPrinter.show();
        bubbleSortPrinter.run();
        System.out.print("정렬 후 : ");
        bubbleSortPrinter.show();

        SortPrinter selectionSortPrinter = new SortPrinter();
        selectionSortPrinter.selection(new SelectionSort());

        for (int i = 0; i < 10; i++) {
            int randomNumber = random.nextInt(100) + 1;
            selectionSortPrinter.insert(randomNumber);
        }

        System.out.println("선택정렬");
        System.out.print("정렬 전 : ");
        selectionSortPrinter.show();
        selectionSortPrinter.run();
        System.out.print("정렬 후 : ");
        selectionSortPrinter.show();

    }
}

interface Sort {
   void sort(List<Integer> nums);

    default void swap(List<Integer> nums, int x, int y) {
        int temp = nums.get(x);
        nums.set(x, nums.get(y));
        nums.set(y, temp);
    }
}

class BubbleSort implements Sort {
    @Override
    public void sort(List<Integer> nums) {
        //가장 큰 요소를 맨 끝으로 이동
        //첫 번째 반복 후에는 배열의 마지막 요소가 최댓값이므로 더 이상 비교할 필요가 없음, 이후 반복
        for (int i = 0; i < nums.size(); i++) {
            //nums.size()-1-i; 마지막에 위치한 정렬된 요소를 확인하지 않게..
            for (int j = 0; j < nums.size()-1-i; j++) {
                int x = nums.get(j);
                int y = nums.get(j + 1);
                if(x > y) {
                    swap(nums, j, j+1);
                }
            }
        }
    }
}

class SelectionSort implements Sort {
    @Override
    public void sort(List<Integer> nums) {
        //현재 위치부터 ~ 끝까지 중 최소값 인덱스 찾기
        //찾은 인덱스의 값과 현재 위치의 값을 교환
        for (int i = 0; i < nums.size()-1; i++) {
            int MINIDX = i;

            for (int j = i+1; j < nums.size(); j++) {
                if(nums.get(j) < nums.get(MINIDX)) {
                    MINIDX = j;
                }
            }

            swap(nums, MINIDX, i);
        }
    }
}

class SortPrinter {
    private final List<Integer> nums;
    private Sort sort;
    public SortPrinter() {
        this.nums = new ArrayList<>();
    }

    public void insert(int x) {
        nums.add(x);
    }

    public void selection(Sort sort) {
        this.sort = sort;
    }

    public void run() {
        if(this.sort == null) {
            System.out.println("선택된 정렬 알고리즘이 없습니다.");
            return;
        }
        this.sort.sort(this.nums);
    }

    public void show() {
        System.out.println(nums.toString());
    }
}