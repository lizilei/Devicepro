/*
 * Copyright (C) 2010 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zxing.decoding;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zxing.activity.CaptureActivity;

import cn.com.sgcc.dev.R;
import cn.com.sgcc.dev.customeView.CustomDialog;
import cn.com.sgcc.dev.utils.ToastUtils;

import static android.app.Activity.RESULT_OK;

/**
 * Simple listener used to exit the app in a few cases.
 * lxf
 */
public final class FinishListener
    implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener, Runnable {
  private CustomDialog dialog;

  private final Activity activityToFinish;

  public FinishListener(Activity activityToFinish) {
    this.activityToFinish = activityToFinish;
  }

  @Override
  public void onCancel(DialogInterface dialogInterface) {
    run();
  }

  @Override
  public void onClick(DialogInterface dialogInterface, int i) {
    run();
  }

  @Override
  public void run() {
    //activityToFinish.finish();  //超时后直接关闭扫一扫界面
    ToastUtils.showLongToast(activityToFinish,"扫码超时,您可以点击  手动输入识别码 ");
  }
}
