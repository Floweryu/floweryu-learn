package concurent;

/**
 * @Author Floweryu
 * @Date 2024/7/4 11:47
 */
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentModificationExample {

    static class CampaignTntBo {
        private long tntProductId;

        public CampaignTntBo(long tntProductId) {
            this.tntProductId = tntProductId;
        }

        public long getTntProductId() {
            return tntProductId;
        }
    }

    static class TNTProduct {
        private long productId;

        public TNTProduct(long productId) {
            this.productId = productId;
        }

        public long getProductId() {
            return productId;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<TNTProduct> tntProducts = new ArrayList<>();
        List<CampaignTntBo> campaignTntBoList = new ArrayList<>();

        // 初始化tntProducts和campaignTntBoList
        for (int i = 0; i < 100000; i++) {
            tntProducts.add(new TNTProduct(i));
            campaignTntBoList.add(new CampaignTntBo(i));
        }

        List<Long> productIdSet = new ArrayList<>();
        for (TNTProduct tntProduct : tntProducts) {
            productIdSet.add(tntProduct.getProductId());
        }

        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(1);

        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 线程1：构建productIdSet
        executor.submit(() -> {
//            startLatch.countDown(); // 允许线程2开始
            try {
                // 等待线程2完成修改，再继续
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 移除没有查询到信息的TNT
            campaignTntBoList.removeIf(item -> {
                if (productIdSet.contains(item.getTntProductId())) {
                    System.out.println(STR."在删除: \{item.getTntProductId()}");
                    return true;
                }
                return false;
            });
        });

        // 线程2：在removeIf执行期间，尝试修改campaignTntBoList
        executor.submit(() -> {
            for (long i = 0; i < 10000; i++) {
                System.out.println(STR."我在添加: \{i}");
                campaignTntBoList.add(new CampaignTntBo(i));
                try {
                    // 等待线程1开始removeIf操作
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 关闭线程池
        executor.shutdown();
        executor.awaitTermination(1, java.util.concurrent.TimeUnit.MINUTES);
    }
}
