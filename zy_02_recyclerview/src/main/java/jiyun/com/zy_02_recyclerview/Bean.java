package jiyun.com.zy_02_recyclerview;

import java.util.List;

/**
 * Created by lenovo on 2017/9/4.
 */
public class Bean {


    /**
     * title : 新闻大事件
     * type : 0
     * content : 新闻新闻，发生了新闻
     * imgs : ["http://img.juhe.cn/joke/201412/19/B9EBF01A3C718DABB4C166356CC839A8.jpg","http://img.juhe.cn/joke/201412/19/62287B57ED97B8A06861ADA51D921CEB.jpg","http://img.juhe.cn/joke/201412/19/E3070767518CB4DFEA708DCCC332EE2F.jpg"]
     */

    private String title;
    private int type;
    private String content;
    private List<String> imgs;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }
}
