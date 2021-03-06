package app.nvgtor.com.helicoidsurcace.Cylinder;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import app.nvgtor.com.helicoidsurcace.Utils.Constant;
import app.nvgtor.com.helicoidsurcace.Utils.MatrixState;

import static app.nvgtor.com.helicoidsurcace.Utils.Constant.initTexture;


public class CylinderSurfaceView extends GLSurfaceView {
	float xAngle=0;//绕x轴旋转的角度
	float yAngle=0;//绕y轴旋转的角度
	float zAngle=0;//绕z轴旋转的角度
	float h;
	float scale;

	private float mPreviousY;// 上次的触控位置Y坐标
	private float mPreviousX;// 上次的触控位置X坐标

	private CylinderRenderer mRenderer;// 场景渲染器
	public CylinderSurfaceView(Context context,AttributeSet attributeSet) {
		super(context,attributeSet);
		this.setEGLContextClientVersion(2); // 设置使用OPENGL ES2.0
		mRenderer = new CylinderRenderer(); // 创建场景渲染器
		setRenderer(mRenderer); // 设置渲染器
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);// 设置渲染模式为主动渲染
	}

	// 触摸事件回调方法
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		float y = e.getY();
		float x = e.getX();
		switch (e.getAction()) {
			case MotionEvent.ACTION_MOVE:
				float dy = y - mPreviousY;// 计算触控笔Y位移
				float dx = x - mPreviousX;// 计算触控笔X位移
				mRenderer.cylinder.yAngle += dx * Constant. TOUCH_SCALE_FACTOR;// 设置绕y轴旋转角度
				mRenderer.cylinder.zAngle += dy * Constant.TOUCH_SCALE_FACTOR;// 设置绕z轴旋转角度
		}
		mPreviousY = y;// 记录触控笔位置
		mPreviousX = x;// 记录触控笔位置
		return true;
	}

	private class CylinderRenderer implements GLSurfaceView.Renderer {

		CylinderSide cylinder;

		public void onDrawFrame(GL10 gl) {
			// 清除深度缓冲与颜色缓冲
			GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT
					| GLES20.GL_COLOR_BUFFER_BIT);

			// 保护现场
			MatrixState.pushMatrix();
			MatrixState.translate(0, 0, -10);
			cylinder.drawSelf();
			MatrixState.popMatrix();

		}

		public void onSurfaceChanged(GL10 gl, int width, int height) {
			// 设置视窗大小及位置
			GLES20.glViewport(0, 0, width, height);
			// 计算GLSurfaceView的宽高比
			float ratio = (float) width / height;
			// 调用此方法计算产生透视投影矩阵
			MatrixState.setProjectFrustum(-ratio, ratio, -1, 1, 4f, 100);
			// 调用此方法产生摄像机9参数位置矩阵
			MatrixState.setCamera(0, 0, 8.0f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

		}

		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			// 设置屏幕背景色RGBA
			GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
			// 启用深度测试
			GLES20.glEnable(GLES20.GL_DEPTH_TEST);
			// 设置为打开背面剪裁
			// GLES20.glEnable(GLES20.GL_CULL_FACE);
			// 初始化变换矩阵
			MatrixState.setInitStack();
			// 加载纹理
			for (int i = 0; i < Constant.pictures_Number; i++) {
				initTexture(CylinderSurfaceView.this.getResources(), i,
						Constant.pictures[i], Constant.pictures_Number, false);
			}
			// 创建圆柱对象
			cylinder = new CylinderSide(CylinderSurfaceView.this, 1, 1.8f, 2.0f, 6);

		}
	}
}


