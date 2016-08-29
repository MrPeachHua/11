package com.boxiang.share.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by dfl on 2016/7/28.
 */
public class ZipUtil {

    public static void main(String[] args) {
        try {
            zip("C:\\Users\\Public\\Pictures\\SamplePictures.zip",
                    new File("C:\\Users\\Public\\Pictures\\Sample Pictures"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zip(String zipFileName, File inputFile) throws Exception {
//        System.out.println("压缩中...");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        BufferedOutputStream bo = new BufferedOutputStream(out);
        zip(out, inputFile, inputFile.getName(), bo);
        bo.close();
        out.close(); // 输出流关闭
//        System.out.println("压缩完成");
    }

    private static void zip(ZipOutputStream out, File f, String base, BufferedOutputStream bo) throws Exception { // 方法重载
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            if (fl.length == 0) {
                out.putNextEntry(new ZipEntry(base + "/")); // 创建zip压缩进入点base
//                System.out.println(base + "/");
            }
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + "/" + fl[i].getName(), bo); // 递归遍历子文件夹
            }
        } else {
            out.putNextEntry(new ZipEntry(base)); // 创建zip压缩进入点base
//            System.out.println(base);
            FileInputStream in = new FileInputStream(f);
            BufferedInputStream bi = new BufferedInputStream(in);
            int b;
            while ((b = bi.read()) != -1) {
                bo.write(b); // 将字节流写入当前zip目录
            }
            bo.flush();
            bi.close();
            in.close(); // 输入流关闭
        }
    }

}
