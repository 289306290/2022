package com.test.iflytek.task;

import java.io.File;
import java.io.IOException;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;

public class RecordRtmpStream extends Thread {

    public String streamURL;// 流地址
    public String filePath;// 文件路径
    public void setStreamURL(String streamURL) {
        this.streamURL = streamURL;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public void run() {
        System.out.println("流地址------------------------>"+streamURL);
        // 获取视频源
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(streamURL);
        FFmpegFrameRecorder recorder = null;
        try {
            grabber.start();
            Frame frame = grabber.grabFrame();
            grabber.setOption("stimoout", "5*1000*1000");
            if (frame != null) {
                File outFile = new File(filePath);
                if (!outFile.isFile()) {
                    try {
                        outFile.createNewFile();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                System.out.println("end!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1111111111");
                // 流媒体输出地址，分辨率（长，高），是否录制音频（0:不录制/1:录制）
                recorder = new FFmpegFrameRecorder(filePath, 1080, 1920, 1);
                recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);// 直播流格式
                recorder.setFormat("flv");// 录制的视频格式
//                recorder.setFormat("mp4");// 录制的视频格式
                recorder.setFrameRate(25);// 帧数
                //百度翻译的比特率，默认400000，但是我400000贼模糊，调成800000比较合适
                recorder.setVideoBitrate(800000);
                System.out.println("end!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!22222");
                recorder.start();
                System.out.println("end!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!33333");
                while ((frame != null)) {
                    recorder.record(frame);// 录制
                    // 此处会导致 同步阻塞
                    frame = grabber.grabFrame();// 获取下一帧
                }
                System.out.println("end!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!over");
                recorder.record(frame);
                // 停止录制
                recorder.stop();
                grabber.stop();
            }
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        } catch (FrameRecorder.Exception e) {
            e.printStackTrace();
        } finally {
            if (null != grabber) {
                try {
                    grabber.stop();
                } catch (FrameGrabber.Exception e) {
                    e.printStackTrace();
                }
            }
            if (recorder != null) {
                try {
                    recorder.stop();
                } catch (FrameRecorder.Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
