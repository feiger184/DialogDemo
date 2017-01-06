package com.feicui.dialogdemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private String[] string;
    private TextView textView;
    private ListView listView;
    private int position;
    private boolean[] pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);
        listView = (ListView) findViewById(R.id.listview);
        string = new String[]{
                "创建AlertDialog 简单对话框",
                "创建AlertDialog 普通列表对话框",
                "创建AlertDialog 单选列表对话框",
                "创建AlertDialog 多选列表对话框",
                "创建AlertDialog 带图标的列表对话框"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, string);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(listener);
    }

    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i) {
                case 0:
                    initAlertDialog();
                    break;
                case 1:
                    simpleListDialog();
                    break;
                case 2:
                    simpleCheckedListDialog();
                    break;
                case 3:
                    simpleMoreCheckedListDialog();
                    break;
                case 4:
                    simpleIconListDialog();
                    break;

            }
        }
    };

    /*
    * AlertDialog的简单使用
    * */
    private void initAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("提示：");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("确认退出吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "你点击了" + i, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "你点击了" + i, Toast.LENGTH_SHORT).show();

            }
        });

        builder.setNeutralButton("中立", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "你点击了" + i, Toast.LENGTH_SHORT).show();

            }
        });
        builder.create().show();

    }

    /*
    * 创建AlertDialog 普通列表对话框simpleListDialog
    * */
    private void simpleListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("请选择颜色：");
        builder.setIcon(R.mipmap.ic_launcher);
        final String[] arrColor = new String[]{"红色", "绿色", "蓝色"};
        builder.setItems(arrColor, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                textView.setText(arrColor[i]);
                switch (i) {
                    case 0:
                        textView.setTextColor(Color.RED);
                        break;
                    case 1:
                        textView.setTextColor(Color.GREEN);

                        break;
                    case 2:
                        textView.setTextColor(Color.BLUE);
                        break;
                    default:
                        break;
                }
            }
        });
        builder.show();
    }

    /*
    * 创建AlertDialog 对话框添加单选列表项
    * */
    private void simpleCheckedListDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("请选择颜色：");
        builder.setIcon(R.mipmap.ic_launcher);
        final String[] arrColor = new String[]{"红色", "绿色", "蓝色"};
        // 往对话框中放置单选列表
        builder.setSingleChoiceItems(arrColor, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                position = i;
            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText(arrColor[position]);
                switch (position) {
                    case 0:
                        textView.setBackgroundColor(Color.RED);
                        break;
                    case 1:
                        textView.setBackgroundColor(Color.GREEN);
                        break;
                    case 2:
                        textView.setBackgroundColor(Color.BLUE);
                        break;
                    default:
                        break;
                }
            }
        });
        builder.show();
    }

    /*
    * 创建AlertDialog 对话框添加多选列表项
    * */
    private void simpleMoreCheckedListDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("请选择颜色：");
        builder.setIcon(R.mipmap.ic_launcher);
        final String[] hobbyArr = new String[]{"看书", "学习", "饮食", "爬山", "绘画"};
        // 将多选项中每次勾选的结果放到一个pos数组中。
        pos = new boolean[hobbyArr.length];
        // 往对话框中放置多选列表

        builder.setMultiChoiceItems(hobbyArr, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                pos[i] = b;
            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < pos.length; j++) {
                    if (pos[j]) {
                        stringBuilder.append(hobbyArr[j]);
                        stringBuilder.append("、");
                    }
                }
                textView.setText("您勾选了：" + stringBuilder.toString());

            }
        });
        builder.show();
    }


    /*
    * 创建AlertDialog 对话框添加带图标列表项
    * */
    private void simpleIconListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("手机设置：");
        final String[] settingArr = new String[]{"看书", "学习", "饮食", "爬山", "绘画"};
        final int[] imageId = new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < settingArr.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("settingType", settingArr[i]);
            map.put("imageId", imageId[i]);
            list.add(map);
        }
        // 使用SimpleAdapter将数据源和自定义布局文件结合到一起。
        SimpleAdapter adapter = new SimpleAdapter(
                MainActivity.this, list, R.layout.item_dialog,
                new String[]{"settingType", "imageId"},
                new int[]{R.id.text_item_dialog,
                        R.id.imageView_item_dialog});
        // 调用builder的setAdapter()方法将适配器加载到对话框中。
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                textView.setText("您选择设置：" + settingArr[which]);
            }
        });
        // 显示对话框
        builder.show();

    }
}