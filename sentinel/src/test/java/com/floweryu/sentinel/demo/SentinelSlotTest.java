package com.floweryu.sentinel.demo;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.context.Context;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.junit.Test;

/**
 * @author Floweryu
 * @date 2023/7/2 15:54
 */
public class SentinelSlotTest {

    @Test
    public void nodeSelectorSlotTest() throws BlockException {
        Context context1 = ContextUtil.enter("entrance1", "appA");
        Entry nodeA = SphU.entry("nodeA");
        if (nodeA != null) {
            nodeA.exit();
        }
        ContextUtil.exit();

        Context context2 = ContextUtil.enter("entrance2", "appA");
        nodeA = SphU.entry("nodeA");
        if (nodeA != null) {
            nodeA.exit();
        }
        ContextUtil.exit();
    }
}
