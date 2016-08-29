package com.boxiang.share.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class ImageProcess {
    /**
     * 图片
     */
    private Image img;
    /**
     * 宽度
     */
    private int width;
    /**
     * 高度
     */
    private int height;
    /**
     * 文件格式
     */
    private String imageFormat;

    /**
     * 构造函数
     *
     * @throws Exception
     */
    public ImageProcess(InputStream in, String fileName) throws Exception {
        //构造Image对象
        img = ImageIO.read(in);
        //得到源图宽
        width = img.getWidth(null);
        //得到源图长
        height = img.getHeight(null);
        //文件格式
        imageFormat = fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 按照宽度还是高度进行压缩
     *
     * @param w int 最大宽度
     * @param h int 最大高度
     */
    public byte[] resizeFix(int w, int h) throws IOException {
        if (width / height > w / h) {
            return resizeByWidth(w);
        } else {
            return resizeByHeight(h);
        }
    }

    /**
     * 以宽度为基准，等比例放缩图片
     *
     * @param w int 新宽度
     */
    public byte[] resizeByWidth(int w) throws IOException {
        int h = height * w / width;
        return resize(w, h);
    }

    /**
     * 以高度为基准，等比例缩放图片
     *
     * @param h int 新高度
     */
    public byte[] resizeByHeight(int h) throws IOException {
        int w = width * h / height;
        return resize(w, h);
    }

    /**
     * 强制压缩/放大图片到固定的大小
     *
     * @param w int 新宽度
     * @param h int 新高度
     */
    public byte[] resize(int w, int h) throws IOException {
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, imageFormat, baos);
        return baos.toByteArray();
    }


//    public static void main(String[] args) throws Exception {
//        InputStream io = new FileInputStream("C:/test/Koala.jpg");
//        ImageProcess imageProcess = new ImageProcess(io, "Koala.jpg");
//        byte[] b = imageProcess.resizeByWidth(500);
//        OutputStream os = new FileOutputStream("C:/test/kl.jpg");
//        os.write(b);
//    }
}