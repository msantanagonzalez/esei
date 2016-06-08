/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.c
 * Author: Tatiux
 *
 * Created on 3 de mayo de 2016, 21:49
 */

#include <stdio.h>
#include <stdlib.h>


#include <opencv/cv.h>
#include <opencv/cxcore.h>
#include <opencv/highgui.h>
#include <emmintrin.h>

CvCapture* bgAvi;
/*
 * 
 */
/*
int main(int argc, char** argv) {    
    IplImage* imagenOriginal = cvLoadImage("C:/Users/tatiux/Desktop/ESEI/Curso2015-2016/AP/Proyecto/chromaKey2/imagen.jpg",CV_LOAD_IMAGE_UNCHANGED);
    IplImage* imagenDos = cvLoadImage("C:/Users/tatiux/Desktop/ESEI/Curso2015-2016/AP/Proyecto/chromaKey2/imagen2.jpg",CV_LOAD_IMAGE_UNCHANGED);
    
    if(!imagenOriginal || !imagenDos){
        printf("|ERROR|- Background canÂ´t be opened");
        return EXIT_FAILURE;
    }
    
    IplImage* imagenNegra = cvCloneImage(imagenOriginal); //Create a temporal clone of original image
    cvNamedWindow("tempo",CV_WINDOW_AUTOSIZE);
    
    // -------------- BLOQUE 1 (Original to black) ----------------------------- 
    __m128i one,registro, *punteroData; //Registers and pointers declaration
    
    char a=1;
    one = _mm_set1_epi8(a); //Set the "one" register value to "1"

    for(int i=0;i<255;i++){ 
        punteroData = (__m128i *) imagenNegra->imageData; //Set the pointer to tempo image 
        for(int fila=0;fila<imagenNegra->imageSize;fila+=16){ // +=16 because the 16 bytes align
            registro = _mm_load_si128(punteroData); //registro = punteroData with 16 bytes
            registro = _mm_subs_epu8(registro,one); //registro = registro - one
            _mm_store_si128(punteroData,registro); //punteroData = registro
            punteroData++; //punteroData++
        }
        cvWaitKey(8);
        cvShowImage("tempo",imagenNegra);
    }
    
    // -------------- BLOQUE 3 (Black to original) -----------------------------      
       
   __m128i valorPixel,registroDos,*punteroDos; 
   
    for(int j=0;j<255;j++){ 
        punteroData = (__m128i *) imagenNegra->imageData; //Set the pointer to image data 
        punteroDos = (__m128i *) imagenOriginal->imageData; //Set the pointer to original image
        for(int filaBlack=0;filaBlack<imagenNegra->imageSize;filaBlack+=16){ 
            registro = _mm_load_si128(punteroData); //registro = punteroData
            registroDos = _mm_load_si128(punteroDos); //registroDos = punteroDos

            registro = _mm_adds_epu8(registro,one); //registro = registro + 1
            registro = _mm_min_epu8(registroDos,registro); //Avoid registro to set the pixel value > registroDos
            _mm_store_si128(punteroData,registro); //punteroData = registro
            punteroData++;
            punteroDos++;
        }
        cvWaitKey(8);
        cvShowImage("tempo",imagenNegra);
    }
    
    // -------------- BLOQUE 4 (Transition between two images) ----------------------------- 
    
    __m128i *temp1Pointer,*temp2Pointer,temp1Register,temp2Register;
    
    for(int j=0;j<255;j++){ 
        temp1Pointer = (__m128i *) imagenNegra->imageData; //Set the pointer to image data 
        temp2Pointer = (__m128i *) imagenDos->imageData; //Set the pointer to original image
        for(int filaBlack=0;filaBlack<imagenNegra->imageSize;filaBlack+=16){ 
            temp1Register = _mm_load_si128(temp1Pointer); //registro = punteroData
            temp2Register = _mm_load_si128(temp2Pointer); //registroDos = punteroDos
            
            temp1Register = _mm_adds_epu8(temp1Register,one); //registro = registro + 1
            temp1Register = _mm_min_epu8(temp2Register,temp1Register); //Avoid registro to set the pixel value > registroDos
            _mm_store_si128(temp1Pointer,temp1Register); //punteroData = registro
            temp1Pointer++;
            temp2Pointer++;
        }
        cvWaitKey(8);
        cvShowImage("tempo",imagenNegra);
    }  
 * //FadeInFadeOut
 * int newRow,newCol;
    IplImage* result = cvCloneImage(imagenDos);
    IplImage* new = cvCloneImage(imagenDos);
    
    cvZero(result);
    __m128i *imagenResult,*imagenNew,registroResult,registroNew;
    
    for(newCol=0;newCol<result->height;newCol++){
        imagenResult = (__m128i *) result->imageData + result->widthStep*newCol;
        imagenNew = (__m128i *) new->imageData + new->widthStep*newCol;
        for(newRow=0;newRow<result->width*3;newRow++){
            
            registroResult = _mm_load_si128(imagenResult);
            
            registroNew = _mm_load_si128(imagenNew);
            
            registroResult = _mm_adds_epu8(registroResult,registroNew);
            
            _mm_store_si128(imagenResult,registroResult); 
            imagenResult++;
            imagenNew++;
         
        }    
        cvWaitKey(78);
        cvShowImage ("tempo", result);
    }
    cvReleaseImage(&imagenNegra);
    cvReleaseImage(&imagenOriginal);
    cvReleaseImage(&imagenDos);
    cvDestroyAllWindows();
    return (EXIT_SUCCESS);
}
*/

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
    
    __m128i b_FrameReg,g_FrameReg,r_FrameReg;
    __m128i b_FrameRegVal,g_FrameRegVal,r_FrameRegVal;
    __m128i b_BgReg,g_BgReg,r_BgReg;
    __m128i *framePointer,frameReg; 
    
    //int channels = frame->nChannels;
    //printf("Channels: %d \n", channels);
    
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
            //Set the pointer    
            framePointer = (__m128i *) (frame->imageData + i+j*stepF);
            offsetF = j * nchannelsF; 
            b_chF = data[offsetF];
            g_chF = data[offsetF + 1];
            r_chF = data[offsetF + 2];
            
            //Load the pointer
            frameReg = _mm_loadu_si128(framePointer);
                
            if(b_chF < 200 && g_chF > 150 && r_chF < 200){
                offsetB = j * nchannelsB; 
                /*
                data[offsetF] = dataB[offsetB];
                data[offsetF + 1] = dataB[offsetB + 1];
                data[offsetF + 2] = dataB[offsetB + 2];
                */
                
                //Set the values into the registers
                b_FrameRegVal = _mm_set1_epi8((char) b_chF);
                g_FrameRegVal = _mm_set1_epi8((char) g_chF);
                r_FrameRegVal = _mm_set1_epi8((char) r_chF);
                
                b_BgReg = _mm_set1_epi8((char) dataB[offsetB]);
                g_BgReg = _mm_set1_epi8((char) dataB[offsetB+1]);
                r_BgReg = _mm_set1_epi8((char) dataB[offsetB+2]);
                
                //Set the pixels to zero
                b_FrameReg = _mm_subs_epu8(b_FrameReg,b_FrameRegVal);
                g_FrameReg = _mm_subs_epu8(g_FrameReg,g_FrameRegVal);
                r_FrameReg = _mm_subs_epu8(r_FrameReg,r_FrameRegVal);
                //Set the new value to the pixels
                b_FrameReg = _mm_adds_epu8(b_FrameReg,b_BgReg); 
                g_FrameReg = _mm_adds_epu8(g_FrameReg,g_BgReg);
                r_FrameReg = _mm_adds_epu8(r_FrameReg,r_BgReg);
                
                
                //Save the new values
                _mm_storeu_si128(framePointer,frameReg);
                //registro = _mm_min_epu8(registroDos,registro); //registro = min(registroDos,registro) registro no puede pasar valor de registroDos
            }
            framePointer++;
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
