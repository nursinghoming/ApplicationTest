#include <jni.h>
#include <string>
#include <opencv2/core/core.hpp>
#include <opencv2/imgproc.hpp>
using namespace std;
using namespace cv;

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_example_raytine_applicationtest_MainActivity_ResultTest
        (JNIEnv *env, jobject instance, jintArray Sbuf, jintArray Tbuf,jint Wstand, jint Hstand,jint Wtest,jint Htest) {

    jint *cbuf1;
    jboolean ptfalse1 = false;
    Mat imgStand;
    cbuf1 = env->GetIntArrayElements(Sbuf, &ptfalse1);
    if (cbuf1 == NULL) {
        return 0;
    }
    Mat imgStand1(Hstand, Wstand, CV_8UC4, (unsigned char *) cbuf1);
    cvtColor(imgStand1 , imgStand , CV_RGBA2BGR);

    jint *cbuf2;
    jboolean ptfalse2 = false;
    Mat imgTest;
    cbuf2 = env->GetIntArrayElements(Tbuf, &ptfalse2);
    if (cbuf2 == NULL) {
        return 0;
    }
    Mat imgTest1(Htest, Wtest, CV_8UC4, (unsigned char *) cbuf2);
    //cvtColor(imgTest1 , imgTest , CV_RGBA2RGB);
    cvtColor(imgTest1 , imgTest , CV_RGBA2BGR);

    int size=imgStand.rows*imgStand.cols;
    int *outImage=new int[size];
    for(int i=0;i<size;i++)
    {
        outImage[i]=(int)imgStand.data[i];
    }
    jintArray result = env->NewIntArray(size);
    env->SetIntArrayRegion(result, 0, size, outImage);
    env->ReleaseIntArrayElements(Sbuf, cbuf1, 0);
    env->ReleaseIntArrayElements(Tbuf, cbuf2, 0);
    return result;
}
