package jp.gr.java_conf.mitchibu.lib.badgeview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

@SuppressWarnings("unused")
public class BadgeView extends FrameLayout {
	private final TextView badge;

	public BadgeView(Context context) {
		this(context, null);
	}

	@SuppressWarnings("deprecation")
	public BadgeView(Context context, AttributeSet attrs) {
		super(context, attrs);

		badge = new TextView(context);
		if(attrs == null) return;

		float textSize;
		ColorStateList color;
		Drawable drawable = null;
		int gravity = 0;
		TypedArray a = null;
		try {
			a = context.obtainStyledAttributes(attrs, R.styleable.BadgeView);
			textSize = a.getDimensionPixelSize(R.styleable.BadgeView_badge_textSize, 24);
			color = a.getColorStateList(R.styleable.BadgeView_badge_textColor);
			drawable = a.getDrawable(R.styleable.BadgeView_badge_background);
			int i = a.getInt(R.styleable.BadgeView_badge_gravity, 6);
			if((i & 1) != 0) gravity |= Gravity.START;
			if((i & 2) != 0) gravity |= Gravity.TOP;
			if((i & 4) != 0) gravity |= Gravity.END;
			if((i & 8) != 0) gravity |= Gravity.BOTTOM;
		} finally {
			if(a != null) a.recycle();
		}
		badge.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
		badge.setTextColor(color);

		if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
			badge.setBackgroundDrawable(drawable);
		} else {
			badge.setBackground(drawable);
		}
		addView(badge, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, gravity));
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		bringChildToFront(badge);
	}

	public void setText(CharSequence text) {
		badge.setText(text);
	}

	public void show() {
		badge.setVisibility(VISIBLE);
	}

	public void hide() {
		badge.setVisibility(GONE);
	}
}
