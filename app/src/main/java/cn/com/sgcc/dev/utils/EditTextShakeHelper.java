package cn.com.sgcc.dev.utils;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

/**
 * <p>@description:</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/6
 */

public class EditTextShakeHelper {
	// 震动动画
	private Animation shakeAnimation;
	// 插值器
	private CycleInterpolator cycleInterpolator;
	// 振动器
	private Vibrator shakeVibrator;

	public EditTextShakeHelper(Context context) {
		// 初始化振动器
		shakeVibrator = (Vibrator) context
				.getSystemService(Service.VIBRATOR_SERVICE);
		// 初始化震动动画
		shakeAnimation = new TranslateAnimation(0, 10, 0, 0);
		shakeAnimation.setDuration(200);
		cycleInterpolator = new CycleInterpolator(8);
		shakeAnimation.setInterpolator(cycleInterpolator);
	}

	/**
	 * 开始震动
	 * 
	 * @param editTexts
	 */
	public void shake(EditText... editTexts) {
		for (EditText editText : editTexts) {
			editText.startAnimation(shakeAnimation);
		}
		shakeVibrator.vibrate(new long[] { 0, 100 }, -1);
	}
}
