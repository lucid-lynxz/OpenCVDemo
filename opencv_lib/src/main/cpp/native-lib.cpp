#include <jni.h>
#include <string>
#include <opencv2/imgproc/types_c.h>
#include "bitmap_utils.h"

extern "C"
JNIEXPORT jobject JNICALL
Java_org_lynxz_opencvwrapper_OpenCVWrapper_bitmap2GrayByCV(JNIEnv *env, jobject thiz, jobject bitmap,
                                                        jobject config) {
    Mat srcMat;
    Mat dstMat;
    bitmap2Mat(env, bitmap, &srcMat);
    cvtColor(srcMat, dstMat, CV_BGR2GRAY);//将图片的像素信息灰度化盛放在dstMat
    return createBitmap(env, dstMat, config);//使用dstMat创建一个Bitmap对象
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_org_lynxz_opencvwrapper_OpenCVWrapper_bitmap2Gray(JNIEnv *env, jobject thiz, jintArray pixels,
                                                    jint w, jint h) {
    jint *cbuf;
    jboolean ptfalse = false;
    cbuf = env->GetIntArrayElements(pixels, &ptfalse);
    if (cbuf == NULL) {
        return 0;
    }

    Mat imgData(h, w, CV_8UC4, (unsigned char *) cbuf);
    // 注意，Android的Bitmap是ARGB四通道,而不是RGB三通道
    cvtColor(imgData, imgData, COLOR_BGRA2GRAY);
    cvtColor(imgData, imgData, COLOR_GRAY2BGRA);

    int size = w * h;
    jintArray result = env->NewIntArray(size);
    env->SetIntArrayRegion(result, 0, size, (jint *) imgData.data);
    env->ReleaseIntArrayElements(pixels, cbuf, 0);
    return result;
}