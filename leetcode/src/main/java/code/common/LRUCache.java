package code.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangjunfeng
 * @date 2023/2/2 16:15
 */
public class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {
            key = _key;
            value = _value;
        }
    }
    
    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;
    
    public LRUCache(int _capacity) {
        this.size = 0;
        this.capacity = _capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // key存在则移动到
        moveToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            
        }
    }
    
    /**
     * 添加一个结点需要修改四条链
     * @param node
     */
    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        head.next.prev = node;
    }
    
    /**
     * 删除一个结点需要修改两条链
     * @param node
     */
    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    private void moveToHead(DLinkedNode node) {
        // 先删除节点
        removeNode(node);
        // 再将该节点移到头部
        addToHead(node);
    }
    
    private DLinkedNode removeTail() {
        DLinkedNode last = tail.prev;
        removeNode(last);
        return last;
    }
}
