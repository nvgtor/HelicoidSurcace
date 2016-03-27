package app.nvgtor.com.helicoidsurcace.Utils;

import android.content.res.Resources;
/*
 * 该shader管理器主要是用于加载shader和编译shader
 */
public class ShaderManager
{
	final static String[][] shaderName=
			{
					{"vertex_spring.sh","frag_spring.sh"},//螺旋界面
					{"vertex_tex_cylinder.sh","frag_tex_cylinder.sh"},//六面棱柱
			};
	static String[]mVertexShader=new String[shaderName.length];//顶点着色器字符串数组
	static String[]mFragmentShader=new String[shaderName.length];//片元着色器字符串数组
	static int[] program=new int[shaderName.length];//程序数组
	//加载loading 界面的shader
	public static void loadFirstViewCodeFromFile(Resources r)
	{
		mVertexShader[0]=ShaderUtil.loadFromAssetsFile(shaderName[0][0],r);
		mFragmentShader[0]=ShaderUtil.loadFromAssetsFile(shaderName[0][1], r);
	}
	//加载shader字符串
	public static void loadCodeFromFile(Resources r)
	{
		for(int i=1;i<shaderName.length;i++)
		{
			//加载顶点着色器的脚本内容
			mVertexShader[i]=ShaderUtil.loadFromAssetsFile(shaderName[i][0],r);
			//加载片元着色器的脚本内容
			mFragmentShader[i]=ShaderUtil.loadFromAssetsFile(shaderName[i][1], r);
		}
	}
	//这里主要是编译loading界面中的shader
	public static void compileFirstViewShader()
	{
		program[0]=ShaderUtil.createProgram(mVertexShader[0], mFragmentShader[0]);
	}
	//编译其他的shader
	public static void compileShader()
	{
		for(int i=1;i<shaderName.length;i++)
		{
			program[i]=ShaderUtil.createProgram(mVertexShader[i], mFragmentShader[i]);
		}
	}

	//返回的是只有纹理的shader程序
	public static int getSpringTextureShaderProgram()
	{
		return program[0];
	}
	//这里返回的是水面流动的shader程序
	public static int getCylinderTextureShaderProgram()
	{
		return program[1];
	}

}

