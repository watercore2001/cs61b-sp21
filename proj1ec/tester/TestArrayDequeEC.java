package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void RandomTest(){
        ArrayDequeSolution<Integer> good = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> bad = new StudentArrayDeque<>();

        ArrayDequeSolution<String> commandStrings = new ArrayDequeSolution<>();

        for (int i = 0; i < 10000; i += 1) {
            // between [0, 1]
            double option = StdRandom.uniform();
            int item = StdRandom.uniform(1000);
            if (option < 0.25) {
                commandStrings.addLast("addFirst("+item+")");
                good.addFirst(item);
                bad.addFirst(item);

            } else if (option<0.5) {
                commandStrings.addLast("addLast("+item+")");
                good.addLast(item);
                bad.addLast(item);
            }
            else if (option<0.75){
                if (good.isEmpty()){
                    continue;
                }
                commandStrings.addLast("removeFirst()");
                String message = String.join("\n", commandStrings);
                assertEquals(message, good.removeFirst(), bad.removeFirst());

            }
            else {
                if (good.isEmpty()){
                    continue;
                }
                commandStrings.addLast("removeLast()");
                String message = String.join("\n", commandStrings);
                assertEquals(message, good.removeLast(), bad.removeLast());
            }
        }
    }
}
