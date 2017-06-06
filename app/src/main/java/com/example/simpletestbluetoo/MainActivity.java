package com.example.simpletestbluetoo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.graphics.Color;
import android.os.Message;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int RECV_VIEW = 0;
    public static final int NOTICE_VIEW = 1;

    Context mContext = this;
    Activity mActivity = this;
    private BlueToothPopwindow mBlueToothPopwindow = null;

    Button mButtonSend;
    Button mButtonBlueTooth;
    TextView mTextViewState;
    EditText mEditTextSend;
    Button mButton01;
    Button mButton02;
    Button mButton03;
    Button mButton04;
    Button mButton05;
    Button mButton06;
    Button mButton07;
    Button mButton08;
    Button mButton09;
    Button mButton10;
    Button mButton11;
    Button mButton12;
    Button mButton13;
    Button mButton14;
    Button mButton15;
    Button mButton16;
    TextView mTextViewReceive;
    ScrollView mScrollView;
    Button mButtonClear;

    private BluetoothAdapter mBluetoothAdapter = null;
    private ConnectThread mConnectThread = null;
    private ConnectedThread mConnectedThread = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonSend = (Button)findViewById(R.id.button_send);
        mButtonSend.setOnClickListener(this);
        mButtonClear = (Button)findViewById(R.id.button_clear);
        mButtonClear.setOnClickListener(this);
        mButtonBlueTooth = (Button)findViewById(R.id.button_bluetooth);
        mButtonBlueTooth.setOnClickListener(this);
        mTextViewState = (TextView)findViewById(R.id.textview_state);
        mTextViewReceive = (TextView)findViewById(R.id.textview_receive);
        mEditTextSend = (EditText)findViewById(R.id.edittext_send);
        mScrollView = (ScrollView)findViewById(R.id.scrollview);
        mButton01 = (Button)findViewById(R.id.button_01);
        mButton02 = (Button)findViewById(R.id.button_02);
        mButton03 = (Button)findViewById(R.id.button_03);
        mButton04 = (Button)findViewById(R.id.button_04);
        mButton05 = (Button)findViewById(R.id.button_05);
        mButton06 = (Button)findViewById(R.id.button_06);
        mButton07 = (Button)findViewById(R.id.button_07);
        mButton08 = (Button)findViewById(R.id.button_08);
        mButton09 = (Button)findViewById(R.id.button_09);

        mButton10 = (Button)findViewById(R.id.button_10);
        mButton11 = (Button)findViewById(R.id.button_11);
        mButton12 = (Button)findViewById(R.id.button_12);
        mButton13 = (Button)findViewById(R.id.button_13);
        mButton14 = (Button)findViewById(R.id.button_14);
        mButton15 = (Button)findViewById(R.id.button_15);
        mButton16 = (Button)findViewById(R.id.button_16);
        mButton01.setOnClickListener(this);
        mButton02.setOnClickListener(this);
        mButton03.setOnClickListener(this);
        mButton04.setOnClickListener(this);
        mButton05.setOnClickListener(this);
        mButton06.setOnClickListener(this);
        mButton07.setOnClickListener(this);
        mButton08.setOnClickListener(this);
        mButton09.setOnClickListener(this);
        mButton10.setOnClickListener(this);
        mButton11.setOnClickListener(this);
        mButton12.setOnClickListener(this);
        mButton13.setOnClickListener(this);
        mButton14.setOnClickListener(this);
        mButton15.setOnClickListener(this);
        mButton16.setOnClickListener(this);



        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            return;
        }

        if (!mBluetoothAdapter.isEnabled()) {
            mTextViewState.setText("蓝牙未开启");
        }
        else {
            mTextViewState.setText("蓝牙已开启");
        }
    }

    void sendMessage(String chr) {

                /* send message*/
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Toast.makeText(this, "蓝牙未开启", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mConnectedThread == null) {
            Toast.makeText(this, "未连接设备", Toast.LENGTH_SHORT).show();
            return;
        }
        mConnectedThread.write(chr.getBytes());
        //Toast.makeText(this, "send" + chr + "<<end", Toast.LENGTH_SHORT).show();
        return;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_clear: {
                mTextViewReceive.setText("");
                break;
            }
            case R.id.button_send: {
                /* send message*/
                if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
                    Toast.makeText(this, "蓝牙未开启", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mConnectedThread == null) {
                    Toast.makeText(this, "未连接设备", Toast.LENGTH_SHORT).show();
                    break;
                }

                mConnectedThread.write(mEditTextSend.getText().toString().getBytes());
                Toast.makeText(this, "send " + mEditTextSend.getText().toString() + "end", Toast.LENGTH_SHORT).show();
                mEditTextSend.setText("");
                break;
            }
            case R.id.button_bluetooth: {
                if (mBlueToothPopwindow != null && mBlueToothPopwindow.isShowing()) {
                    mBlueToothPopwindow.dismiss();
                } else {
                    mBlueToothPopwindow = new BlueToothPopwindow(mActivity, itemsOnClick);
                    mBlueToothPopwindow.showAsDropDown(view, 0, 5);
                }
                break;
            }
            case R.id.button_01: { sendMessage("1"); break; }
            case R.id.button_02: { sendMessage("2"); break; }
            case R.id.button_03: { sendMessage("3"); break; }
            case R.id.button_04: { sendMessage("4"); break; }
            case R.id.button_05: { sendMessage("5"); break; }
            case R.id.button_06: { sendMessage("6"); break; }
            case R.id.button_07: { sendMessage("7"); break; }
            case R.id.button_08: { sendMessage("8"); break; }
            case R.id.button_09: { sendMessage("9"); break; }
            case R.id.button_10: { sendMessage("a"); break; }
            case R.id.button_11: { sendMessage("b"); break; }
            case R.id.button_12: { sendMessage("c"); break; }
            case R.id.button_13: { sendMessage("d"); break; }
            case R.id.button_14: { sendMessage("e"); break; }
            case R.id.button_15: { sendMessage("f"); break; }
            case R.id.button_16: { sendMessage("g"); break; }
        }
    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick (View v) {
            switch (v.getId()) {
                case R.id.button_showdevices:
//                    Toast.makeText(mContext, "show", Toast.LENGTH_SHORT).show();
                    if (mBluetoothAdapter != null) {
                        if (!mBluetoothAdapter.isEnabled()) {
                            Toast.makeText(mContext, "蓝牙未开启", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        // 查询配对设备
                        List<String> devices = new ArrayList<String>();
                        Set<BluetoothDevice> bondedDevices = mBluetoothAdapter.getBondedDevices();
                        for (BluetoothDevice device : bondedDevices) {
                            devices.add(device.getName() + "-" + device.getAddress());
                        }
                        StringBuilder text = new StringBuilder();
                        for (String device : devices) {
                            text.append(device + "\n");
                        }
                        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.button_startbluetooth:
//                    Toast.makeText(mContext, "start", Toast.LENGTH_SHORT).show();
                    if (mBluetoothAdapter != null) {
                        //开启蓝牙
                        int REQUEST_ENABLE_BT = 1;
                        if (!mBluetoothAdapter.isEnabled()) {
                            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                            startActivityForResult(intent, REQUEST_ENABLE_BT);
                            mTextViewState.setText("开启蓝牙成功");
                        } else {
                            Toast.makeText(mContext, "蓝牙已开启", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                case R.id.button_connectdevices:
//                    Toast.makeText(mContext, "connect", Toast.LENGTH_SHORT).show();
                    if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
                        Toast.makeText(mContext, "蓝牙未开启", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    // 查询配对设备 建立连接，只能连接第一个配对的设备
                    List<String> devices = new ArrayList<String>();
                    Set<BluetoothDevice> bondedDevices = mBluetoothAdapter.getBondedDevices();
                    for (BluetoothDevice device : bondedDevices) {
                        mConnectThread = new ConnectThread(device);
                        mConnectThread.start();
                        break;
                    }
                    break;
            }
        }
    };

    private class ConnectThread extends Thread {
        private final String MY_UUID = "00001101-0000-1000-8000-00805F9B34FB";
        private final BluetoothSocket socket;
        private final BluetoothDevice device;
        public ConnectThread(BluetoothDevice device) {
            this.device = device;
            BluetoothSocket tmp = null;
            try {
                tmp = device.createRfcommSocketToServiceRecord(UUID.fromString(MY_UUID));
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.socket = tmp;
        }
        public void run() {
            mBluetoothAdapter.cancelDiscovery();
            try {
                socket.connect();
                mConnectedThread = new ConnectedThread(socket);
                mConnectedThread.start();
            } catch (IOException e) {
                try {
                    socket.close();
                } catch (IOException ee) {
                    ee.printStackTrace();
                }
                return;
            }
//manageConnectedSocket(socket);
        }
        public void cancel() {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // 客户端与服务器建立连接成功后，用ConnectedThread收发数据
    private class ConnectedThread extends Thread {
        private final BluetoothSocket socket;
        private final InputStream inputStream;
        private final OutputStream outputStream;
        public ConnectedThread(BluetoothSocket socket) {
            this.socket = socket;
            InputStream input = null;
            OutputStream output = null;
            try {
                input = socket.getInputStream();
                output = socket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.inputStream = input;
            this.outputStream = output;
        }
        public void run() {
            StringBuilder recvText = new StringBuilder();
            byte[] buff = new byte[1024];
            int bytes;
            Bundle tmpBundle = new Bundle();
            Message tmpMessage = new Message();
            tmpBundle.putString("notice", "连接成功");
            tmpMessage.what = NOTICE_VIEW;
            tmpMessage.setData(tmpBundle);
            handler.sendMessage(tmpMessage);
            while (true) {
                try {
                    bytes = inputStream.read(buff);
                    String str = new String(buff, "ISO-8859-1");
                    str = str.substring(0, bytes);
// 收到数据，单片机发送上来的数据以"#"结束，这样手机知道一条数据发送结束
//Log.e("read", str);
                    if (!str.endsWith("#")) {
                        recvText.append(str);
                        continue;
                    }
                    recvText.append(str.substring(0, str.length() - 1)); // 去除'#'
                    Bundle bundle = new Bundle();
                    Message message = new Message();
                    bundle.putString("recv", recvText.toString());
                    message.what = RECV_VIEW;
                    message.setData(bundle);
                    handler.sendMessage(message);
                    recvText.replace(0, recvText.length(), "");
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
        public void write(byte[] bytes) {
            try {
                outputStream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void cancel() {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private android.os.Handler handler = new android.os.Handler() {
        public void handleMessage(Message msg) {
            Bundle bundle = null;
            switch (msg.what) {
                case RECV_VIEW:

                    bundle = msg.getData();
                    String recv = bundle.getString("recv");
                    mTextViewReceive.append(recv + "\n");
                    mScrollView.fullScroll(ScrollView.FOCUS_DOWN); // 滚动到底部
//                    if (recv.isEmpty() || recv.contains(" ") || recv.contains("#")) {
//                        break;
//                    }

                    break;
                case NOTICE_VIEW:
                    bundle = msg.getData();
                    String notice = bundle.getString("notice");
                    mTextViewState.setText(notice);
                    break;
                default:
                    break;
            }
        }
    };

}
