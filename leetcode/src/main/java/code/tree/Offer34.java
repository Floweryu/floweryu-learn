package code.tree;

import java.util.*;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 * https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
 * @author: zhangjunfeng
 * @createTime: 2023/05/05
 */
public class Offer34 {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        dfs(root, target, new ArrayList<>());
        return res;
    }

    public void dfs(TreeNode root, int target, List<Integer> arr) {
        if (root == null) {
            return;
        }
        arr.add(root.val);
        dfs(root.left, target - root.val, arr);
        dfs(root.right, target - root.val, arr);

        if (target == root.val && root.left == null && root.right == null) {
            // 这里不能直接加入arr, 而是要复制一份
            // 因为直接加入是引用, arr后面还会变
            res.add(new ArrayList<>(arr));
            // 这里也不能return, 因为该节点不能影响其他节点, 还需要将它移除
        }
        // 如果当前叶子节点不满足target, 需要把他移除, 该节点不影响其他节点
        arr.remove(arr.size() - 1);
    }

    public List<List<Integer>> pathSum2(TreeNode root, int target) {
        // 存储最终结果
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 存储节点的父结点, 便于寻找完成路径 key: 节点 value: 父结点
        Map<TreeNode, TreeNode> map = new HashMap<>();
        // 层序遍历队列存储节点
        Queue<TreeNode> queueNode = new LinkedList<>();
        // 存储层序遍历到当前节点时，前面已经累加的sum
        Queue<Integer> sumQueue = new LinkedList<>();
        queueNode.offer(root);
        sumQueue.offer(0);
        while (!queueNode.isEmpty()) {
            TreeNode poll = queueNode.poll();
            int rec = sumQueue.poll() + poll.val;

            if (poll.left == null && poll.right == null && rec == target) {
                getPath(poll, res, map);
            } else {
                if (poll.left != null) {
                    // 记录left的父结点
                    map.put(poll.left, poll);
                    queueNode.offer(poll.left);
                    sumQueue.offer(rec);
                }
                if (poll.right != null) {
                    map.put(poll.right, poll);
                    queueNode.offer(poll.right);
                    sumQueue.offer(rec);
                }
            }
        }
        return res;
    }

    /**
     * 根据叶节点获取完整路径
     */
    public void getPath(TreeNode node, List<List<Integer>> res, Map<TreeNode, TreeNode> map) {
        LinkedList<Integer> tmp = new LinkedList<>();
        while (node != null) {
            tmp.addFirst(node.val);
            node = map.get(node);
        }
        res.add(tmp);
    }
}
