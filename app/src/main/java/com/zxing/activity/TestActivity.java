package com.zxing.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.zxing.decoding.EncodingHandler;

import cn.com.sgcc.dev.R;

//import com.zxing.encoding.EncodingHandler;

/**
 * <p>@description:</p>
 *   扫码测试界面
 * @author lxf
 * @version 1.0.0
 * @Email
 * @since 2017/8/31
 */
public class TestActivity extends Activity {
	
	private Button scanButton;
	private TextView text;
	private EditText input;
	private Button genButton;
	private ImageView img;
	InputMyReceiver inputMyReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		
		scanButton = (Button) findViewById(R.id.scan);
		text = (TextView) findViewById(R.id.text);
		
		//扫描二维码
		scanButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//Toast.makeText(TestActivity.this, "你可以扫描条形码或者二维码", Toast.LENGTH_SHORT).show();
				Intent  startScan = new Intent(TestActivity.this, CaptureActivity.class);
				startActivityForResult(startScan, 0);		
			}
		});
		
		input = (EditText) findViewById(R.id.input);
		genButton = (Button) findViewById(R.id.gen);
		img = (ImageView) findViewById(R.id.img);
		
		//生成二维码
		genButton.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				String in = input.getText().toString();
				if(in.equals("")){
					Toast.makeText(TestActivity.this, "请输入文本", Toast.LENGTH_SHORT).show();
				}else {
					try {						
						Bitmap qrcode = EncodingHandler.createQRCode(in, 400);
						img.setImageBitmap(qrcode);
					} catch (WriterException e) {
						e.printStackTrace();
					}
				}
			}
		});

		IntentFilter filter = new IntentFilter();
		filter.addAction("com.CaptureActivity.input");
		inputMyReceiver= new InputMyReceiver();
		registerReceiver(inputMyReceiver,filter);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode==0) {
			//扫码获取的识别码
			String result = data.getExtras().getString("result");
			text.setText(result);
		}
	}

	public class InputMyReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			//用户填写的识别码
            String inputs=intent.getExtras().getString("input");
			Toast.makeText(getApplicationContext(), "用户填写的识别码: "+inputs, Toast.LENGTH_SHORT).show();
            text.setText(inputs);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(inputMyReceiver);
	}
}
