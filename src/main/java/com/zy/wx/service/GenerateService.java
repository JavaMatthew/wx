package com.zy.wx.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.zy.wx.dto.InfoDTO;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerTemplateAvailabilityProvider;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: ZYi
 * @Date: 2021/3/22 18:53
 * @Version 1.0
 **/
@Service
public class GenerateService {
    public void add(InfoDTO info, HttpServletResponse response) throws IOException {
        String text = "https://www.yaomanghe.com/q/show?acceptDate=" + info.getAcceptDate() + "&name=" + info.getName() + "&dossier=" + info.getDossier() + "&number=" + info.getNumber() + "&applicant=" + info.getApplicant();
        int width = 100;
        int height = 100;
        String format = "png";
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            response.reset();
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }

    public static void main(String[] args) {
        String text = "www.baidu.com";
        int width = 100;
        int height = 100;
        String format = "png";
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            Path file = new File("D:/new.png").toPath();//在D盘生成二维码图片
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        } catch (WriterException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void gen(MultipartFile file, HttpServletResponse response) throws IOException {
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);
        int width = 100;
        int height = 100;
        String format = "jpg";
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.MARGIN, 2);
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            response.reset();
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageWriter.writeToStream(bitMatrix, format, stream);
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }finally {
            if (stream != null) {
                stream.flush();
                stream.close();
            }
        }
    }
}

