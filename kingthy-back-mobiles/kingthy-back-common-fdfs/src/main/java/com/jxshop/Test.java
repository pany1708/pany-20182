package com.jxshop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.csource.FastDFSClient;

import magick.ImageInfo;
import magick.MagickApiException;
import magick.MagickException;
import magick.MagickImage;
import net.coobird.thumbnailator.Thumbnailator;

public class Test
{
    static
    {
        // 不能漏掉这个，不然jmagick.jar的路径找不到
        System.setProperty("jmagick.systemclassloader", "no");
    }
    
    public static void main(String[] args)
        throws Exception
    {
        
        // File f = new File("C:/Users/Administrator/Desktop/sz4.jpg");
        // // File saveDir =
        // //
        // saveTempFile(request,file);http://192.168.1.217/group1/M00/00/15/wKgB21j9Y4eAGGNZAAO-56j0m8M264_120x120.jpg
        //
        // String fileId = FastDFSClient.uploadFile(f, "sz4.jpg");
        // System.out.println("http://192.168.1.217/" + fileId);
        // String masterFile = "http://192.168.1.217/" + fileId;
        // String s = uploadSlaveFile(fileId, "_20x40", "C:/Users/Administrator/Desktop/sz4.jpg");
        // FastDFSClient.downloadFile(s, "C:/Users/Administrator/Desktop/sz4-2040.jpg");
        // System.out.println("http://192.168.1.217/" + s);
        InputStream is = FastDFSClient.downloadFile("group1/M00/00/15/wKgB21j9Y4eAGGNZAAO-56j0m8M264.jpg");
        
        OutputStream os = new FileOutputStream(new File("C:/Users/Administrator/Desktop/asdf.jpg"));
        // 小图
        Thumbnailator.createThumbnail(is, os, 300, 400);
        
        // String s = uploadSlaveFile(fileId, "_20x40", "C:/Users/Administrator/Desktop/sz4.jpg");
        // 中图
        
        // 大图
        
    }
    
    public static void resetsize(String picFrom, String picTo)
    {
        try
        {
            ImageInfo info = new ImageInfo(picFrom);
            MagickImage image = new MagickImage(new ImageInfo(picFrom));
            MagickImage scaled = image.scaleImage(120, 97);
            scaled.setFileName(picTo);
            scaled.writeImage(info);
        }
        catch (MagickApiException ex)
        {
            ex.printStackTrace();
        }
        catch (MagickException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public static String uploadSlaveFile(String masterFileId, String prefixName, String slaveFilePath)
        throws Exception
    {
        String slaveFileId = "";
        String slaveFileExtName = "";
        if (slaveFilePath.contains("."))
        {
            slaveFileExtName = slaveFilePath.substring(slaveFilePath.lastIndexOf(".") + 1);
        }
        else
        {
            return slaveFileId;
        }
        
        // 上传文件
        try
        {
            slaveFileId = FastDFSClient.upload_file1(masterFileId, prefixName, slaveFilePath, slaveFileExtName, null);
        }
        catch (Exception e)
        {
        }
        
        return slaveFileId;
    }
}
