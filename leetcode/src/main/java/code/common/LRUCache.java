package code.common;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU算法: 哈希表+双向链表实现
 * 1. 双向链表按照被使用的顺序来存储, 靠近头部的节点是最近使用的, 靠近尾部的节点是最久未使用的
 * 2. 哈希表存储key和node映射关系, 通过key能快速定位到链表中的节点
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
    
    /**
     * 1. 先判断key是否存在, 不存在返回-1
     * 2. 若key存在, 则key对应的节点就是最近访问节点, 通过哈希表映射到在双向链表中的位置, 然后将节点移动到链表头部
     * @param key
     * @return
     */
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // key存在则移动到链表头部, 表示最近访问
        moveToHead(node);
        return node.value;
    }
    
    /**
     * 1. 如果key不存在, 创建一个新节点并在链表头部添加该节点, 判断链表长度是否超出容量限制, 若超出容量, 则删除链表尾部结点
     * 2. 如果key存在, 覆盖旧值, 将节点移动到头部
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // node不存在, 则创建一个新节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加到链表头部, 表示最近访问
            addToHead(newNode);
            // 链表长度加1
            ++size;
            // 如果超出缓存容量
            if (size > capacity) {
                // 删除链表最后一个结点, 去掉最长时间未访问的
                DLinkedNode tail = removeTail();
                // 去掉哈希表中对应节点
                cache.remove(tail.key);
                // 减小链表长度
                --size;
            }
        } else {
            // 如果缓存中有
            // 先覆盖旧值
            node.value = value;
            // 再将节点移到链表头部, 表示最近访问
            moveToHead(node);
        }
    }
    
    /**
     * 添加一个结点需要修改四条链
     * @param node
     */
    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    
    /**
     * 删除一个结点需要修改两条链
     * @param node
     */
    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    /**
     * 把结点移到头部
     */
    private void moveToHead(DLinkedNode node) {
        // 先删除节点
        removeNode(node);
        // 再将该节点移到头部
        addToHead(node);
    }
    
    /**
     * 删除尾结点并返回
     */
    private DLinkedNode removeTail() {
        DLinkedNode last = tail.prev;
        removeNode(last);
        return last;
    }
    
    public void print() {
        DLinkedNode cur = head.next;
        while (cur != null && cur.next != null) {
            System.out.println("key: " + cur.key + "; value: " + cur.value);
            cur = cur.next;
            
        }
        System.out.println("-----------------");
    }
}
