package com.ys.controller;


import com.ys.common.ApiRestResponse;
import com.ys.exception.YsLjException;
import com.ys.exception.YsLjExceptionEnum;
import com.ys.model.pojo.Component;
import com.ys.model.vo.ComponentVo;
import com.ys.model.vo.QrCodeVo;
import com.ys.service.ComponentService;
import com.ys.service.QrCodeService;
import com.ys.utils.QRcodeUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

/**
 * @Description:
 * @Auther: dengzhili
 * @Date: 2021/01/13/19:33
 */
@Controller
public class QrCodeController {
    /**
     * @description: 获取二维码的方法,通过IO流返回,不再写入磁盘通过路径展示
     * @param response
     * @return void
     * @author: dengzhili
     * @date: 2021/1/13
     * */
    @Autowired
    ComponentService componentService;

    @Autowired
    QrCodeService qrCodeService;
    @ApiOperation(value = "二维码功能",notes = "eCode:设备编码(String)")
    @GetMapping("/getShopCode")
    public void getCode(Component component,HttpServletResponse response) throws IOException {

        //设置输出文件格式
        response.setContentType("image/png");
        Component CurrentComponent = qrCodeService.qrCodeImp(component);

        if (CurrentComponent == null) {
            throw new YsLjException(YsLjExceptionEnum.REQUEST_PARAM_ERROR);
        }

        // 存放在二维码中的内容
        String text = "\n零件名称: "+CurrentComponent.getcName()+"\n零件编码: "+CurrentComponent.getcCode()+"\n设备名称："+CurrentComponent.geteName();
        String companyName = "";

        String logoPath = this.getClass().getClassLoader().getResource("logo.jpeg").getPath();

        String bgPath = this.getClass().getClassLoader().getResource("beijing.jpeg").getPath();


        //生成二维码
        BufferedImage qrCodeImage = QRcodeUtils.generateQrCode(text, logoPath);
        BufferedImage picture = QRcodeUtils.createPictureNew(qrCodeImage, bgPath, companyName);

        //获取绘制好的图片的InputStream对象
        InputStream in = getImageStream(picture);
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        //创建存放文件内容的数组
        byte[] buff =new byte[1024];
        //所读取的内容使用n来接收
        int n;
        //当没有读取完时,继续读取,循环
        while((n=in.read(buff))!=-1){
            //将字节数组的数据全部写入到输出流中
            outputStream.write(buff,0,n);
        }
        //强制将缓存区的数据进行输出
        outputStream.flush();
        //关流
        outputStream.close();
        in.close();
    }

    //从图片文件或BufferedImage中得到InputStream
    public static InputStream getImageStream(BufferedImage bi) {

        InputStream is = null;

        ByteArrayOutputStream bs = new ByteArrayOutputStream();

        ImageOutputStream imOut;
        try {
            imOut = ImageIO.createImageOutputStream(bs);

            ImageIO.write(bi, "png", imOut);

            is = new ByteArrayInputStream(bs.toByteArray());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

}
