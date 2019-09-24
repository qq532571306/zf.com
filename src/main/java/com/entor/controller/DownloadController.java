package com.entor.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DownloadController {

	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request,String fileName) throws IOException{
		String path = request.getServletContext().getRealPath("/upload/");
		FileInputStream fis = new FileInputStream(new File(path,fileName));
		//把文件读到字节数组中
		byte[] b = new byte[fis.available()];
		fis.read(b);
		fis.close();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName, "utf-8"));
		return new ResponseEntity<byte[]>(b,headers,HttpStatus.OK);
	}
	@RequestMapping("/download2")
	public void download2(HttpServletRequest request,String fileName,HttpServletResponse response) throws IOException {
		String path = request.getServletContext().getRealPath("/upload/");
		File file = new File(path,fileName);
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(fileName, "utf-8"));
		response.setHeader("Content-Length", String.valueOf(file.length()));
		FileInputStream fis = new FileInputStream(file);
		OutputStream os = response.getOutputStream();
		byte[] b  = new byte[1024];
		int len;
		while((len=fis.read(b))!=-1) {
			os.write(b,0,len);
		}
		fis.close();
		os.close();
	}
	@RequestMapping("/download3")
	@ResponseBody
	public void download3(HttpServletRequest request,String fileName,HttpServletResponse response) throws IOException {
		String path = request.getServletContext().getRealPath("/upload/");
		File file = new File(path,fileName);
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode(fileName, "utf-8"));
		response.setHeader("Content-Length", String.valueOf(file.length()));
		Files.copy(file.toPath(), response.getOutputStream());
	}
}
