package qingfengmy.cascadelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;

public class MainActivity extends Activity {

	CascadeLayout layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		layout = (CascadeLayout) findViewById(R.id.cascade);
		// 布尔值参数表示所有动画是否使用同一个interpolator（插补器）
		AnimationSet set = new AnimationSet(false);

		AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation.setDuration(1000);
		set.addAnimation(alphaAnimation);
		
		// 参数依次是起始x类型，其实x位置；终点x类型，终点x位置；起始y类型，起始y位置；终点y类型，终点y位置。
		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF,
				0.0f, Animation.RELATIVE_TO_SELF, -1.0f,
				Animation.RELATIVE_TO_SELF, 0.0f);
		translateAnimation.setDuration(1000);
		translateAnimation.setInterpolator(new OvershootInterpolator());
		set.addAnimation(translateAnimation);

		LayoutAnimationController controller = new LayoutAnimationController(
				set, 0.5f);
		layout.setLayoutAnimation(controller);
	}

}
