package ProiectTrei;

import java.util.Deque;
import java.util.LinkedList;

public class MyQueue<T> {
    private Deque<T> queue;

    public MyQueue() {
        this.queue = new LinkedList<>();
    }

    public MyQueue(MyQueue<T> q) {
        this.queue = new LinkedList<>();

        while (!q.isEmpty()) {
            this.queue.offer(q.removeElement());
        }
    }

    public T readElement() {
        return this.queue.peek();
    }

    public void addElement(T element) {
        this.queue.offer(element);
    }

    public T removeElement() {
        return this.queue.poll();
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
}
