package qingfengmy.cascadelayout;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class CascadeLayout extends ViewGroup {

	private int mHorizontalSpacing;
	private int mVerticalSpacing;

	public CascadeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context, attrs);
	}

	private void initView(Context context, AttributeSet attrs) {
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.CascadeLayout);
		try {
			mHorizontalSpacing = a.getDimensionPixelSize(
					R.styleable.CascadeLayout_horizontal_spacing,
					getResources().getDimensionPixelSize(
							R.dimen.activity_horizontal_margin));

			mVerticalSpacing = a.getDimensionPixelSize(
					R.styleable.CascadeLayout_vertical_spacing,
					getResources().getDimensionPixelSize(
							R.dimen.activity_vertical_margin));
		} catch (NotFoundException e) {
			e.printStackTrace();
		} finally {
			a.recycle();
		}
	}

	// ����view�Ŀ��
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = 0;
		int height = getPaddingTop();
		int verticalSpacing;
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			verticalSpacing = mVerticalSpacing;
			View child = getChildAt(i);
			// ��ÿ����view��������
			measureChild(child, widthMeasureSpec, heightMeasureSpec);

			LayoutParams lp = (LayoutParams) child.getLayoutParams();
			width = getPaddingLeft() + mHorizontalSpacing * i;
			// layoutParams�б���ÿ������ͼ���Ͻǵ�x��y����
			lp.x = width;
			lp.y = height;

			if (lp.verticalSpacing >= 0) {
				verticalSpacing = lp.verticalSpacing;
			}

			width += child.getMeasuredWidth();
			height += verticalSpacing;
		}

		// ʹ�ü������õĿ�������������ֵĲ����ߴ�
		width += getPaddingRight();
		height += getChildAt(getChildCount() - 1).getMeasuredHeight()
				+ getPaddingBottom();
		// resolveSize����Ҫ���þ��Ǹ������ṩ�Ĵ�С��MeasureSpec����������Ҫ�Ĵ�Сֵ�����������ݴ���ģʽ�Ĳ�ͬ������Ӧ�Ĵ���
		setMeasuredDimension(resolveSize(width, widthMeasureSpec),
				resolveSize(height, heightMeasureSpec));

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			View child = getChildAt(i);
			LayoutParams lp = (LayoutParams) child.getLayoutParams();
			child.layout(lp.x, lp.y, lp.x + child.getMeasuredWidth(), lp.y
					+ child.getMeasuredHeight());
		}
	}

	@Override
	protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
		return p instanceof LayoutParams;
	}

	@Override
	protected LayoutParams generateDefaultLayoutParams() {
		return new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new LayoutParams(getContext(), attrs);
	}

	@Override
	protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
		return new LayoutParams(p.width, p.height);
	}

	public static class LayoutParams extends ViewGroup.LayoutParams {
		int x;
		int y;
		int verticalSpacing;

		public LayoutParams(Context context, AttributeSet attrs) {
			super(context, attrs);
			TypedArray a = context.obtainStyledAttributes(attrs,
					R.styleable.CascadeLayout_LayoutParams);
			try {
				verticalSpacing = a
						.getDimensionPixelSize(
								R.styleable.CascadeLayout_LayoutParams_layout_vertical_spacing,
								-1);
			} finally {
				a.recycle();
			}
		}

		public LayoutParams(int w, int h) {
			super(w, h);
		}

	}

}
