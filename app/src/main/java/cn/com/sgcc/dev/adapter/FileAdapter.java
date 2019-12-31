package cn.com.sgcc.dev.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.model2.regist.FileInfo;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0
 * @Email lizilei_warms@163.com
 * @since 2019/1/17 0017
 */

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {
    private List<FileInfo> data;
    private Context context;
    private Activity mActivity;
    private int layoutId;
    private Event event;
    private ArrayList<String> selectData;
    private boolean isChooseKey;

    public FileAdapter(List<FileInfo> data, Context context, int layoutId, Activity mActivity) {
        this.data = new ArrayList<>();
        this.selectData = new ArrayList<>();
        this.data.clear();
        this.data.addAll(data);
        this.context = context;
        this.mActivity = mActivity;
        this.layoutId = layoutId;
    }

    public void setData(List<FileInfo> data, boolean isChooseKey) {
        this.data.clear();
        this.data.addAll(data);
        this.isChooseKey = isChooseKey;
        notifyDataSetChanged();
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void addData(FileInfo content) {
        this.data.add(content);
        notifyDataSetChanged();
    }

    public FileInfo getDataByPostion(int postion) {
        if (data.size() <= postion)
            return null;
        return data.get(postion);
    }

    public List<FileInfo> getAllData() {
        return data;
    }

    public void clearAllData() {
        this.data.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    private int tempPosition = -1;  //记录已经点击的CheckBox的位置

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final FileInfo fileInfo = data.get(position);
        boolean isChecked = false;//初始化一个boolean值 来判断是否被选中
        for (String s : selectData
                ) {//遍历选中的集合
            if (s.trim().equals(fileInfo.getFilePath())) {//如果集合中的子元素与适配其中的路径相同
                isChecked = true;//判断已被选中
                break;//终止循环
            }
        }
        holder.checkBox.setId(position);    //设置当前position为CheckBox的id
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {//设置checkBox的点击事件
                if (b) {
                    selectData.clear();
                    if (tempPosition != -1) {
                        //根据id找到上次点击的CheckBox,将它设置为false.
                        CheckBox tempCheckBox = (CheckBox) mActivity.findViewById(tempPosition);
                        if (tempCheckBox != null) {
                            tempCheckBox.setChecked(false);
                        }
                    }
                    //保存当前选中CheckBox的id值
                    tempPosition = compoundButton.getId();
                    if (selectData.contains(fileInfo.getFilePath()))//如果集合中包含了该元素则直接返回
                        return;
                    else//否则添加
                        selectData.add(fileInfo.getFilePath());
                } else {    //当CheckBox被选中,又被取消时,将tempPosition重新初始化.
                    tempPosition = -1;
                    if (selectData.contains(fileInfo.getFilePath()))//如果集合中包含了该元素则移除
                        selectData.remove(fileInfo.getFilePath());
                    else//否则 返回
                        return;
                }
            }
        });
        holder.checkBox.setChecked(isChecked);
        holder.tvFileDir.setText(fileInfo.getFilePath().substring(fileInfo.getFilePath().lastIndexOf("/") + 1));

        if (isChooseKey && fileInfo.isDirectory()) {
            holder.imageView2.setBackgroundResource(R.drawable.file);
            holder.checkBox.setVisibility(View.GONE);
        } else if (isChooseKey) {
            holder.checkBox.setVisibility(View.VISIBLE);
            holder.imageView2.setBackgroundResource(R.mipmap.ic_launcher);
        }
        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isChooseKey) {
                    if (fileInfo.isDirectory()) {
                        event.enterNextDir(data.get(position));
                    } else {//选中文件
                        holder.checkBox.setChecked(!holder.checkBox.isChecked());
                    }
                } else {
                    if (!holder.checkBox.isChecked() && tempPosition == -1) //获取checkBox的选中状态来判断是否能进入下一个文件夹
                    {
                        event.enterNextDir(data.get(position));
                    } else {
                        Toast.makeText(context, "您已经选中文件夹", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public ArrayList<String> getSelectData() {
        return selectData;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFileDir;
        private RelativeLayout rootLayout;
        private CheckBox checkBox;
        private ImageView imageView2;

        public ViewHolder(View view) {
            super(view);
            tvFileDir = (TextView) view.findViewById(R.id.tvFileDir);
            rootLayout = (RelativeLayout) view.findViewById(R.id.rootLayout);
            checkBox = (CheckBox) view.findViewById(R.id.checkBox);
            imageView2 = (ImageView) view.findViewById(R.id.imageView2);
        }
    }

    /**
     * 在主界面中需要进行的操作
     */
    public interface Event {
        void enterNextDir(FileInfo fileInfo);//进入下一个文件夹
    }
}
