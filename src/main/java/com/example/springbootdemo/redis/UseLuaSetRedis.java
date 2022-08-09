package com.example.springbootdemo.redis;

/**
 * @Description
 * @Date 2022/7/24
 */
public class UseLuaSetRedis {




    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int a = target - num;
            for (int j = i+1; j < nums.length; j++) {
                int num1 = nums[j];
                if(num1 == a ){
                    int[] b = {i,j};
                    return b;
                }
            }

        }
        return null;

    }

    public static void main(String[] args) {
        int[] nums = {2,5,5,11};
        int[] ints = twoSum(nums, 10);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

}



/**
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）
 * 原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 *
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，
 * 我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 *
 * 你的实现应该支持如下操作：
 *
 * MyCircularQueue(k):
 * Front:
 * Rear:
 * enQueue(value):
 * deQueue():
 * isEmpty():
 * isFull():
 *
 *
 * 示例：
 *
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 * circularQueue.enQueue(1);  // 返回 true
 * circularQueue.enQueue(2);  // 返回 true
 * circularQueue.enQueue(3);  // 返回 true
 * circularQueue.enQueue(4);  // 返回 false，队列已满
 * circularQueue.Rear();  // 返回 3
 * circularQueue.isFull();  // 返回 true
 * circularQueue.deQueue();  // 返回 true
 * circularQueue.enQueue(4);  // 返回 true
 * circularQueue.Rear();  // 返回 4
 *
 * 提示：
 *
 * 所有的值都在 0 至 1000 的范围内；
 * 操作数将在 1 至 1000 的范围内；
 * 请不要使用内置的队列库。
 *
 */
class MyCircularQueue {
    //头
    int header;
    //尾
    int tail;

    int[] num;

    int max ;

    //构造器，设置队列长度为 k
    public MyCircularQueue(int k) {
        max= k+1;
        num = new int[max];
        tail = header = 0;
    }

    //向循环队列插入一个元素。如果成功插入则返回真。
    public boolean enQueue(int value) {
        if(!isFull()){
            num[tail] = value;
            tail = (tail+1)%max;
            return true;
        }
        return false;
    }
    //从循环队列中删除一个元素。如果成功删除则返回真。
    public boolean deQueue() {
        if(!isEmpty()){
            header = (header+1)%max;
            return true;
        }
        return false;
    }
    //从队首获取元素。如果队列为空，返回 -1
    public int Front() {
        int result = -1;
        if(!isEmpty()){
            result = num[header];
        }
        return result;

    }
    //获取队尾元素。如果队列为空，返回 -1
    public int Rear() {
        if(isEmpty()){
            return -1;
        }
        int result = num[(tail-1+max)%max];
        return result;
    }
    //检查循环队列是否为空。
    public boolean isEmpty() {
        if(tail == header){
            return true;
        }
        return false;
    }
    //检查循环队列是否已满。
    public boolean isFull() {
        if((tail+1)%max != header){
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        MyCircularQueue myCircularQueue = new MyCircularQueue(10);
        System.out.println(myCircularQueue.enQueue(1));
        System.out.println(myCircularQueue.enQueue(2));
        System.out.println(myCircularQueue.enQueue(3));
        System.out.println(myCircularQueue.enQueue(4));
        int [] a = myCircularQueue.num;
        System.out.println(a[0]+"-"+a[1]+"-"+a[2]+"-"+a[3]+"-"+a[4]+"-"+a[5]);
        System.out.println("删一个"+myCircularQueue.deQueue());
        System.out.println("删一个"+myCircularQueue.deQueue());
        System.out.println("空了"+myCircularQueue.isEmpty());
        System.out.println("队尾"+myCircularQueue.Rear());
        System.out.println("队尾"+myCircularQueue.Rear());
        System.out.println("满了"+myCircularQueue.isFull());
        System.out.println("队首"+myCircularQueue.Front());
        System.out.println("插入一个"+myCircularQueue.enQueue(6));
        System.out.println("队尾"+myCircularQueue.Rear());
    }
}


class a2{
    public static void main(String[] args) {
        MyCircularQueue myCircularQueue = new MyCircularQueue(3);
        System.out.println(myCircularQueue.enQueue(1));
        System.out.println(myCircularQueue.enQueue(2));
        System.out.println(myCircularQueue.enQueue(3));
        System.out.println(myCircularQueue.enQueue(4));
        System.out.println(myCircularQueue.Rear());
        System.out.println(myCircularQueue.isFull());
        System.out.println(myCircularQueue.deQueue());
        System.out.println(myCircularQueue.enQueue(4));
        System.out.println(myCircularQueue.Rear());
    }

    static class MyCircularQueue {
        private int front;
        private int rear;
        private int capacity;
        private int[] elements;

        public MyCircularQueue(int k) {
            capacity = k + 1;
            elements = new int[capacity];
            rear = front = 0;
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            elements[rear] = value;
            rear = (rear + 1) % capacity;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            front = (front + 1) % capacity;
            return true;
        }

        public int Front() {
            if (isEmpty()) {
                return -1;
            }
            return elements[front];
        }

        public int Rear() {
            if (isEmpty()) {
                return -1;
            }
            return elements[(rear - 1 + capacity) % capacity];
        }

        public boolean isEmpty() {
            return rear == front;
        }

        public boolean isFull() {
            return ((rear + 1) % capacity) == front;
        }
    }
}
