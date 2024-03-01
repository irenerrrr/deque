import deque.ArrayDeque;
import deque.Deque;
import deque.LinkedListDeque;
import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import java.util.Iterator;


public class ArrayDequeTest {

    @Test
    public void Test1() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 6; i++) {
            ad1.addLast(i);
        }
        assertThat(ad1.toString()).isEqualTo("[0, 1, 2, 3, 4, 5]");
        assertThat(ad1.toList()).containsExactly(0, 1, 2, 3, 4, 5).inOrder();

    }

    @Test
    public void IteratorTest() {
        Deque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("Front");
        assertThat(ad1).containsExactly("Front");
        ad1.addLast("Middle");
        assertThat(ad1).containsExactly("Front", "Middle").inOrder();
        ad1.addLast("Back");
        assertThat(ad1).containsExactly("Front", "Middle", "Back").inOrder();

    }
    @Test
    public void testEqualDeques() {
        Deque<String> lld1 = new ArrayDeque<>();
        Deque<String> lld2 = new ArrayDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        assertThat(lld1.toString()).isEqualTo("[front, middle, back]");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");
        assertThat(lld2.toString()).isEqualTo("[front, middle, back]");

        assertThat(lld1).isEqualTo(lld2);
        lld1.removeFirst();
        lld2.removeFirst();
        assertThat(lld1).isEqualTo(lld2);
        lld2.removeFirst();
        assertThat(lld1).isNotEqualTo(lld2);
    }





}
