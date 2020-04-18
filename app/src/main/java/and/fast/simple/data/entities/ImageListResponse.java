package and.fast.simple.data.entities;

import java.util.List;

public class ImageListResponse {

    /**
     * data : [{"_id":"5e8c80682bce50b3ceaa80ea","author":"鸢媛","category":"Girl","createdAt":"2020-04-07 08:00:00","desc":"当你越来越优秀的时候，就会遇见越来越好的人。","images":["http://gank.io/images/341ecaacdd7d4cc09cd0de100f3eab46"],"likeCounts":0,"publishedAt":"2020-04-07 08:00:00","stars":1,"title":"第48期","type":"Girl","url":"http://gank.io/images/341ecaacdd7d4cc09cd0de100f3eab46","views":285},{"_id":"5e8c804c3f6af49cb7780b2b","author":"鸢媛","category":"Girl","createdAt":"2020-04-06 08:00:00","desc":"爱就是，我以为我要变得足够好才能遇见你，却发现原来是遇见了你，我才变成一个最好的我。","images":["http://gank.io/images/7878d08eb776401a85deeb203372665c"],"likeCounts":0,"publishedAt":"2020-04-06 08:00:00","stars":1,"title":"第47期","type":"Girl","url":"http://gank.io/images/7878d08eb776401a85deeb203372665c","views":282},{"_id":"5e8745402bce50b3ceaa80dd","author":"鸢媛","category":"Girl","createdAt":"2020-04-05 08:00:00","desc":"月亮是我抛的硬币，\n两面都是想见你 。","images":["http://gank.io/images/7f64754ca07e4af3a242399fd37c2432"],"likeCounts":0,"publishedAt":"2020-04-05 08:00:00","stars":1,"title":"第46期","type":"Girl","url":"http://gank.io/images/7f64754ca07e4af3a242399fd37c2432","views":345},{"_id":"5e874524945ed1af2eda88c5","author":"鸢媛","category":"Girl","createdAt":"2020-04-04 08:00:00","desc":"决定转身就不要频频回头，酷的人才会被记得久一些。 \u200b\u200b","images":["http://gank.io/images/cdd7031fa92d40e18a715035b686b4c4"],"likeCounts":0,"publishedAt":"2020-04-04 08:00:00","stars":1,"title":"第45期","type":"Girl","url":"http://gank.io/images/cdd7031fa92d40e18a715035b686b4c4","views":637},{"_id":"5e87450e945ed1af2eda88c3","author":"鸢媛","category":"Girl","createdAt":"2020-04-03 08:00:00","desc":"如果那个人是真的爱你，你走不掉的，跑不了的，\n除非他有意放你走，你还有什么不明白。","images":["http://gank.io/images/58389e1189534e1cb75b7a788f6b8a86"],"likeCounts":0,"publishedAt":"2020-04-03 08:00:00","stars":1,"title":"第44期","type":"Girl","url":"http://gank.io/images/58389e1189534e1cb75b7a788f6b8a86","views":292},{"_id":"5e8200918402c5364e3ac153","author":"鸢媛","category":"Girl","createdAt":"2020-04-02 08:00:00","desc":"时光太瘦，指缝太宽，\n不经意的一瞥，已隔经年。","images":["http://gank.io/images/9770422c45294684af50f725049d7c07"],"likeCounts":1,"publishedAt":"2020-04-02 08:00:00","stars":1,"title":"第43期","type":"Girl","url":"http://gank.io/images/9770422c45294684af50f725049d7c07","views":292},{"_id":"5e8200698402c5364e3ac152","author":"鸢媛","category":"Girl","createdAt":"2020-04-01 08:00:00","desc":"与我相遇，是此生最美的风景。\n我的一生借你一程，\n这一程便是余生。\n你我之间无人背叛，朝夕相安。","images":["http://gank.io/images/54fc1a56dbc44b52b23714030a457a1b"],"likeCounts":0,"publishedAt":"2020-04-01 08:00:00","stars":1,"title":"第42期","type":"Girl","url":"http://gank.io/images/54fc1a56dbc44b52b23714030a457a1b","views":534},{"_id":"5e82003ed5b9e4e6b5920fae","author":"鸢媛","category":"Girl","createdAt":"2020-03-31 08:00:00","desc":"快乐的人没有过去，\n不快乐的人除了过去一无所有。","images":["http://gank.io/images/0fdac44dada5489b85049a3f3fb7fd85"],"likeCounts":0,"publishedAt":"2020-03-31 08:00:00","stars":1,"title":"第41期","type":"Girl","url":"http://gank.io/images/0fdac44dada5489b85049a3f3fb7fd85","views":394},{"_id":"5e7a178f42e738699c989f9b","author":"鸢媛","category":"Girl","createdAt":"2020-03-30 08:00:00","desc":"你还记得让你最遗憾的是什么吗？ \u200b\u200b\u200b\u200b","images":["http://gank.io/images/79f717dc495645dfb4e9c43f4674fa30"],"likeCounts":0,"publishedAt":"2020-03-30 08:00:00","stars":1,"title":"第40期","type":"Girl","url":"http://gank.io/images/79f717dc495645dfb4e9c43f4674fa30","views":903},{"_id":"5e7785ba181a6495ef1172ad","author":"鸢媛","category":"Girl","createdAt":"2020-03-29 08:00:00","desc":"突如其来的脾气，往往是积攒很久了的委屈。❤","images":["http://gank.io/images/e6b78c1949d5438fa37ff2f272e5f1d0"],"likeCounts":0,"publishedAt":"2020-03-29 08:00:00","stars":1,"title":"第39期","type":"Girl","url":"http://gank.io/images/e6b78c1949d5438fa37ff2f272e5f1d0","views":533}]
     * page : 2
     * page_count : 6
     * status : 100
     * total_counts : 58
     */

    private int               page;
    private int               page_count;
    private int               status;
    private int               total_counts;
    private List<ImageEntity> data;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal_counts() {
        return total_counts;
    }

    public void setTotal_counts(int total_counts) {
        this.total_counts = total_counts;
    }

    public List<ImageEntity> getData() {
        return data;
    }

    public void setData(List<ImageEntity> data) {
        this.data = data;
    }


}
