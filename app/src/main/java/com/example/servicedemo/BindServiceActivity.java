package com.example.servicedemo;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class BindServiceActivity extends Activity implements Button.OnClickListener {
    private static final String TAG = "Strong";
    private BindService service = null;
    private boolean isBind = false;

    private ServiceConnection conn = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            isBind = true;
            BindService.MyBinder myBinder = (BindService.MyBinder) binder;
            service = myBinder.getService();
            Log.i(TAG, "BinActivity - onServiceConnected");
            int num = service.getRandomNumber();
            Log.i(TAG, "BinActivity - getRandomNumber = " + num);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBind = false;
            Log.i(TAG, "BinActivity - onServiceDisconnected");
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);
        Log.i(TAG, "BinActivity - onCreate - Thread = " + Thread.currentThread().getName());

        findViewById(R.id.bind).setOnClickListener(this);
        findViewById(R.id.unbind).setOnClickListener(this);
        findViewById(R.id.finish).setOnClickListener(this);
        findViewById(R.id.startActivty).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bind:
                //单击了“bindService”按钮
                Intent intent = new Intent(this, BindService.class);
                intent.putExtra("from", "BinActivity");
                Log.i(TAG, "----------------------------------------------------------------------");
                Log.i(TAG, "BinActivity 执行 bindService");
                bindService(intent, conn, BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                //单击了“unbindService”按钮
                if (isBind) {
                    Log.i(TAG,
                            "----------------------------------------------------------------------");
                    Log.i(TAG, "BinActivity 执行 unbindService");
                    unbindService(conn);
                }
                break;
            case R.id.finish:
                //单击了“Finish”按钮
                Log.i(TAG, "----------------------------------------------------------------------");
                Log.i(TAG, "BinActivity 执行 finish");
                this.finish();
                break;
            case R.id.startActivty:

                //单击了“start FinishActivity”按钮
                Intent intent2 = new Intent(this, FinishActivity.class);
                Log.i(TAG,
                        "----------------------------------------------------------------------");
                Log.i(TAG, "BinActivity 启动 FinishActivity");
                startActivity(intent2);
                break;

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "BinActivity - onDestroy");
    }
}
