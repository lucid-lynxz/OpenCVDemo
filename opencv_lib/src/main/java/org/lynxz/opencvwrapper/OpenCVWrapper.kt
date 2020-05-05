package org.lynxz.opencvwrapper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

object OpenCVWrapper {
    init {
        System.loadLibrary("lynxzCV")
    }

    /**
     * 对图片置灰后返回
     * @param drawableResId 图片资源id
     * */
    fun bitmap2Gray(context: Context, drawableResId: Int): Bitmap? {
        val bitmap = BitmapFactory.decodeResource(context.resources, drawableResId)
        val w = bitmap.width
        val h = bitmap.height
        val piexls = IntArray(w * h)
        bitmap.getPixels(piexls, 0, w, 0, 0, w, h)

        val resultData =
            bitmap2Gray(
                piexls,
                w,
                h
            )
        val resultImage = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        resultImage.setPixels(resultData, 0, w, 0, 0, w, h)
        return resultImage
    }

    /**
     * 对图片置灰后返回
     * */
    fun bitmap2Gray(bitmap: Bitmap): Bitmap? {
        val w = bitmap.getWidth()
        val h = bitmap.getHeight()
        val piexls = IntArray(w * h)
        bitmap.getPixels(piexls, 0, w, 0, 0, w, h)

        val resultData =
            bitmap2Gray(
                piexls,
                w,
                h
            )
        val resultImage = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        resultImage.setPixels(resultData, 0, w, 0, 0, w, h)
        return resultImage
    }


    /**
     * 使用opencv库对图片置灰
     * */
    fun bitmap2GrayByCV(context: Context, drawableResId: Int, config: Bitmap.Config): Bitmap? {
        return bitmap2GrayByCV(
            BitmapFactory.decodeResource(context.resources, drawableResId),
            config
        )
    }

    /**
     * 使用opencv库对图片置灰
     * */
    external fun bitmap2GrayByCV(bitmap: Bitmap?, config: Bitmap.Config?): Bitmap?

    /**
     * 将图片置灰后返回(非openCV方式)
     *
     * @param pixels bitmap所有像素信息
     * @param w      bitmap宽,单位:px
     * @param h      bitmap高,单位:px
     * @return
     */
    external fun bitmap2Gray(pixels: IntArray?, w: Int, h: Int): IntArray?


}