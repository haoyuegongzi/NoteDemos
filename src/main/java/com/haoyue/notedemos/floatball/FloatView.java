package com.haoyue.notedemos.floatball;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.haoyue.notedemos.R;

import java.util.ArrayList;

/**
 * 
 * @author lavon
 * @date 2016-9-26 09:17:49
 * @version 1.0.0
 * 
 */
public class FloatView extends AppCompatImageView{

//	private int mFloatingMode;
	private boolean mIsFloating;
	private boolean mFloatingLeft;
	private boolean mFloatingTop;
	private boolean mFloatingRight;
	private boolean mFloatingBottom;
	private Handler mHandler;

	// public static final int FLOATING_MODE_LEFT = 1;
	// public static final int FLOATING_MODE_TOP = 2;
	// public static final int FLOATING_MODE_RIGHT = 3;
	// public static final int FLOATING_MODE_BOTTOM = 4;
	// public static final int FLOATING_MODE_NORMAL = 0;

	private float mInitX;
	private float mInitY;

	private String TAG = "FloatView";

	private float mViewSetX;
	private float mViewSetY;

	private float mSDownX;
	private float mSDownY;

	private float mSMoveX;
	private float mSMoveY;

	private float mSUpX;
	private float mSUpY;

	private float mSDiffX;
	private float mSDiffY;

	private float mSWidth;
	private float mSHeight;

	private float mVWidth;
	private float mVHeight;
	private int statusBarHeight;

	private int mHBerthLength;

	public void setmHBerthLength(int mHBerthLength) {
		this.mHBerthLength = mHBerthLength;
	}

	public void setmVBerthLength(int mVBerthLength) {
		this.mVBerthLength = mVBerthLength;
	}

	private int mVBerthLength;

	private OnClickListener mClickListener;
	private WindowManager windowManager;
	private WindowManager.LayoutParams windowManagerParams;
	private Context mContext;
	private ArrayList<Float> mPoints;

	private float mMoveFactor = 20;
	private long mTimeFactor = 1;

	public FloatView(Context context, float x, float y) {
		super(context);
		mContext = context;

		Rect frame = new Rect();
		getWindowVisibleDisplayFrame(frame);
		statusBarHeight = frame.top - 48;
		windowManager = (WindowManager) getContext().getApplicationContext()
				.getSystemService(Context.WINDOW_SERVICE);
		windowManagerParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.TYPE_TOAST);
		windowManagerParams.format = PixelFormat.RGBA_8888;
		windowManagerParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
				| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		windowManagerParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
		windowManagerParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		mSWidth = windowManager.getDefaultDisplay().getWidth();
		mSHeight = windowManager.getDefaultDisplay().getHeight();
		mHBerthLength = (int) (mSWidth/2);
		mVBerthLength = 500;
		windowManagerParams.gravity = Gravity.LEFT | Gravity.TOP;
		mInitX = mSWidth;
		mInitY = mSHeight/10*7;
		windowManagerParams.x = (int) mInitX;
		windowManagerParams.y = (int) mInitY;
		mHandler = new Handler();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			setImageResource(R.mipmap.microphone);
			mVWidth = getWidth();
			mVHeight = getHeight();
			final int[] location = new int[2];
			getLocationOnScreen(location);

			mSDownX = event.getRawX();
			mSDownY = event.getRawY();

			break;

		case MotionEvent.ACTION_MOVE:

			mSMoveX = event.getRawX();
			mSMoveY = event.getRawY();

			mSDiffX = (int) Math.abs(mSDownX - mSMoveX);
			mSDiffY = (int) Math.abs(mSDownY - mSMoveY);

			if (!(mSDiffX < 10 || mSDiffY < 10)) {
				mViewSetX = mSMoveX - mVWidth / 2;
				mViewSetY = mSMoveY - mVHeight / 2;
				updateViewPosition(mViewSetX, mViewSetY);
			}
			break;

		case MotionEvent.ACTION_UP:
			setImageResource(R.mipmap.microphone);

			mSUpX = event.getRawX();
			mSUpY = event.getRawY();
			mSDiffX = (int) Math.abs(mSDownX - mSUpX);
			mSDiffY = (int) Math.abs(mSDownY - mSUpY);
			if (mSDiffX < 10 && mSDiffY < 10) {
				if (mClickListener != null) {
					mClickListener.onClick(this);
				}
			} else {
				mViewSetX = mSMoveX - mVWidth / 2;
				mViewSetY = mSMoveY - mVHeight / 2;
				setViewPosition();
			}
			mSMoveX = 0;
			mSMoveY = 0;
			break;

		default:
			break;
		}
		return true;
	}

	private void setViewPosition() {
		if (!mIsFloating) {
			updateViewPosition(mViewSetX, mViewSetY);
		} else {
			analyzeForDistance();
		}

	}

	private void analyzeForDistance() {
		boolean isFloating = false;
		int upX = (int) mViewSetX;
		int upY = (int) mViewSetY;
		// top
		if (mViewSetY < mVBerthLength) {
			if (mFloatingTop) {
				mViewSetY = 0;
				isFloating = true;
			}
		}

		// bottom
		if (mViewSetY > (mSHeight - mVBerthLength)) {
			if (mFloatingBottom) {
				mViewSetY = mSHeight;
				isFloating = true;
			}
		}

		// left
		if (!isFloating) {
			if (mViewSetX < mHBerthLength) {
				if (mFloatingLeft) {
					mViewSetX = 0;
				}
			}
			if (mViewSetX > mSWidth - mHBerthLength) {
				if (mFloatingRight) {
					mViewSetX = mSWidth;
				}
			}
		}
		ArrayList<Float> buildInterpolatorPoint = buildInterpolatorPoint(upX,
				upY, mViewSetX, mViewSetY, mMoveFactor);
		updateViewPosition(buildInterpolatorPoint);
	}

	private ArrayList<Float> buildInterpolatorPoint(int starX, int starY,
			float endX, float endY, float factor) {
		ArrayList<Float> points = new ArrayList<Float>();

		float diffX = endX - starX;
		float oneLengthX = diffX / factor;
		float diffY = endY - starY;
		float oneLengthY = diffY / factor;
		float newX = starX;
		float newY = starY;

		for (int i = 0; i < factor; i++) {
			newX = newX + oneLengthX;
			newY = newY + oneLengthY;
			points.add(newX);
			points.add(newY);
		}
		return points;
	}

	@Override
	public void setOnClickListener(OnClickListener l) {
		this.mClickListener = l;
	}

	private void updateViewPosition(float x, float y) {
//		windowManagerParams.x = (int) (/* lastX - */x);
//		windowManagerParams.y = (int) (/* lastY - */y);
		windowManagerParams.x = (int) (x);
		windowManagerParams.y = (int) (y);
		windowManager.updateViewLayout(this, windowManagerParams);
	}

	private void updateViewPosition(
			final ArrayList<Float> buildInterpolatorPoint) {
		mPoints = buildInterpolatorPoint;
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
//				if (!mIsStopMove) {
					setWindowParams(mPoints);
//				}
			}
		}, mTimeFactor);
	}


	private void setWindowParams(ArrayList<Float> buildInterpolatorPoint) {
		Log.e(TAG, "setWindowParams");
		if (buildInterpolatorPoint != null
				&& buildInterpolatorPoint.size() >= 2) {
			float x = buildInterpolatorPoint.remove(0);
			float y = buildInterpolatorPoint.remove(0);

//			windowManagerParams.x = (int) (/* lastX - */x);
//			windowManagerParams.y = (int) (/* lastY - */y);
			windowManagerParams.x = (int) (x);
			windowManagerParams.y = (int) (y);
			windowManager.updateViewLayout(FloatView.this, windowManagerParams);
			updateViewPosition(mPoints);
		}
	}

	public WindowManager.LayoutParams getWindowManagerParams() {
		return windowManagerParams;
	}

	/**
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 */
	public void setFloatingMode(boolean left, boolean top, boolean right,
			boolean bottom) {
		mIsFloating = true;
		mFloatingLeft = left;
		mFloatingTop = top;
		mFloatingRight = right;
		mFloatingBottom = bottom;
	}
}
