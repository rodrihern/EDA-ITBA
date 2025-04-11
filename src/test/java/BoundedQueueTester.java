import U3_EstructurasLineales.StackQueue.BoundedQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoundedQueueTester {
    
    private BoundedQueue<Integer> queue;
    private final int QUEUE_SIZE = 5;
    
    @BeforeEach
    public void setup() {
        queue = new BoundedQueue<>(QUEUE_SIZE);
    }
    
    @Test
    public void testNewQueueIsEmpty() {
        assertTrue(queue.isEmpty());
        assertFalse(queue.isFull());
    }
    
    @Test
    public void testEnqueueSingleElement() {
        queue.enqueue(1);
        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());
    }
    
    @Test
    public void testEnqueueUntilFull() {
        for (int i = 0; i < QUEUE_SIZE; i++) {
            queue.enqueue(i);
        }
        assertTrue(queue.isFull());
        assertFalse(queue.isEmpty());
    }
    
    @Test
    public void testEnqueueDequeue() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertTrue(queue.isEmpty());
    }
    
    @Test
    public void testCircularBehavior() {
        // Fill queue
        for (int i = 0; i < QUEUE_SIZE; i++) {
            queue.enqueue(i);
        }
        
        // Remove 3 elements
        assertEquals(0, queue.dequeue());
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        
        // Add 3 more elements which should wrap around
        queue.enqueue(5);
        queue.enqueue(6);
        queue.enqueue(7);
        
        // Check remaining elements
        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
        assertEquals(5, queue.dequeue());
        assertEquals(6, queue.dequeue());
        assertEquals(7, queue.dequeue());
        
        assertTrue(queue.isEmpty());
    }
    
    @Test
    public void testDequeueEmptyQueueThrowsException() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            queue.dequeue();
        });
        
        String expectedMessage = "Queue is empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
    @Test
    public void testEnqueueFullQueueThrowsException() {
        // Fill the queue
        for (int i = 0; i < QUEUE_SIZE; i++) {
            queue.enqueue(i);
        }
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            queue.enqueue(100);
        });
        
        String expectedMessage = "Queue is full";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
