package com.example.zy.sagittarius.bean;

/**
 * Created by zhaoy on 2017/9/16.
 */

public interface HttpConstants {

    public static String BASE_URL = "https://news-at.zhihu.com/api/4/";

    /**
     * 软件版本查询
     */
    public static String CURRENT_VERSION = "https://news-at.zhihu.com/api/4/version/android/2.6.0";

    /**
     * 最新消息
     */
    public static String NEW_MESSAGE = "https://news-at.zhihu.com/api/4/news/latest";

    /**
     * 消息内容获取和离线下载
     * 最后加上需要获取的消息的id
     */
    public static String NEWS_OBTAIN_DOWN_LOADING = "https://news-at.zhihu.com/api/4/news/";

    /**
     * 过往消息
     * 知乎日报的生日为2013年5月19日 只能查询到20130320之前的
     * 最后加日期 格式为yyyyMMdd
     */
    public static String HISTORY_MESSAGE = "https://news-at.zhihu.com/api/4/news/before/";

    /**
     * 新闻额外信息
     * 最后加上新闻id
     */
    public static String STORY_EXTRA = "https://news-at.zhihu.com/api/4/story-extra/";

    /**
     * 新闻对应长评论查看
     */
    public static String LONG_COMMENTS = "https://news-at.zhihu.com/api/4/story/#{id}/long-comments";

    /**
     * 新闻对应短评论查看
     */
    public static String SHORT_COMMENTS = "https://news-at.zhihu.com/api/4/story/#{id}/short-comments";

    /**
     * 主题日报列表查看
     */
    public static String THEMES = "https://news-at.zhihu.com/api/4/themes";

    /**
     * 主题日报内容查看
     * 后跟主题日报的id
     */
    public static String DETAIL_THEME = "https://news-at.zhihu.com/api/4/theme/";

    /**
     * 热门信息
     */
    public static String HOT = "https://news-at.zhihu.com/api/3/news/hot";

    /**
     * 栏目总览
     */
    public static String SECTIONS = "http://news-at.zhihu.com/api/3/sections";

    /**
     * 栏目具体消息查看
     */
    public static String DETIAL_SECTION = "http://news-at.zhihu.com/api/3/section/";

    /**
     * 查看新闻的推荐者
     * 加入新闻id
     */
    public static String RECOMMENDERS = "https://news-at.zhihu.com/api/4/story/#{id}/recommenders";

    /**
     * 查看某个作者的主页
     */
    public static String EDITOR = "https://news-at.zhihu.com/api/4/editor/#{id}/profile-page/android";
}
