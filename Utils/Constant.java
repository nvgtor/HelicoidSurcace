package app.nvgtor.com.helicoidsurcace.Utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;
import app.nvgtor.com.helicoidsurcace.R;

/**
 * Created by nvgtor on 2016/3/27.
 */

public class Constant
{
    public static final float TOUCH_SCALE_FACTOR = 180.0f/320;//角度缩放比例
    public static int picture_Number = 30;
    public static int pictures_Number=6;
    public static int pictures[] = { R.drawable.apple_green,
            R.drawable.apple_red, R.drawable.goosebeery, R.drawable.lemon,
            R.drawable.pimiento, R.drawable.image06, R.drawable.image07,
            R.drawable.image08, R.drawable.image09, R.drawable.image10,
            R.drawable.image11, R.drawable.image12, R.drawable.image13,
            R.drawable.image14, R.drawable.image15, R.drawable.image16,
            R.drawable.image17, R.drawable.image18, R.drawable.image19,
            R.drawable.image20, R.drawable.image21, R.drawable.image22,
            R.drawable.image23, R.drawable.image24, R.drawable.image25,
            R.drawable.image26, R.drawable.image27, R.drawable.image28,
            R.drawable.image29, R.drawable.image30 };

    public  static void initTexture(Resources r, int no, int drawableId, int length,boolean isMipmap) {
        int[] textures = new int[1];
        for (int i = 1; i <= length; i++) {
            if (i == no) {
                GLES20.glGenTextures(1, textures, 0);
                pictures[i - 1] = textures[0];
                GLES20.glBindTexture(GL10.GL_TEXTURE_2D, Constant.pictures[i - 1]);
            }
        }
        if(isMipmap)
        {//Mipmap纹理采样过滤参数
            GLES20.glTexParameteri ( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR_MIPMAP_LINEAR);
            GLES20.glTexParameteri ( GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR_MIPMAP_NEAREST);
        }
        else
        {//非Mipmap纹理采样过滤参数
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER,GLES20.GL_NEAREST);
            GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_MAG_FILTER,GLES20.GL_LINEAR);
        }
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S,GLES20.GL_REPEAT);
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T,GLES20.GL_REPEAT);

        // 通过输入流加载图片===============begin===================
        InputStream is = r.openRawResource(drawableId);
        Bitmap bitmapTmp;
        try {
            bitmapTmp = BitmapFactory.decodeStream(is);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 通过输入流加载图片===============end=====================
        GLUtils.texImage2D
                (
                        GLES20.GL_TEXTURE_2D, //纹理类型
                        0,   // 纹理的层次，0表示基本图像层，可以理解为直接贴图
                        GLUtils.getInternalFormat(bitmapTmp),
                        bitmapTmp, //纹理图像
                        GLUtils.getType(bitmapTmp),
                        0 //纹理边框尺寸
                );
        bitmapTmp.recycle(); // 纹理加载成功后释放图片
        //自动生成Mipmap纹理
//    GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
    }
}

