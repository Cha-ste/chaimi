package com.ocean.utils;

import com.ocean.enums.FileType;
import com.ocean.exception.FileEmptyException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件处理工具类
 */
public class FileUtil {
    private static String FOLDER = "E:/";
    private static String FILE_PATH = "upload/";
    /**
     * 根据文件类型上传文件
     * @param file 文件
     * @param fileType 文件类型
     * @return 文件保存路径
     * @throws FileEmptyException 空文件
     */
    public static String uploadFile(MultipartFile file, FileType fileType) throws FileEmptyException {
        if(file == null) {
            throw new FileEmptyException("文件为空");
        }
        // 1、拼接文件保存目录
        String filePath = FOLDER + FILE_PATH + fileType.getValue();
        // 2、创建文件夹链接
        File folder = new File(filePath);
        // 3、判断文件夹是否存在，否则创建
        if(!folder.isDirectory()) {
            folder.mkdirs();
        }
        // 4、生成需要返回的文件名（时间戳加原本的文件名）
        Long millisSecond = DateUtils.getCurrentMillisSecond();
        String originalFilename = file.getOriginalFilename();
        String realPath = filePath + "/" + millisSecond + "-" + originalFilename;
        // 5、使用文件转换，把MultipartFile写入磁盘
        try {
            file.transferTo(new File(realPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 6、返回存储路径
        return realPath;
    }

}
