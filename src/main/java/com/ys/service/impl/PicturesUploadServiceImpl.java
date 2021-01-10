package com.ys.service.impl;

import com.ys.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Service
public class PicturesUploadServiceImpl {
    /**
     * 文件上传工具类
     */
    @Autowired
    private FileUtil fileUtil;

    @Transactional //得到图片的名字
    public String[] add(MultipartFile[] file) throws Exception {
        return fileUtil.uploadFiles(file);
    }
}
