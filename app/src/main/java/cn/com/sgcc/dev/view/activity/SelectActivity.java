package cn.com.sgcc.dev.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.GoodsAttrListAdapter;
import cn.com.sgcc.dev.adapter.GoodsAttrsAdapter;
import cn.com.sgcc.dev.adapter.SelectAdapter;
import cn.com.sgcc.dev.model2.CommonFilter;
import cn.com.sgcc.dev.model2.vo.SaleAttributeVo;
import cn.com.sgcc.dev.utils.DeviceDateilsChooseUtils;
import cn.com.sgcc.dev.utils.DeviceDateilsUtilsTwo;
import cn.com.sgcc.dev.utils.PinyinComparators;
import cn.com.sgcc.dev.utils.TextPinyinUtil;
import cn.com.sgcc.dev.utils.ToastUtils;
import cn.com.sgcc.dev.view.fragment.BHSB.Fragment_Type_Ak;
import cn.com.sgcc.dev.view.viewinterface.MFCleanEditText;
import cn.com.sgcc.dev.view.viewinterface.NoScrollGridview;
import cn.com.sgcc.dev.view.viewinterface.SideBar;

public class SelectActivity extends BaseActivity {

    @BindView(R.id.app_search_edit)
    MFCleanEditText appSearchEdit;
    @BindView(R.id.btn_filter)
    Button btn_filter;
    @BindView(R.id.app_search_layout)
    LinearLayout appSearchLayout;
    @BindView(R.id.list_select)
    ListView listSelect;
    @BindView(R.id.app_toolbar_center)
    TextView app_toolbar_center;
    @BindView(R.id.tv_select_item_cancel)
    TextView tvSelectItemCancel;
    @BindView(R.id.app_toolbar_left)
    ImageView app_toolbar_left;
    @BindView(R.id.app_toolbar_sao)
    ImageView app_toolbar_sao;
    @BindView(R.id.left_zimu)
    SideBar leftZimu;
    @BindView(R.id.contact_dialog)
    TextView contactDialog;
    @BindView(R.id.yuguo_service)
    NoScrollGridview serviceGrid;

    private SelectAdapter selectAdapter;
    public List<String> list_data;
    public List<String> list_data_search;
    public List<CommonFilter> filters;
    public String type;
    public String putposition;
    public String akid;
    public boolean isMore;  //是否多选，一次设备名称为母线、断路器
    List<String> characterList = new ArrayList<>();
    List<Integer> characterPositionList = new ArrayList<>();
    private List<SaleAttributeVo> serviceList;
    private GoodsAttrListAdapter adapter;
    private GoodsAttrsAdapter serviceAdapter;
    private String[] serviceStr = new String[]{"南瑞继保", "国电南自", "四方继保",
            "许继电气", "长园深瑞", "国电南瑞"};
    Boolean booleanPX=false; //默认不排序
    String tg;//辅助保护套别筛选的标志

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_select);
        ButterKnife.bind(this);
        initview();
        initdata();
    }

    //对获取的数据排序及获取首字母列表(首次出现的字母列表以及对应的位置)
    public void getPXData(List<String> list_data) {
        Collections.sort(list_data, new PinyinComparators());  //排序
        if (list_data.size() > 0) {
            for (int i = 0; i < list_data.size(); i++) {
                //获取存在的字母
                String pinyin = TextPinyinUtil.getInstance().getPinyin(list_data.get(i));
                if("".equals(pinyin)){
                    if (!characterList.contains("#")) {
                        characterList.add("#");
                        characterPositionList.add(i);
                    }
                }else{
                    String character = (pinyin.charAt(0) + "").toUpperCase(Locale.ENGLISH);
                    if (!characterList.contains(character)) {
                        if (character.hashCode() >= "A".hashCode() && character.hashCode() <= "Z".hashCode()) { // 是字母
                            characterList.add(character);
                            characterPositionList.add(i);
                        } else {
                            if (!characterList.contains("#")) {
                                characterList.add("#");
                                characterPositionList.add(i);
                            }
                        }
                    }
                }

            }
        }
    }

    private void initview() {
        leftZimu.setTextView(contactDialog);

        app_toolbar_center.setText("请选择");
        app_toolbar_sao.setVisibility(View.GONE);
        appSearchEdit.setHint("请输入要查询的内容");
        btn_filter.setText("搜索");
        list_data = new ArrayList<>();
        list_data_search = new ArrayList<>();
        type = getIntent().getStringExtra("type") + "";
        if (type.equals("辅助保护套别")) {
            tg = getIntent().getStringExtra("tg") +"";
        }
        if (type.equals("制造厂家")) {
            serviceGrid.setVisibility(View.VISIBLE);
        }
        if (type.equals("制造厂家") || type.equals("装置型号") || type.equals("一次设备名称")
                || type.equals("单位名称") || type.equals("调度单位") || type.equals("设计单位")
                || type.equals("基建单位") || type.equals("运行单位") || type.equals("维护单位")) {
            leftZimu.setVisibility(View.VISIBLE);
            booleanPX=true;
        }else{
            booleanPX=false;
        }

        //常用六大厂家置顶显示
        serviceList = new ArrayList<SaleAttributeVo>();
        for (int i = 0; i < serviceStr.length; i++) {
            SaleAttributeVo vo = new SaleAttributeVo();
            vo.setValue(serviceStr[i]);
            serviceList.add(vo);
        }
        serviceAdapter = new GoodsAttrsAdapter(this);
        serviceGrid.setAdapter(serviceAdapter);
        serviceAdapter.notifyDataSetChanged(true, serviceList);
        serviceGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //设置当前选中的位置的状态为非。  arg2是点击的位置
                serviceList.get(arg2).setChecked(!serviceList.get(arg2).isChecked());
                sendData(serviceList.get(arg2).getValue(), 0);
                for (int i = 0; i < serviceList.size(); i++) {
                    //跳过已设置的选中的位置的状态
                    if (i == arg2) {
                        continue;
                    }
                    serviceList.get(i).setChecked(false);
                }
                serviceAdapter.notifyDataSetChanged(true, serviceList);
            }
        });


        Map<String, Object> map = null;
        if (getIntent().hasExtra("conditions")) {
            map = (Map<String, Object>) getIntent().getSerializableExtra("conditions");
            putposition = map.get("position") + "";
            if (type.equals("一次设备名称")) {
                String ycsblx = map.get("ycsblx") + "";
                isMore = ycsblx.equals("母线") || ycsblx.equals("断路器");
            } else if (type.equals("跳闸关系")) {
                isMore = true;
            } else if (type.equals("光纤接口模式")) {
                isMore = true;
            }
        }
        if (DemoActivity.instance.BHorFZ) {
            if (map.get("number").equals("1") || type.equals("一次设备类型") || type.equals("一次设备名称")
                    || type.equals("电压等级") || type.equals("单位名称") || type.equals("调度单位")
                    || type.equals("保护套别") || type.equals("跳闸关系") || type.equals("名称属性")) {
                list_data = DeviceDateilsChooseUtils.DeviceDateilsFind(type + "", this, map);
            } else {
                list_data = DeviceDateilsUtilsTwo.DeviceDateilsFind(type + "", this);
            }
            if (booleanPX){
                getPXData(list_data);
            }
        } else {
            //辅助设备查询数据
            list_data = DeviceDateilsChooseUtils.getFZDateilsFind(this, type + "", map,tg);
            if (booleanPX){
                getPXData(list_data);
            }
        }

        if (isMore) {
            tvSelectItemCancel.setVisibility(View.VISIBLE);
            filters = new ArrayList<>();
            for (String list_datum : list_data) {
                filters.add(new CommonFilter(list_datum));
            }
            selectAdapter = new SelectAdapter(this, filters, true, "");
        } else {
            tvSelectItemCancel.setVisibility(View.GONE);
            selectAdapter = new SelectAdapter(this, list_data, false);
        }
        selectAdapter.setData(list_data, characterList, characterPositionList, type);
        listSelect.setAdapter(selectAdapter);

        listSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isMore) {
                    filters.get(position).setSelect(!filters.get(position).isSelect());
                    selectAdapter.setDatas(filters);
                } else {
                    String value;
                    if (list_data_search != null && list_data_search.size() > 0) {
                        value = list_data_search.get(position) + "";
                    } else {
                        value = list_data.get(position) + "";
                    }
                    //数据是使用Intent返回
                    sendData(value, position);
                    if (type.equals("安控系统调度名")) {
                        akid = Fragment_Type_Ak.instance.akxt_id_list.get(position);
                    }
                }
            }
        });

        appSearchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (s.toString().equals(""))
//                    selectAdapter.setData(list_data, characterList, characterPositionList, type);
//                list_data_search.clear();
                if (s.toString().equals("")) {
                    if (isMore) {
                        tvSelectItemCancel.setVisibility(View.VISIBLE);
                        filters = new ArrayList<>();
                        for (String list_datum : list_data) {
                            filters.add(new CommonFilter(list_datum));
                        }
                        selectAdapter.setDatas(filters);
                    } else {
                        tvSelectItemCancel.setVisibility(View.GONE);
                        selectAdapter.setData(list_data, characterList, characterPositionList, type);
                    }
                    list_data_search.clear();
                }

            }
        });

        //设置右侧触摸监听
        leftZimu.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                // 该字母首次出现的位置
                if (s.equals("#")) {
                    listSelect.setSelection(0);
                } else {
                    int position = selectAdapter.getScrollPosition(s);
                    if (position != -1) {
                        listSelect.setSelection(position);
                    }
                }

//                if (s.equals("#")){
//                    //listSelect.scrollToPositionWithOffset(0, 0);
//                    listSelect.scrollBy(0, 0);
//                }else{
//                    //layoutManager.scrollToPositionWithOffset(selectAdapter.getScrollPosition(s), 0);
//                    listSelect.scrollBy(0, selectAdapter.getScrollPosition(s));
//                }
            }
        });
    }

    /**
     * 统一回调
     *
     * @param value
     */
    private void sendData(String value, int position) {
        Intent intent = new Intent();
        //把返回数据存入Intent,回传值
        intent.putExtra("result", "" + value);
        intent.putExtra("puttype", "" + type);
        intent.putExtra("putposition", putposition);
        if (type.equals("安控系统调度名")) {
            intent.putExtra("putposition", position+"");
        }
        //设置返回数据
        SelectActivity.this.setResult(RESULT_OK, intent);
        //关闭Activity
        finish();
    }

    private void initdata() {

    }

    @OnClick({R.id.tv_select_item_cancel, R.id.app_toolbar_left, R.id.app_toolbar_sao, R.id.btn_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_select_item_cancel:
                String value = "";
                for (CommonFilter filter : filters) {
                    if (filter.isSelect()) {
                        if (value.equals("")) {
                            value += filter.getTitle();
                        } else {
                            value += "," + filter.getTitle();
                        }
                    }
                }
                sendData(value, 0);
                break;
            case R.id.app_toolbar_left:
                finish();
                break;
            case R.id.app_toolbar_sao:
//                startActivity(new Intent(this, MainActivitys.class));
//                sendBroadcast(new Intent("cn.sgg.finishActivity"));
//                finish();
                break;
            case R.id.btn_filter://搜索
                String search = appSearchEdit.getText().toString() + "";
                if (search.equals("")) {
                    ToastUtils.showLongToast(this, "请输入要搜索的内容");
                    return;
                }
//                if (list_data.size() > 0) {
//                    list_data_search.clear();
//                    for (String list_datum : list_data) {
//                        if (list_datum.contains(search))
//                            list_data_search.add(list_datum);
//                    }
//                    selectAdapter.setData(list_data_search, characterList, characterPositionList, type);

                    if (list_data.size() > 0) {
                        list_data_search.clear();
                        for (String list_datum : list_data) {
                            if (list_datum.contains(search)) {
                                list_data_search.add(list_datum);
                            }
                        }

                    if (isMore) {
                        tvSelectItemCancel.setVisibility(View.VISIBLE);
                        filters = new ArrayList<>();
                        for (String list_datum : list_data_search) {
                            filters.add(new CommonFilter(list_datum));
                        }

                        selectAdapter.setDatas(filters);
                    } else {
                        tvSelectItemCancel.setVisibility(View.GONE);
                        selectAdapter.setData(list_data_search, characterList, characterPositionList, type);
                    }
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
