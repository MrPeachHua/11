package com.boxiang.share.sample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.boxiang.framework.base.Constants;
import com.boxiang.share.utils.json.JacksonUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import net.sf.json.JSONObject;

/**
 * 二维码生成与解析
 *
 */
public class QRCodeTest {
	private static final int QRCODE_WIDTH_SIZE = 200; // 图像宽度  
	private static final int QRCODE_HEIGHT_SIZE = 200; // 图像高度  
	
    /** 
     * 生成图像 
     *  
     * @throws WriterException 
     * @throws IOException 
     */  
    @Test  
    public void testEncode() throws WriterException, IOException {  
        String filePath = "D://tmp";  
        String fileName = "aaaabbb.png";  
        Map<String,String> map = new HashMap<String,String>();
        map.put("action_name", "https://github.com/zxing/zxing/tree/zxing-3.0.0/javase/src/main/java/com/google/zxing");
        map.put("action_info", "shihy");
        String content = JacksonUtil.toJson(map);// 内容  
        String format = "png";// 图像类型    
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>(); 
        hints.put(EncodeHintType.CHARACTER_SET, Constants.CHARACTER_ENCODING_UTF8); 
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,  BarcodeFormat.QR_CODE, QRCODE_WIDTH_SIZE, QRCODE_HEIGHT_SIZE, hints);// 生成矩阵  
        Path path = FileSystems.getDefault().getPath(filePath, fileName);  
        MatrixToImageWriter.writeToPath(bitMatrix, format, path);// 输出图像  
        System.out.println("输出成功.");  
    }  
  
    /** 
     * 解析图像 
     */  
    @Test  
    public void testDecode() {  
        String filePath = "D://tmp/w.png";  
        BufferedImage image;  
        try {  
            image = ImageIO.read(new File(filePath));  
            LuminanceSource source = new BufferedImageLuminanceSource(image);  
            Binarizer binarizer = new HybridBinarizer(source);  
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();  
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");  
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);// 对图像进行解码  
            Map<String,Object> content = JacksonUtil.jsonToMap(result.getText());
            System.out.println("图片中内容：  ");  
            System.out.println("author： " + content.get("author"));  
            System.out.println("zxing：  " + content.get("zxing"));  
            System.out.println("图片中格式：  ");  
            System.out.println("encode： " + result.getBarcodeFormat());  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (NotFoundException e) {  
            e.printStackTrace();  
        }  
    }  
}
