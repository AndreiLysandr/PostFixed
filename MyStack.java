package ProiectTrei;
import java.util.Deque;
import java.util.LinkedList;

public class MyStack<T> {
    private Deque<T> stack;

    public MyStack() {
        this.stack = new LinkedList<>();
    }

    public T readElement() {
        return this.stack.peek();
    }

    public void addElement(T element) {
        this.stack.push(element);
    }

    public T removeElement() {
        return this.stack.pop();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
