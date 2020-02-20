import java.util.ArrayList;

public class Stack<T> {
    private ArrayList<T> elements = new ArrayList<>();

    public void push(T element) {
        elements.add(element);
    }

    public T pop() {
        T a = elements.get(elements.size() - 1);
        elements.remove(elements.size() - 1);
        return a;
    }

    public boolean hasItems() {
        return elements.size() != 0;
    }

}
