package com.example.zy.sagittarius.bean;

import java.util.List;

public class Story {

    /**
     * date : 20180415
     * stories : [{"images":["https://pic1.zhimg.com/v2-47096fe5724b530a1c9e7722676fb6fc.jpg"],"type":0,"id":9678434,"ga_prefix":"041521","title":"人生清欢与清悲"},{"images":["https://pic2.zhimg.com/v2-7688ab1914839c16b53044b618458b21.jpg"],"type":0,"id":9678106,"ga_prefix":"041519","title":"总控制不住刷手机？别不承认了，你是在害怕失去"},{"images":["https://pic2.zhimg.com/v2-2420cbbb28d4828a83e365a559d7ce65.jpg"],"type":0,"id":9678429,"ga_prefix":"041518","title":"越来越多近两年拍的电影进到豆瓣 TOP250 排行榜，这是好事吗？"},{"images":["https://pic3.zhimg.com/v2-375eb0d3f93cd2ef4dcc530777e353aa.jpg"],"type":0,"id":9677584,"ga_prefix":"041518","title":"一个鲜有人涉足的地方，这里的老人说，从没见过汉族人"},{"images":["https://pic4.zhimg.com/v2-17b2ad08241c3eb5897661afdf8fb5f7.jpg"],"type":0,"id":9678126,"ga_prefix":"041517","title":"「哎呀酒量差没事，多喝几次你就练出来了」"},{"images":["https://pic2.zhimg.com/v2-7c36a506aa5339ef2026a2f4b491b165.jpg"],"type":0,"id":9678142,"ga_prefix":"041515","title":"婴儿的哭声，怎么那么让人奔溃？"},{"images":["https://pic1.zhimg.com/v2-91a2f11e2496bc0f11d20309e23cf4e8.jpg"],"type":0,"id":9675614,"ga_prefix":"041514","title":"是人都知道，拉出的翔就不能再吃进去\u2026\u2026可是，为什么？"},{"images":["https://pic1.zhimg.com/v2-6058a21116d921793b9f7b273a4f2c90.jpg"],"type":0,"id":9678278,"ga_prefix":"041513","title":"大数据杀熟算什么，你知道「大数据售假」吗？"},{"images":["https://pic1.zhimg.com/v2-515e9b6d00617d1975300e65d3d1aadc.jpg"],"type":0,"id":9678350,"ga_prefix":"041512","title":"大误 · 如何防止一觉醒来头发乱得跟鸡窝一样"},{"images":["https://pic4.zhimg.com/v2-24ee1c880d9576bd7a486eb68a64123b.jpg"],"type":0,"id":9678194,"ga_prefix":"041510","title":"做过 10 年定投，我来说说怎样选基金能避免你走弯路"},{"images":["https://pic1.zhimg.com/v2-469b7d3dc52b5dca4f2c5efbb85331cc.jpg"],"type":0,"id":9678258,"ga_prefix":"041509","title":"电影中一个响指就能控制人的「催眠术」是真的吗？"},{"images":["https://pic1.zhimg.com/v2-d1fc92827493c0f364e5fc05d364e360.jpg"],"type":0,"id":9678392,"ga_prefix":"041508","title":"本周热门精选 · 嘿，从别人那儿，学着点"},{"images":["https://pic1.zhimg.com/v2-b022973f7117fc3b0c306937273bebcc.jpg"],"type":0,"id":9678208,"ga_prefix":"041507","title":"成人世界的人际交往有哪些潜台词？"},{"images":["https://pic1.zhimg.com/v2-049b1f10608c4b64f9f64b8e4e80b760.jpg"],"type":0,"id":9678185,"ga_prefix":"041507","title":"上周去北大，终于体验了一把传说中的网红「游戏课」"},{"images":["https://pic1.zhimg.com/v2-caab8513f61bc140b76b251f148f6cc0.jpg"],"type":0,"id":9678377,"ga_prefix":"041506","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-5f877a07b960d0e92af9c3479b56c595.jpg","type":0,"id":9678392,"ga_prefix":"041508","title":"本周热门精选 · 嘿，从别人那儿，学着点"},{"image":"https://pic2.zhimg.com/v2-829911223f55b6f207eaf1f876705f61.jpg","type":0,"id":9677567,"ga_prefix":"041416","title":"因为炸鱼薯条，我错过了大胸妹子"},{"image":"https://pic4.zhimg.com/v2-8a1c23638732f404c90945f17720e51f.jpg","type":0,"id":9678327,"ga_prefix":"041419","title":"用户都跑去看短视频了，微信公众号现在是越来越难做了"},{"image":"https://pic3.zhimg.com/v2-4e0608a39d64a436388f426cfb11a1d6.jpg","type":0,"id":9678304,"ga_prefix":"041408","title":"为什么一些人看起来友善的人，却喜欢独来独往？"},{"image":"https://pic3.zhimg.com/v2-14a5cd953438c778029e5b1d6703919a.jpg","type":0,"id":9678166,"ga_prefix":"041415","title":"又找不到钥匙了，又忘记车停哪儿了，这不是「记性差」"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic1.zhimg.com/v2-47096fe5724b530a1c9e7722676fb6fc.jpg"]
         * type : 0
         * id : 9678434
         * ga_prefix : 041521
         * title : 人生清欢与清悲
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic2.zhimg.com/v2-5f877a07b960d0e92af9c3479b56c595.jpg
         * type : 0
         * id : 9678392
         * ga_prefix : 041508
         * title : 本周热门精选 · 嘿，从别人那儿，学着点
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
