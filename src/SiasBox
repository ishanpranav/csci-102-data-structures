import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public final class SiasBox {
    private static final int OPERATOR_PUSH = 1;
    private static final int OPERATOR_POP = 2;
    
    private SiasBox() {
    }
    
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            final PriorityQueue<Integer> maxPriorityQueue = new PriorityQueue<Integer>((left, right) -> right - left);
            final Queue<Integer> queue = new ArrayDeque<Integer>();
            final Stack<Integer> stack = new Stack<Integer>();
            final int n = scanner.nextInt();
            
            boolean isMaxPriorityQueue = true;
            boolean isQueue = true;
            boolean isStack = true;
            
            for (int i = 0; i < n; i++) {
                final int operator = scanner.nextInt();
                final int operand = scanner.nextInt();
                
                switch (operator) {
                    case OPERATOR_PUSH:
                        maxPriorityQueue.add(operand);
                        queue.add(operand);
                        stack.push(operand);
                        break;
                        
                    case OPERATOR_POP:
                        if (isMaxPriorityQueue && maxPriorityQueue.remove() != operand) {
                            isMaxPriorityQueue = false;
                        }
                        
                        if (isQueue && queue.remove() != operand) {
                            isQueue = false;
                        }
                        
                        if (isStack && stack.pop() != operand) {
                            isStack = false;
                        }
                        break;
                }
            }
            
            if (isMaxPriorityQueue && !isQueue && !isStack) {
                System.out.println("priority queue");
                
                return;
            }
            
            if (isQueue && !isMaxPriorityQueue && !isStack) {
                System.out.println("queue");
                
                return;
            }
            
            if (isStack && !isMaxPriorityQueue && !isQueue) {
                System.out.println("stack");
            
                return;
            }
            
            if (!isMaxPriorityQueue && !isQueue && !isStack) {
                System.out.println("impossible");
                
                return;
            }
            
            System.out.println("not sure");
        }
    }
}
