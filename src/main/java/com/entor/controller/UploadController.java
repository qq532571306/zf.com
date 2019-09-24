package com.entor.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	/**
	 * 
	 * 文件上传
	 * @MultipartFile
	 *
	 */
	@RequestMapping("/uploadFile")
	public String uploadFile(String name,MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException {
		System.out.println(name);
		String path = request.getServletContext().getRealPath("/upload/");
		if(file!=null) {
			//获得文件类型
			String contentType = file.getContentType();
			//获得文件名称
			String fileName = file.getOriginalFilename();
			//文件大小
			long size = file.getSize();
			System.out.println("文件类型："+contentType);
			System.out.println("文件名称"+fileName);
			System.out.println("文件大小："+size);
			System.out.println(path);
			//文件上传
			file.transferTo(new File(path,fileName));
		}
		return "index";
	}
	/**
	 * 
	 * 多文件上传
	 * @MultipartFile[]
	 *
	 */
	@RequestMapping("/uploadFiles")
	public String uploadFiles(String name,MultipartFile[] file,HttpServletRequest request) throws IllegalStateException, IOException {
		System.out.println(name);
		String path = request.getServletContext().getRealPath("/upload/");
		if(file.length>0) {
			for (MultipartFile f : file) {
				if(f!=null) {
					//获得文件类型
					String contentType = f.getContentType();
					//获得文件名称
					String fileName = f.getOriginalFilename();
					//文件大小
					long size = f.getSize();
					if(!"".equals(fileName)) {
						System.out.println("文件类型："+contentType);
						System.out.println("文件名称："+fileName);
						System.out.println("文件大小："+size);
						System.out.println(path);
						//文件上传
						f.transferTo(new File(path,fileName));
					}
				}
			}
		}
		return "index";
	}
}
