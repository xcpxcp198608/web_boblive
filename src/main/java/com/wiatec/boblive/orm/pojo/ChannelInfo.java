package com.wiatec.boblive.orm.pojo;

/**
 * Created by xuchengpeng on 23/06/2017.
 */
public class ChannelInfo {
    private int id;
    private int channelId;
    private int skSequence;
    private int czSequence;
    private String tag;
    private String name;
    private String url;
    private String icon;
    private String country;
    private int type;
    private int style;
    private boolean visible;
    private boolean backupStart;
    private boolean locked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getSkSequence() {
        return skSequence;
    }

    public void setSkSequence(int skSequence) {
        this.skSequence = skSequence;
    }

    public int getCzSequence() {
        return czSequence;
    }

    public void setCzSequence(int czSequence) {
        this.czSequence = czSequence;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isBackupStart() {
        return backupStart;
    }

    public void setBackupStart(boolean backupStart) {
        this.backupStart = backupStart;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public String toString() {
        return "ChannelInfo{" +
                "id=" + id +
                ", channelId=" + channelId +
                ", skSequence=" + skSequence +
                ", czSequence=" + czSequence +
                ", tag='" + tag + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", country='" + country + '\'' +
                ", type=" + type +
                ", style=" + style +
                ", visible=" + visible +
                ", backupStart=" + backupStart +
                ", locked=" + locked +
                '}';
    }
}
