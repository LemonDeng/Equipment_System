package com.ys.utils;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * lixin
 * 上传下载文件工具类
 */
@Component
public class FileUtil {

    @Value("${file.path}")
    private String dirpath;

    /**
     * 单文件上传
     *
     * @param file
     * @return
     * @throws Exception
     */
    public String uploadFile(MultipartFile file) throws Exception {
        // 首先校验图片格式
        List<String> imageType = Lists.newArrayList("jpg", "jpeg", "png", "bmp", "gif");
        // 获取文件名，带后缀
        String originalFilename = file.getOriginalFilename();
        // 获取文件的后缀格式
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        if (imageType.contains(fileSuffix)) {
            // 只有当满足图片格式时才进来，重新赋图片名，防止出现名称重复的情况
            String newFileName = System.currentTimeMillis() + "." + fileSuffix;
            String path = File.separator + newFileName;
            File destFile = new File(this.dirpath + path);
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            try {
                file.transferTo(destFile);
                // 返回上传的文件名
                return newFileName;
            } catch (IOException e) {
                return null;
            }
        } else {
            // 非法文件
            throw new Exception("the picture's suffix is illegal");
        }
    }

    /**
     * 多文件上传 返回上传后的文件名和原文件名
     *
     * @param file
     * @return
     * @throws Exception
     */
    public String[] uploadFiles(MultipartFile[] file) throws Exception {
        String fileNames[] = new String[file.length];
        String saveFileNames[] = new String[file.length];
        for (int i = 0, len = file.length; i < len; i++) {
            String fileName = file[i].getOriginalFilename();
            fileNames[i] = fileName;
            String saveFileName = uploadFile(file[i]);
            saveFileNames[i] = saveFileName;
        }
//        Map<String, String> map = new HashMap<>();
//        //map.put("fileNames", String.join(",", fileNames)); 原名字
//        map.put("saveFileNames", String.join(",", saveFileNames));
        return saveFileNames;
    }
}