import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void checkStudentArraryDeque() {
        StudentArrayDeque<Integer> array1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> array2 = new ArrayDequeSolution<>();
        String message = "";

        for (int i = 0; i < 100; i++) {
            double numberBetween01 = StdRandom.uniform();
            if (!array1.isEmpty() && numberBetween01 >= 0.8) {
                message += "removeFirst()\n";
                assertEquals(message, array1.removeFirst(), array2.removeFirst());
            } else if (!array1.isEmpty() && numberBetween01 >= 0.6 && numberBetween01 < 0.8) {
                message += "removeLast()\n";
                assertEquals(message, array1.removeLast(), array2.removeLast());
            } else if (!array1.isEmpty() && numberBetween01 >= 0.3 && numberBetween01 < 0.6) {
                array1.addFirst(i);
                array2.addFirst(i);
                message += "addFirst(" + i + ")\n";
                assertEquals(message, array1.get(0), array2.get(0));
            } else {
                array1.addLast(i);
                array2.addLast(i);
                message += "addLast(" + i + ")\n";
                assertEquals(message, array1.get(array1.size() - 1), array2.get(array2.size() - 1));
            }
            message += "size()\n";
            assertEquals(message, array1.size(), array2.size());
        }
    }
}
