package cn.com.sgcc.dev.model2;

/**
 * Created by Administrator on 2016/12/9.
 */
public class CommonFilter {

    private String title;
    private boolean isSelect;

    public CommonFilter() {
    }

    public CommonFilter(String search_data) {
        this.title = search_data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
