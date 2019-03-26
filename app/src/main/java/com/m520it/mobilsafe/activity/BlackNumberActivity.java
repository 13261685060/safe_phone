package com.m520it.mobilsafe.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.m520it.mobilsafe.R;
import com.m520it.mobilsafe.bean.BlackNumberInfo;
import com.m520it.mobilsafe.db.dao.BlackNumberDao;

import java.util.List;

/**
 * @author 王维波
 * @time 2016/9/10  15:03
 * @desc ${TODD}
 */
public class BlackNumberActivity extends Activity {
    private ListView lv_black;
    private BlackNumberDao dao;
    private List<BlackNumberInfo> all;
    private BlackNumberAdapter adapter;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            adapter = new BlackNumberAdapter();
            lv_black.setAdapter(adapter);
        }
    };
    private EditText et_phone;
    private AlertDialog dialog;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        new Thread() {
            public void run() {
                all = dao.findAll();
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    private void initView() {
        dao = new BlackNumberDao(getApplicationContext());
        setContentView(R.layout.activity_blacknumber);
        lv_black = (ListView) findViewById(R.id.lv_black);
    }

    public void add(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BlackNumberActivity.this);
        View view = View.inflate(getApplicationContext(), R.layout.item_black_add_view, null);

        final EditText et_phone = (EditText) view.findViewById(R.id.et_phone);
        Button btn_cancel = (Button) view.findViewById(R.id.bt_cancel);
        Button btn_ok = (Button) view.findViewById(R.id.bt_ok);
        final RadioGroup radiog = (RadioGroup) view.findViewById(R.id.rg);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = et_phone.getText().toString().trim();
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(BlackNumberActivity.this, "请输入黑名单号码", Toast.LENGTH_SHORT).show();
                } else {
                    String mode=0+"";
                    int id = radiog.getCheckedRadioButtonId();
                    switch (id) {

                        case R.id.rb_all:
                            mode="0";
                            break;
                        case R.id.rb_phone:
                            mode="1";
                            break;
                        case R.id.rb_sms:
                            mode="2";
                            break;
                    }
                    boolean result = dao.add(phone, mode);
                    if(result) {
                        BlackNumberInfo  info=new BlackNumberInfo();
                        info.setMode(mode);
                        info.setPhone(phone);
                        all.add(0,info);
                        adapter.notifyDataSetChanged();

                    }else {
                        Toast.makeText(BlackNumberActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }
            }
        });
        dialog= builder.create();
        dialog.setView(view,0,0,0,0);
        dialog.show();


    }

    class BlackNumberAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return all.size();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup viewGroup) {
            View view = null;
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                view = View.inflate(getApplicationContext(), R.layout.item_blacknumber_view, null);
                holder.tv_phone = (TextView) view.findViewById(R.id.tv_phone);
                holder.tv_mode = (TextView) view.findViewById(R.id.tv_mode);
                holder.iv_delete= (ImageView) view.findViewById(R.id.iv_item_delete);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
            final BlackNumberInfo info = all.get(position);
            String mode = info.getMode();
            holder.tv_phone.setText(info.getPhone());
            switch (Integer.parseInt(mode)) {
                case 0:
                    holder.tv_mode.setText("全部拦截");
                    break;
                case 1:
                    holder.tv_mode.setText("电话拦截");
                    break;
                case 2:
                    holder.tv_mode.setText("短信拦截");
                    break;
            }

            holder.iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder=new AlertDialog.Builder(BlackNumberActivity.this);
                    builder.setTitle("删除");
                    builder.setMessage("您确认删除吗?");
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            boolean result = dao.delete(info.getPhone());
                            if(result) {
                                all.remove(position);
                                adapter.notifyDataSetChanged();
                            }else {
                                Toast.makeText(BlackNumberActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                            }
                            alertDialog.dismiss();
                        }
                    });


                    alertDialog = builder.show();
                }
            });

            return view;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        class ViewHolder {
            TextView tv_phone;
            TextView tv_mode;
            ImageView iv_delete;
        }
    }
}
