package code.hot;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU 缓存
 * @author Floweryu
 * @date 2024/3/4 15:58:36
 */
public class LRUCache {
    class Node {
        Node prev;
        Node next;

        int key;
        int value;
        public Node(){}
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    Map<Integer, Node> cache = new HashMap<>();
    int size;
    int capacity;
    Node head, tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 删除node
        deleteNode(node);
        // 将node移动到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            moveToHead(newNode);
            ++size;
            if (size > capacity) {
                Node last = tail.prev;
                deleteNode(tail.prev);
                cache.remove(tail.prev.key);
                --size;
            }
        } else {
            node.value = value;
            deleteNode(node);
            moveToHead(node);
        }
    }

    private void moveToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;

    }

    private void deleteNode(Node node) {
        // 改变node前指针指向
        node.prev.next = node.next;
        // 改变node后指针指向
        node.next.prev = node.prev;
    }

    public void print() {
        Node cur = head.next;
        while (cur != null && cur.next != null) {
            System.out.println("key: " + cur.key + "; value: " + cur.value);
            cur = cur.next;

        }
        System.out.println("-----------------");
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        int i;
        i = lruCache.get(1);
        System.out.println(i);
        lruCache.put(3, 3);
        i = lruCache.get(2);
        System.out.println(i);
        lruCache.put(4, 4);
        i = lruCache.get(1);
        System.out.println(i);
        i = lruCache.get(3);
        System.out.println(i);
        i = lruCache.get(4);
        System.out.println(i);
    }
}
