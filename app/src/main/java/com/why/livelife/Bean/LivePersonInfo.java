package com.why.livelife.Bean;

/**
 * Created by lenovo on 2017/5/5.
 */

public class LivePersonInfo {
    /**
     * 性别
     */
    private int sex;
    /**
     * 直播地址
     */
    private String rtspUrl;
    /**
     * 缩略图地址
     */
    private String thumbPicUrl;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 观看量
     */
    private int viewCount;
    /**
     * 粉丝数
     */
    private int fansCount;
    /**
     * 是否在直播
     */
    private int isLiving;

    /**
     * 直播类型
     */
    private String liveCategory;

    /**
     * 直播描述
     */
    private String liveDesc;

    public String getLiveCategory() {
        return liveCategory;
    }

    public void setLiveCategory(String liveCategory) {
        this.liveCategory = liveCategory;
    }

    public String getLiveDesc() {
        return liveDesc;
    }

    public void setLiveDesc(String liveDesc) {
        this.liveDesc = liveDesc;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getRtspUrl() {
        return rtspUrl;
    }

    public void setRtspUrl(String rtspUrl) {
        this.rtspUrl = rtspUrl;
    }

    public String getThumbPicUrl() {
        return thumbPicUrl;
    }

    public void setThumbPicUrl(String thumbPicUrl) {
        this.thumbPicUrl = thumbPicUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public int getIsLiving() {
        return isLiving;
    }

    public void setIsLiving(int isLiving) {
        this.isLiving = isLiving;
    }
}
