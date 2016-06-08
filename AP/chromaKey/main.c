#include <stdio.h>
#include <stdlib.h>

#include <opencv/cv.h>
#include <opencv/cxcore.h>
#include <opencv/highgui.h>

CvCapture* bgAvi;

int main(int argc, char** argv) {
    /* Choose a .avi file */
    // 1 to use the chromaExacto.avi
    // other to use the chroma.avi
    int avi = 1;
    
    /* Choose a background file */
    // 1 to use the backgroung.jp
    // other to use the background.avi
    int bg = 2;
    
    
    
    //CvCapture* aviFile = cvCaptureFromAVI(argv[1]);
    CvCapture* aviFile = cvCaptureFromAVI("C:/Users/tatiux/Desktop/ESEI/Curso2015-2016/AP/Proyecto/chromaKey/chromaExacto.avi");
    if(avi != 1){
    aviFile = cvCaptureFromAVI("C:/Users/tatiux/Desktop/ESEI/Curso2015-2016/AP/Proyecto/chromaKey/chroma.avi");        
    }
    
    if(!aviFile){
        printf("|ERROR|- AVI can´t be opened");
        return EXIT_FAILURE;
    }
    
    //IplImage* background = cvLoadImage(argv[2],CV_LOAD_IMAGE_UNCHANGED);
    
    IplImage* background = cvLoadImage("C:/Users/tatiux/Desktop/ESEI/Curso2015-2016/AP/Proyecto/chromaKey/background.jpg",CV_LOAD_IMAGE_UNCHANGED);
    if( bg != 1){
        bgAvi = cvCaptureFromAVI("C:/Users/tatiux/Desktop/ESEI/Curso2015-2016/AP/Proyecto/chromaKey/background.avi");
        background = cvQueryFrame(bgAvi);
    }
    if(!background){
        printf("|ERROR|- Background can´t be opened");
        return EXIT_FAILURE;
    }
           
    IplImage* frame = cvQueryFrame(aviFile);
    
    int widthF, heightF, nchannelsF, stepF, offsetF;
    int widthB, heightB, nchannelsB, stepB, offsetB;
    int i, j, r_chF, b_chF, g_chF;
     
    int fps = (int)cvGetCaptureProperty(aviFile,CV_CAP_PROP_FPS);
        
    int key=0;
    
    cvNamedWindow("VideoFile",CV_WINDOW_AUTOSIZE);
    
    while(key !='x' && (frame && background)){
        widthF = frame->width;
        heightF = frame->height;
        nchannelsF = frame->nChannels;
        stepF = frame->widthStep;
        widthB = background->width;
        heightB = background->height;
        nchannelsB = background->nChannels;
        stepB = background->widthStep;
        
        for(i = 0 ; i < heightF ; i++) 
        {
          uchar* data = (uchar*)(frame->imageData + i*stepF);
          uchar* dataB = (uchar*)(background->imageData + i*stepB);
          for( j = 0 ; j < widthF ; j++)
          {
            offsetF = j * nchannelsF; 
            b_chF = data[offsetF];
            g_chF = data[offsetF + 1];
            r_chF = data[offsetF + 2];
            
            if(b_chF < 200 && g_chF > 150 && r_chF < 200){
                offsetB = j * nchannelsB; 
                data[offsetF] = dataB[offsetB];
                data[offsetF + 1] = dataB[offsetB + 1];
                data[offsetF + 2] = dataB[offsetB + 2];
            }
          }
        }
        
        cvShowImage("VideoFile",frame);
        
        key=cvWaitKey(1000/fps);
        frame = cvQueryFrame(aviFile);
        if(bg!=1){
           background = cvQueryFrame(bgAvi); 
        }
    }
    
    cvReleaseImage(&background);
    cvReleaseCapture(&aviFile);
    cvDestroyAllWindows();
    return (EXIT_SUCCESS);
}

