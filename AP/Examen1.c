#include <stdio.h>
#include <stdlib.h>

#include <opencv/cv.h>
#include <opencv/cxcore.h>
#include <opencv/highgui.h>

CvCapture* bgAvi;

/* BACKGROUND 
int main(int argc, char** argv) {
    
    IplImage* background = cvLoadImage("C:/Users/ap1/Desktop/166704_SantanaGonzalez_Marco_chromaKey_1/chromaKey/background.jpg",CV_LOAD_IMAGE_UNCHANGED);
    if(!background){
        printf("|ERROR|- Background can´t be opened");
        return EXIT_FAILURE;
    }
    
    cvNamedWindow("Background",CV_WINDOW_AUTOSIZE);
    
    int width, height, channels, rowStep, offset, mid,start,end;
    int i,j,m;
    
    //Cols
    width = background->width;
    //Rows
    height = background->height;
    channels = background->nChannels;
    //Cols * 3(RGB)
    rowStep = background->widthStep;
   
    start = 0;
    mid = height/2;
    end = height-1;
    
    while(start != mid){
        //Top
        uchar* dataTop = (uchar*) (background->imageData + start*rowStep);
        //DO
        for(j=0;j<width;j++){
            //RGB = 3 channels
            offset = j * channels;
            //Blue
            dataTop[offset] = 0;
            //Green
            dataTop[offset+1] = 0;
            //Red
            dataTop[offset+2] = 0;
        }
        start++;
        
        cvShowImage("Background",background);
        cvWaitKey(10);
        
        //Bottom
        uchar* dataBottom = (uchar*) (background->imageData + end*rowStep);
        //DO
        for(j=0;j<width;j++){
            //RGB = 3 channels
            offset = j * channels;
            //Blue
            dataBottom[offset] = 0;
            //Green
            dataBottom[offset+1] = 0;
            //Red
            dataBottom[offset+2] = 0;
        }
        end--;
        
        cvShowImage("Background",background);
        cvWaitKey(10);
    }    
    
    cvReleaseImage(&background);
    cvDestroyAllWindows();
    return (EXIT_SUCCESS);
}
*/

/* PROJECT */
int main(int argc, char** argv) {
    // Choose a .avi file 
    // 1 to use the chromaExacto.avi
    // other to use the chroma.avi
    int avi = 1;
    
    // Choose a background file 
    // 1 to use the backgroung.jp
    // other to use the background.avi
    int bg = 1;
    
    CvCapture* aviFile = cvCaptureFromAVI("C:/Users/ap1/Desktop/166704_SantanaGonzalez_Marco_chromaKey_1/chromaKey/chromaExacto.avi");
    if(avi != 1){
    aviFile = cvCaptureFromAVI("C:/Users/ap1/Desktop/166704_SantanaGonzalez_Marco_chromaKey_1/chromaKey/chroma.avi");        
    }
    
    if(!aviFile){
        printf("|ERROR|- AVI can´t be opened");
        return EXIT_FAILURE;
    }
        
    IplImage* background = cvLoadImage("C:/Users/ap1/Desktop/166704_SantanaGonzalez_Marco_chromaKey_1/chromaKey/background.jpg",CV_LOAD_IMAGE_UNCHANGED);
    if( bg != 1){
        bgAvi = cvCaptureFromAVI("C:/Users/ap1/Desktop/166704_SantanaGonzalez_Marco_chromaKey_1/chromaKey/background.avi");
        background = cvQueryFrame(bgAvi);
    }
    if(!background){
        printf("|ERROR|- Background can´t be opened");
        return EXIT_FAILURE;
    }
           
    IplImage* frame = cvQueryFrame(aviFile);
    
    //To Frame
    int widthF, heightF, nchannelsF, stepF, offsetF;
    //To Background
    int widthB, heightB, nchannelsB, stepB, offsetB;
    int i, j, r_chF, b_chF, g_chF;
    //To last pixel
    int offsetBefore,r_last,b_last,g_last;
    double dif;
    
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
        
        // Get first pixel
        uchar* dataBefore = (uchar*)(frame->imageData + 0*stepF);
                offsetBefore = 0 * nchannelsF;  
                    //Blue
                    b_last = dataBefore[offsetBefore];  
                    //Green
                    g_last = dataBefore[offsetBefore + 1];
                    //Red
                    r_last = dataBefore[offsetBefore + 2];    
                    
        for(i = 0 ; i < heightF ; i++) 
        {
          //Chroma  
          uchar* data = (uchar*)(frame->imageData + i*stepF);
          //Background
          uchar* dataB = (uchar*)(background->imageData + i*stepB);
          for( j = 1 ; j < widthF ; j++)
          {
                // Chroma 3 channels RGB  
                offsetF = j * nchannelsF; 
                    //Blue
                    b_chF = data[offsetF];
                    //Green
                    g_chF = data[offsetF + 1];
                    //Red
                    r_chF = data[offsetF + 2];

              // Get dif
              dif = sqrt((r_last - r_chF)) + sqrt((g_last - g_chF)) + sqrt((b_last - b_chF));                        
                // Change green to background
                if(dif<25){
                    offsetB = j * nchannelsB; 
                    //Blue
                    data[offsetF] = dataB[offsetB];
                    //Green
                    data[offsetF + 1] = dataB[offsetB + 1];
                    //Red
                    data[offsetF + 2] = dataB[offsetB + 2];
                    
                    //Stay with the last green pixel
                    offsetBefore = j * nchannelsF;
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


