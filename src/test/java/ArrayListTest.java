public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();

        // addToFront のテスト
        list.addToFront(1);
        list.addToFront(2);
        list.addToFront(3);
        System.out.println("After addToFront: ");
        printList(list);

        // addToBack のテスト
        list.addToBack(4);
        list.addToBack(5);
        System.out.println("After addToBack: ");
        printList(list);

        // removeFromFront のテスト
        System.out.println("Removed from front: " + list.removeFromFront());
        System.out.println("After removeFromFront: ");
        printList(list);

        // removeFromBack のテスト
        System.out.println("Removed from back: " + list.removeFromBack());
        System.out.println("After removeFromBack: ");
        printList(list);
    }

    private static void printList(ArrayList<Integer> list) {
        Object[] array = list.getBackingArray();  // Object[] 型にキャストせずそのまま取得
        for (int i = 0; i < list.size(); i++) {
            System.out.print((Integer) array[i] + " ");  // 個々の要素をキャストして出力
        }
        System.out.println();
    }
}
