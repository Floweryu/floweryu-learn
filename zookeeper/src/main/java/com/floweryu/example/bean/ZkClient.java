package com.floweryu.example.bean;

import com.floweryu.example.bean.factory.ClientFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangjunfeng
 * @date 2022/3/1 19:54
 */
@Slf4j
@Component
public class ZkClient {

    public static final String ZK_ADDRESS = "106.15.42.148:2181";
    
    @Autowired
    private ZooKeeper zookeeper;
    
    
    public static CuratorFramework getClient() {
        CuratorFramework client = ClientFactory.createSimple(ZK_ADDRESS);
        client.start();
        return client;
    }

    /**
     * 判断指定节点是否存在
     * @param path  
     * @param needWatch 指定是否复用zookeeper中默认的Watcher
     * @return
     */
    public Stat exists(String path, boolean needWatch) {
        try {
            return zookeeper.exists(path, needWatch);
        } catch (Exception e) {
            log.error("Determine if the node exists error. info: {}", path, e);
            return null;
        }
    }

    /**
     * 检测节点是否存在，并设置监听事件
     * 三种监听类型：创建、删除、更新
     * @param path
     * @param watcher 传入指定的监听类
     * @return
     */
    public Stat exists(String path, Watcher watcher) {
        try {
            return zookeeper.exists(path, watcher);
        } catch (Exception e) {
            log.error("Determine if the node exists error. info: {}", path, e);
            return null;
        }
    }

    /**
     * 创建持久化节点
     * @param path
     * @param data
     * @return
     */
    public String createNode(String path, String data) {
        try {
            // ZooDefs.Ids.OPEN_ACL_UNSAFE: 访问控制权限
            // CreateMode.PERSISTEN: 持久化节点
            return zookeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException.NodeExistsException e) {
            log.error("The node is already exists. info: {}", path, e);
        } catch (Exception e) {
            log.error("Create persistent node error. info: {}", path, e);
        }
        return null;
    }

    /**
     * 修改持久化节点
     * @param path
     * @param data
     * @return
     */
    public boolean updateNode(String path, String data) {
        try {
            // zk的数据版本是从0开始计数的. 如果客户端传入的是-1, 则表示zk服务器需要基于最新的数据进行更新. 对数据节点更新没有原子性要求可以使用-1
            // version参数指定要更新的数据版本, 如果version和真实版本不同, 更新操作将失败, 指定version为-1则忽略版本检查
            zookeeper.setData(path, data.getBytes(), -1);
            return true;
        } catch (Exception e) {
            log.error("Update persistent node error. info: {}", path, e);
            return false;
        }
    }

    /**
     * 删除持久化节点
     * @param path
     * @return
     */
    public boolean deleteNode(String path) {
        try {
            zookeeper.delete(path, -1);
            return true;
        } catch (Exception e) {
            log.error("Delete persistent node error. info: {}", path, e);
            return false;
        }
    }

    /**
     * 获取当前节点的子节点
     * @param path
     * @return
     */
    public List<String> getChildren(String path) {
        try {
            return zookeeper.getChildren(path, false);
        } catch (Exception e) {
            log.error("Get children node error. info: {}", path, e);
            return new ArrayList<>();
        }
    }


    /**
     * 获取指定节点的值
     * @param path
     * @param watcher
     * @return
     */
    public String getData(String path, Watcher watcher) {
        try {
            byte[] bytes = zookeeper.getData(path, watcher, new Stat());
            return new String(bytes);
        } catch (Exception e) {
            log.error("Get data error. info: {}", path, e);
            return null;
        }
    }
}
