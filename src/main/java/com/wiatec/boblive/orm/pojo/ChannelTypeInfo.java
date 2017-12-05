package com.wiatec.boblive.orm.pojo;

/**
 * Created by xuchengpeng on 23/06/2017.
 */
public class ChannelTypeInfo {

    private int id;
    private String tag;
    private String name;
    private String icon;
    private String type;
    private short flag;
    private short isLock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public short getFlag() {
        return flag;
    }

    public void setFlag(short flag) {
        this.flag = flag;
    }

    public short getIsLock() {
        return isLock;
    }

    public void setIsLock(short isLock) {
        this.isLock = isLock;
    }

    @Override
    public String toString() {
        return "ChannelTypeInfo{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", type='" + type + '\'' +
                ", flag=" + flag +
                ", isLock=" + isLock +
                '}';
    }
}
