package com.example.animationdemo;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Administrator on 2019/3/31/031.
 */

public class Rotate3dAnimation extends Animation {


    /** final 修饰成员变量
     *  a. 必须初始化值。
     *  b. 被fianl修饰的成员变量赋值，有两种方式：1、直接赋值 2、全部在构造方法中赋初值。
     *  c. 如果修饰的成员变量是基本类型，则表示这个变量的值不能改变。
     *  d. 如果修饰的成员变量是一个引用类型，则是说这个引用的地址的值不能修改，但是这个引用所指向的对象里面的内容还是可以改变的。
     */
    private final float mFromDegrees;
    private final float mToDegrees;
    private final float mCenterX;
    private final float mCenterY;
    private final float mDepthZ;
    private final boolean mReverse;
    private Camera mCamera;
    /**
     *
     * @param fromDegrees
     * @param toDegrees
     * @param centerX
     * @param centerY
     * @param depthZ
     * @param reverse
     */
    public Rotate3dAnimation(float fromDegrees,float toDegrees,float centerX,float centerY,float depthZ,boolean reverse){
        mFromDegrees = fromDegrees;
        mToDegrees = toDegrees;
        mCenterX = centerX;
        mCenterY = centerY;
        mDepthZ = depthZ;
        mReverse = reverse;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final float fromDegrees = mFromDegrees;
        float degress = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);
        final float centerX = mCenterX;
        final float centerY = mCenterY;
        final Camera camera = mCamera;
        final Matrix matrix = t.getMatrix();
        camera.save();
        if (mReverse){
            camera.translate(0.0f,0.0f,mDepthZ * interpolatedTime);
        } else {
            camera.translate(0.0f,0.0f,mDepthZ * (1.0f - interpolatedTime));
        }
        camera.rotateY(degress);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-centerX,-centerY);
        matrix.postTranslate(centerX,centerY);
    }
}
