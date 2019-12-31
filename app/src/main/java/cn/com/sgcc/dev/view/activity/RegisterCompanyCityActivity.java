package cn.com.sgcc.dev.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.adapter.CityCheckLvAdapter;
import cn.com.sgcc.dev.dbUtils.DaoUtil;
import cn.com.sgcc.dev.model2.ycsb.CZCS;
import cn.com.sgcc.dev.utils.PinYin2Abbreviation;

/**
 * <p>Title: RegisterCompanyCityActivity</p>
 * <p>Description: 公司所在城市选择</p>
 * <p>Company: </p>
 *
 * @author song
 * @date 2016/7/21
 */
public class RegisterCompanyCityActivity extends BaseActivity implements View.OnClickListener {
    //国家id
    public static final int CITYACTIVITY = 0x123;

    //选择国家并返回。如果有，标识要点击后返回上一页，并将接收的数据作为返回值得key
    public static final String REQUEST_TAG = "TAG";
    //用于后期关闭页面
    public static RegisterCompanyCityActivity instance;
    private String key = "";

    //顶部搜索和取消
    private EditText et_search_city;
    private TextView tv_cancel;

    //返回按钮
    private ImageView appBackIm;
    //中间文字
    private TextView appText;
    private ListView listView;
    private CityCheckLvAdapter lsAdapter;
    //所有的主营国家。原始数据
    private List<CZCS> cityData;
    //转换后的数据，设置给适配器的数据
    private List<CZCS> data1 = new ArrayList<>();
    private Intent intent;
    //展示字母
    private GridView letterGridView;
    private String[] letters = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };
    private LetterAdapter letterAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register_city);

        instance = this;
        initView();

        new Thread(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        }).run();

        if (getIntent().hasExtra(REQUEST_TAG)) {
            key = getIntent().getStringExtra(REQUEST_TAG);
        }
    }

    private void initData() {
        cityData.addAll(new DaoUtil(this).getCZCS(null, false));

        String[] a = sortIndex(cityData);

        data1.addAll(getAllLists(a));

        lsAdapter.setData(data1);
    }

    public void initView() {
        et_search_city = (EditText) findViewById(R.id.et_search_city);
        et_search_city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")) {
                    lsAdapter.setData(data1);
                } else {
                    String str = s.toString().trim();
                    if (str.matches("[a-zA-Z]")) {
                        str = str.toUpperCase();
                        int i = 0;
                        for (CZCS mc : data1) {
                            if (mc.getFirst().equals(str)) {
                                // 滑动选中的首字母到第一项
                                listView.setSelectionFromTop(i, 0);
                                return;
                            }
                            i++;
                        }
                    } else {
                        lsAdapter.setData(null);
                        List<CZCS> cList = new ArrayList<>();
                        for (int i = 0; i < data1.size(); i++) {
                            if (data1.get(i).getCzmc().contains(s.toString())) {
                                cList.add(data1.get(i));
                            }
                        }
                        lsAdapter.setData(cList);
                    }
                }
            }
        });

        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.main_countres_listview);

        letterGridView = (GridView) findViewById(R.id.main_countres_index_gridview);

        letterAdapter = new LetterAdapter();

        letterGridView.setAdapter(letterAdapter);

        letterGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickLetter = letters[position];//点击的字母
                int i = 0;
                for (CZCS mc : data1) {
                    if (mc.getFirst().equals(clickLetter)) {
                        // 滑动选中的首字母到第一项
                        listView.setSelectionFromTop(i, 0);
                        return;
                    }
                    i++;
                }
            }
        });

        cityData = new ArrayList<>();

        lsAdapter = new CityCheckLvAdapter(this);

        listView.setAdapter(lsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel://取消
                et_search_city.setText("");
                lsAdapter.setData(data1);
                break;
        }
    }


    /**
     * 把数据排序，并把A-Z顺序加进去
     *
     * @param cityData
     * @return
     */
    public String[] sortIndex(List<CZCS> cityData) {
        TreeSet<String> set = new TreeSet<>();
        for (CZCS mainCountres : cityData) {
            char ch = PinYin2Abbreviation.cn2py(mainCountres.getCzmc()).charAt(0);
            mainCountres.setFirst(String.valueOf(ch).toUpperCase(Locale.getDefault()));
            set.add(String.valueOf(ch).toUpperCase(Locale.getDefault()));// 获取首字母
        }
        String[] names = new String[cityData.size() + set.size()];// 新数组，用于保存首字母
        int i = 0;
        for (String string : set) { // 把set中的字母添加到新数组中（前面）
            names[i] = string;
            i++;
        }

        String[] pyheader = new String[cityData.size()];
        for (int j = 0, a = cityData.size(); j < a; j++) {
            pyheader[j] = PinYin2Abbreviation.cn2py(cityData.get(j).getFirst());
        }

        System.arraycopy(pyheader, 0, names, set.size(), pyheader.length);// 将转换为拼音的数组加到新数组后面
        // 自动按照首字母排序
        Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);// 严格按照字母顺序排序，忽略字母大小写，结果为按拼音排序的数组返回

        return names;
    }

    /**
     * 根据名字排序对数据进行排序 因为默认是数字在首位，为了把数字排到末尾，需要进行转换
     *
     * @param arry
     * @return
     */
    public ArrayList<CZCS> getAllLists(String[] arry) {
        ArrayList<CZCS> lists = new ArrayList<>();// 保存排好序的数据
        // 对数据进行排序
        int size = cityData.size();
        CZCS city;
        for (int i = 0, a = arry.length; i < a; i++) {
            if (i == 0) {
                for (int j = 0; j < size; j++) {
                    city = cityData.get(j);
                    if (arry[i].equals(city.getFirst())) {
                        lists.add(city);
                    }
                }
            } else if (!arry[i - 1].equals(arry[i])) {
                for (int j = 0; j < size; j++) {
                    city = cityData.get(j);
                    if (arry[i].equals(city.getFirst())) {
                        lists.add(city);
                    }
                }
            }
        }
        return lists;
    }

    class LetterAdapter extends BaseAdapter {
        @Override
        public Object getItem(int position) {
            return letters[position];
        }

        @Override
        public int getCount() {
            return letters.length;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(RegisterCompanyCityActivity.this).inflate(R.layout.item_letter, null);

                viewHolder.letterTv = (TextView) convertView.findViewById(R.id.item_letter_tv);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.letterTv.setText(letters[position]);

            return convertView;
        }

        class ViewHolder {
            TextView letterTv;
        }
    }
}
