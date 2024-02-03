package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;


public class ArrayDequeTest {
    @Test
    public void RandomCompareTest(){
        LinkedListDequeByCircularSentinel<Integer> correctDeque = new LinkedListDequeByCircularSentinel<>();
        ArrayDeque<Integer> testDeque = new ArrayDeque<>();

        int N = 10000000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correctDeque.addLast(randVal);
                testDeque.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correctDeque.addFirst(randVal);
                testDeque.addFirst(randVal);
                System.out.println("addFirst(" + randVal + ")");
            }else if (operationNumber == 2) {
                // size
                assertEquals(correctDeque.size(), testDeque.size());
            }
            else if (operationNumber == 3){
                // remove first
                if (!correctDeque.isEmpty()){
                    System.out.println("remove First");
                    assertEquals(correctDeque.removeFirst(), testDeque.removeFirst());
                }
            } else if (operationNumber == 4){
                // remove last
                if (!correctDeque.isEmpty()){
                    System.out.println("remove Last");
                    assertEquals(correctDeque.removeLast(), testDeque.removeLast());
                }
            }
            else if (operationNumber == 5){
                // get
                if(correctDeque.isEmpty()){
                    continue;
                }
                int randVal = StdRandom.uniform(0, correctDeque.size());
                if (!correctDeque.isEmpty()){
                    assertEquals(correctDeque.get(randVal), testDeque.get(randVal));
                }
            }
        }
    }
}
