package cn.com.sgcc.dev.model2.vo;

import java.util.List;

import cn.com.sgcc.dev.model2.ycsb.CZCS;

/**
 * <p>@description:</p>
 * <p>
 * author lixiaofei  on 2018/1/5 14:54
 * version 1.0.0
 */

public class DataHolder {
    private List<CZCS> orglist ;//原始数据
    private List<String>  characterList; //存在的字母数据
    private List<Integer>  characterPositionList; //首个字母的位置
    List<List> list5; //存筛选原始数据
    private String ssgs;
    private Boolean ghsjBoolean=false;

    public Boolean getGhsjBoolean() {
        return ghsjBoolean;
    }

    public void setGhsjBoolean(Boolean ghsjBoolean) {
        this.ghsjBoolean = ghsjBoolean;
    }

    public String getSsgs() {
        return ssgs;
    }

    public void setSsgs(String ssgs) {
        this.ssgs = ssgs;
    }

    public List<CZCS> getData() {
        return orglist;
    }

    public void setData(List<CZCS> orglist) {
        this.orglist = orglist;
    }

    public List<String> getZiMu() {
        return characterList;
    }

    public void setZiMu(List<String>  characterList) {
        this.characterList = characterList;
    }

    public List<Integer> getPosition() {
        return characterPositionList;
    }

    public void setPosition(List<Integer>  characterPositionList) {
        this.characterPositionList = characterPositionList;
    }

    public List<List> getFilter() {
        return list5;
    }

    public void setFilter(List<List>  list5) {
        this.list5 = list5;
    }

    private static final DataHolder holder = new DataHolder();

    public static DataHolder getInstance() {
        return holder;
    }

    /**
     * 初始化
     */
    public void init(){
        this.setData(null);
        this.setFilter(null);
        this.setPosition(null);
        this.setZiMu(null);
        this.setGhsjBoolean(false);
        this.setSsgs(null);
    }
}
