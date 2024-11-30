package org.yixi.mallcore.qcode;

import cn.binarywang.wx.miniapp.api.WxMaCodeService;
import cn.binarywang.wx.miniapp.api.WxMaQrcodeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.yixi.mallcore.storage.StorageService;
import org.yixi.mallcore.system.SystemConfig;
import org.yixi.malldb.bean.LitemallCoupon;
import org.yixi.malldb.bean.LitemallGroupon;
import org.yixi.malldb.bean.LitemallStorage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

@Service
public class QCodeService {
    private final Log logger = LogFactory.getLog(QCodeService.class);

    @Autowired
    WxMaService wxMaService;

    @Autowired
    private StorageService storageService;

    /**
     * 创建商品分享二维码
     * @param goodName
     * @param goodPicUrl
     * @param groupon
     * @return
     */
    public String createGrouponShareImage(String goodName, String goodPicUrl, LitemallGroupon groupon){
        try {
            // 创建二维码
            WxMaQrcodeService codeService = wxMaService.getQrcodeService();
            File file = codeService.createWxaCodeUnlimit("groupon," + groupon.getId(), "pages" + "/index/index");
            FileInputStream inputStream = new FileInputStream(file);
            // 将商品图片，商品名字，商城名字刻画的模板图上
            byte[] image = drawPicture(inputStream, goodPicUrl, goodName);
            ByteArrayInputStream inputStream1 = new ByteArrayInputStream(image);

            LitemallStorage storageInfo = storageService.store(inputStream1, image.length, "image/jpeg",
                    getKeyName(groupon.getId().toString()));
            return storageInfo.getUrl();

        } catch (WxErrorException e) {
            logger.error(e.getMessage(), e);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }

    public String createGoodShareImage(String goodId,String goodPicUrl, String goodName){
        if(!SystemConfig.isAutoCreateShareImage()){  //是否自动创建
            return "";
        }
        try {
            // 创建二维码
            WxMaQrcodeService codeService = wxMaService.getQrcodeService();
            File file = codeService.createWxaCodeUnlimit("good," + goodId, "pages" + "/index/index");
            FileInputStream inputStream = new FileInputStream(file);
            // 将商品图片，商品名字，商城名字刻画的模板图上
            byte[] image = drawPicture(inputStream, goodPicUrl, goodName);
            ByteArrayInputStream inputStream1 = new ByteArrayInputStream(image);

                //存储分享图
            LitemallStorage storageInfo = storageService.store(inputStream1, image.length, "image/jpeg",
                    getKeyName(goodId));
            return storageInfo.getUrl();

        }catch (WxErrorException e) {
            logger.error(e.getMessage(), e);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }


    private String getKeyName(String goodId) {
        return "GOOD_QCODE_" + goodId + ".jpg";
    }

    /**
     * 在图片上写文字
     * @param baseImage
     * @param textToWrite
     * @param x
     * @param y
     */
    private void drawTextInImg(BufferedImage baseImage, String textToWrite, int x, int y) {
        Graphics2D g2D = (Graphics2D) baseImage.getGraphics();
        g2D.setColor(new Color(167, 136, 69));

        //TODO 注意，这里的字体必须安装在服务器上
        g2D.setFont(new Font("Microsoft YaHei", Font.PLAIN, 28));
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2D.drawString(textToWrite, x, y);
        g2D.dispose();
    }

    /**
     * 在图片上写图片
     * @param baseImage
     * @param imageToWrite
     * @param x
     * @param y
     * @param width
     * @param heigth
     */
    private void drawImgInImg(BufferedImage baseImage, BufferedImage imageToWrite, int x, int y, int width,
                              int heigth) {
        Graphics2D g2D = (Graphics2D) baseImage.getGraphics();
        g2D.drawImage(imageToWrite, x, y, width, heigth, null);
        g2D.dispose();
    }

    private byte[] drawPicture(InputStream QrCodeImg, String goodPicUrl, String goodName)throws IOException{
        //底图
        ClassPathResource redResource = new ClassPathResource("back.png");
        BufferedImage red = ImageIO.read(redResource.getInputStream());

        //商品图片
        URL goodPic = new URL(goodPicUrl);
        BufferedImage goodImage = ImageIO.read(goodPic);

        //小程序二维码
        BufferedImage qrCodeImage = ImageIO.read(QrCodeImg);

        // --- 画图 ---

        //底层空白 bufferedImage
        BufferedImage baseImage = new BufferedImage(red.getWidth(), red.getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);

        //画上图片
        drawImgInImg(baseImage, red, 0, 0, red.getWidth(), red.getHeight());

        //画上商品图片
        drawImgInImg(baseImage, goodImage, 71, 69, 660, 660);

        //画上小程序二维码
        drawImgInImg(baseImage, qrCodeImage, 448, 767, 300, 300);

        //写上商品名称
        drawTextInImg(baseImage, goodName, 65, 867);

        //写上商城名称
        //        drawTextInImgCenter(baseImage, shopName, 98);

        //转为jpg
        BufferedImage result = new BufferedImage(baseImage.getWidth(), baseImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        result.getGraphics().drawImage(baseImage, 0, 0, null);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(result, "jpg", out);
        return out.toByteArray();
    }
    private void drawTextInImgCenter(BufferedImage baseImage, String textToWrite, int y) {
        Graphics2D g2D = (Graphics2D) baseImage.getGraphics();
        g2D.setColor(new Color(167, 136, 69));

        String fontName = "Microsoft YaHei";

        Font f = new Font(fontName, Font.PLAIN, 28);
        g2D.setFont(f);
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 计算文字长度，计算居中的x点坐标
        FontMetrics fm = g2D.getFontMetrics(f);
        int textWidth = fm.stringWidth(textToWrite);
        int widthX = (baseImage.getWidth() - textWidth) / 2;
        // 表示这段文字在图片上的位置(x,y) .第一个是你设置的内容。

        g2D.drawString(textToWrite, widthX, y);
        // 释放对象
        g2D.dispose();
    }

}
