package org.lynxz.opencvdemo


import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.lynxz.opencvwrapper.OpenCVWrapper
import org.opencv.android.OpenCVLoader


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.android);
//        iv_image_before.setImageBitmap(opBitmap(bitmap, Bitmap.Config.ARGB_8888));
        iv_image_before.setImageResource(R.drawable.op)

        val initDebug = OpenCVLoader.initDebug()
        Log.e("mainActivity", "init opencv result: $initDebug")

        btn_rest.setOnClickListener { iv_image_after.setImageBitmap(null) }

        btn_gray.setOnClickListener {
            iv_image_after.setImageBitmap(OpenCVWrapper.bitmap2Gray(this, R.drawable.op))
        }

        btn_gray_cv.setOnClickListener {
            iv_image_after.setImageBitmap(
                OpenCVWrapper.bitmap2GrayByCV(
                    this,
                    R.drawable.op,
                    Bitmap.Config.ARGB_8888
                )
            )
        }
    }
}
