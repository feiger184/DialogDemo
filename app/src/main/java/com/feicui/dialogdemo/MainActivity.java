package com.feicui.dialogdemo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
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
                "创建AlertDialog 带图标的列表对话框",
                "创建AlertDialog 自定义对话框",
                "创建ProgressDialog 对话框",
                "DatePickerDialog 日期选择对话框"
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
                case 5:
                    CustomDialog();
                    break;
                case 6:
                    ProgressDialogDemo();
                    break;
                case 7:
                    DatePickerDialogDemo();
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



    /*自定义布局文件；
     使用LayoutInflater 的 inflater()方法填充自定义的布局文件，返回view对象。
     用该对象的findViewById()方法加载自定义布局上所有控件；
     调用Builder对象的setView()方法加载view对象；
     调用Builder对象的create()方法创建AlertDialog对话框；
     调用AlertDialog的show()方法来显示对话框。*/

    private void CustomDialog() {
        View view = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("管理员登录：");
        LayoutInflater mInflater = LayoutInflater.from(MainActivity.this);
        view = mInflater.inflate(R.layout.loginform_main, null);
        final EditText edit_loginform_username = (EditText) view
                .findViewById(R.id.edit_loginform_username);
        final EditText edit_loginform_password = (EditText) view
                .findViewById(R.id.edit_loginform_password);
        builder.setView(view);
        builder.setPositiveButton("登录", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String username = edit_loginform_username.getText()
                        .toString().trim();
                String password = edit_loginform_password.getText()
                        .toString().trim();
                if (username.length() < 1 || password.length() < 1) {
                    textView.setText("用户名或密码不可以为空！");
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("您的登录信息：");
                    sb.append("用户名：");
                    sb.append(username);
                    sb.append("密码：");
                    sb.append(password);
                    textView.setText(sb.toString());
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //此处进行异步任务访问网络等处理。
            }
        });
        builder.create().show();

    }



    /*实例化ProgressDialog，创建出ProgressDialog对象；
    调用该对象的方法设置图标、标题、内容、按钮等；
    setTitle()：为对话框设置标题setIcon ()：设置图标setMessage ()：设置要显示的信息
    调用 ProgressDialog 对象的show()方法显示出 ProgressDialog 对话框。*/
    private void ProgressDialogDemo() {

        ProgressDialog pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setTitle("提示：");
        pDialog.setMessage("数据加载中。。。");
        pDialog.show();
    }


    /*创建 DatePickerDialog 对话框的步骤：
    实例化 DatePickerDialog  ，创建出 DatePickerDialog  对象；
    调用  DatePickerDialog  对象的show()方法显示出  DatePickerDialog  对话框；
    绑定监听器：OnDateSetListener().*/
    private void DatePickerDialogDemo() {

        // 获取系统当前日期
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                textView.setText("您选择了：" + i
                        + ":" + (i1 + 1) + ":"
                        + i2);
            }
        },calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setCancelable(true);
        datePickerDialog.show();

    }


}