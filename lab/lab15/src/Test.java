public class Test {
    public static void main(String[] args) throws Exception {
        Student s1 = new Student("Harry", "Potter", Gender.MALE);
        Student s2 = new Student("Ron", "Weasley", Gender.MALE);
        Student s3 = new Student("Hermione", "Granger", Gender.FEMALE);
        // test queue implementation
        Queue<Student> queue = new Queue<Student>();
        queue.enqueue(s1);
        queue.enqueue(s2);
        queue.enqueue(s3);
        System.out.println("---Queue: first in first out---");
        while(queue.hasItems()) {
            System.out.println(queue.dequeue());
        }

        // test stack implementation
        Stack<Student> stack = new Stack<Student>();
        stack.push(s1);
        stack.push(s2);
        stack.push(s3);
        System.out.println("---Stack: last in first out---");
        while(stack.hasItems()) {
            System.out.println(stack.pop());
        }
    }
}
