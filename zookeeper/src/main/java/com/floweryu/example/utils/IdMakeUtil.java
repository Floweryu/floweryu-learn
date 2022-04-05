package com.floweryu.example.utils;

import com.floweryu.example.bean.factory.ClientFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

/**
 * @author zhangjunfeng
 * @date 2022/3/29 17:00
 */
public class IdMakeUtil {
    public static final String ZK_ADDRESS = "106.15.42.148:2181";
    
    private static String createSeqNode(String pathPrefix) {
        CuratorFramework client = ClientFactory.createSimple(ZK_ADDRESS);
        try {
            client.start();
            String destPath = client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                    .forPath(pathPrefix);
            return destPath;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String makeId(String nodeName) {
        String seqNode = createSeqNode(nodeName);
        if (seqNode == null) {
            return null;
        }
        
        int index = seqNode.lastIndexOf(nodeName);
        if (index >= 0) {
            index += nodeName.length();
            return index <= seqNode.length() ? seqNode.substring(index) : "";
        }
        return seqNode;
    }
}
