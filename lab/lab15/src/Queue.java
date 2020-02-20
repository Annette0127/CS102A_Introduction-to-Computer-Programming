import java.util.ArrayList;

public class Queue<T> {
    private ArrayList<T> elements = new ArrayList<>();

    public void enqueue(T element) {
        elements.add(element);
    }

    public T dequeue() {
        T a = elements.get(0);
        elements.remove(0);
        return a;
    }

    public boolean hasItems() {
        return elements.size() != 0;
    }
}
