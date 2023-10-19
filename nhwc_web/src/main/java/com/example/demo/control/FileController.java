package com.example.demo.control;

import com.example.demo.utils.ApiUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Front controller
 * </p>
 *
 * @author Yitian Guo and Depeng Zhang
 * @since 2023-09-25
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${yo.file.root.path}")
    private String path;
    /**Image url prefix*/
    private String base_picture_url = ApiUtils.baseUrl;
    @PostMapping("/upload")
    @ResponseBody
    public Object updateIcon(@RequestParam("file") List<MultipartFile> file) throws IOException {
        //Get the location of the file stored on the server
        File filePath = new File(path);
        System.out.println("Save path of the file"+path);
        if(!filePath.exists() && !filePath.isDirectory()){
            System.out.println("Directory does not exist, create directory" + filePath);
            filePath.mkdir();
        }
        //Access to the name of the original document (including format)
        String originalFileName = file.get(0).getOriginalFilename();
        System.out.println("Name of original document" + originalFileName);
        //Get the file type, identified by the last '.' as the identifier
        String t = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("Document type" + t);
        //Set new name for file: current event + file name (without formatting)
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + "." +t;
        System.out.println("Document type" +fileName);
        //Create a file in the specified path
        File targetFile = new File(path,fileName);
        Map<String,Object> result = new HashMap<String,Object>();//Defining results
        //Save a file to a specified location on the server
        try{
            file.get(0).transferTo(targetFile);
            System.out.println("Uploaded successfully");
            result.put("code",0);
            result.put("msg","Add successfully");
            result.put("data",null);
            result.put("url",base_picture_url+ fileName);
            return result;
        }catch (IOException e){
            System.out.println("Upload Failed");
            result.put("code",1);
            result.put("msg","Add Failure");
            e.printStackTrace();
            return result;
        }

    }

}
