package com.ocean.controller;

import com.ocean.enums.FileType;
import com.ocean.utils.FileUtil;
import com.ocean.utils.StringUtils;
import com.ocean.vo.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
@Api(tags = "文件相关接口")
public class FileUploadController {
    @PostMapping("/upload")
    @ApiOperation(value = "文件上传",
            notes = "fileType 可选类型video-视频，image-图片，office-文档，other-其他")
    public ResultBean<String> upload(MultipartFile file, @RequestParam String fileType) {
        String filePath = FileUtil.uploadFile(file, FileType.valueOf(fileType.toUpperCase()));
        if (StringUtils.isBlank(filePath)) {
            return ResultBean.ERROR;
        }
        return ResultBean.success(filePath);
    }
}
