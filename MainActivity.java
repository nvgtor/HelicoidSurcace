package app.nvgtor.com.helicoidsurcace;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import app.nvgtor.com.helicoidsurcace.Cylinder.*;

public class MainActivity extends AppCompatActivity {

    CylinderSurfaceView mCylinderSurfaceView;
    //	SpringSurfaceView  mSpringSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置为全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //设置为竖屏模式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //切换到主界面
        setContentView(R.layout.playershow);

        mCylinderSurfaceView = (CylinderSurfaceView) findViewById(R.id.CylinderView);
        mCylinderSurfaceView.requestFocus();// 获取焦点
        mCylinderSurfaceView.setFocusableInTouchMode(true);// 设置为可触控
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCylinderSurfaceView.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

        mCylinderSurfaceView.onPause();
    }


    public boolean onKeyDown(int keyCode,KeyEvent e)
    {
        switch(keyCode)
        {
            case 4:
                System.exit(0);
                break;
        }
        return true;
    };
}
