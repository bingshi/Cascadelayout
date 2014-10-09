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
		// ����ֵ������ʾ���ж����Ƿ�ʹ��ͬһ��interpolator���岹����
		AnimationSet set = new AnimationSet(false);

		AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
		alphaAnimation.setDuration(1000);
		set.addAnimation(alphaAnimation);
		
		// ������������ʼx���ͣ���ʵxλ�ã��յ�x���ͣ��յ�xλ�ã���ʼy���ͣ���ʼyλ�ã��յ�y���ͣ��յ�yλ�á�
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
