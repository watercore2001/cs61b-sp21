package disjoint;

import static org.junit.Assert.*;
import org.junit.Test;


public class Main {

    @Test
    public void testDs(){
        DisjointSet ds = new WeightedQuickUnionPathCompressionDs(10);
        ds.connect(1,2);
        ds.connect(3,4);
        ds.connect(5,7);
        ds.connect(1,3);
        assertTrue(ds.isConnected(1,4));
        assertTrue(ds.isConnected(2,3));
        assertFalse(ds.isConnected(4,5));
        // assertFalse(ds.isConnected(5,100));
    }

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}