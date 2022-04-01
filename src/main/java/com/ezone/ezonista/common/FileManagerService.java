package com.ezone.ezonista.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	
	public final static String FILE_UPLOAD_PATH = "C:\\Users\\EZi09\\Desktop\\ezone\\ezonista\\upload\\images/";
	
	private static Logger logger = LoggerFactory.getLogger(FileManagerService.class);
	
	public static String saveFile(int userId, MultipartFile file) {
		
		if(file == null) {
			
			logger.error("FileManagerService-saveFile : 파일 없음");
			return null;
		}
		
		// 사용자 별로 폴더 경로 구분하기 위해 directoryName에 담음
		String directoryName = userId + "_" +System.currentTimeMillis() + "/";
		
		// 최종 파일 경로 : filePath
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(filePath);
		
			// 디렉토리 생성 에러
		if (directory.mkdir() == false) {
			logger.error("FileManagerService-saveFile : 디렉토리 생성 에러");
			return null;
		}
		
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
			
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("FileManagerService-saveFile : 파일 저장 에러");
		}
		
		return "/images/" + directoryName + file.getOriginalFilename();
		
	}
	
	// 파일 및 파일 디렉토리 삭제
	
	public static boolean removeFile(String filePath) {
		
		if(filePath == null) {
			logger.error("FileManagerService-removeFile : 파일 없음");
			return false;
		}
		
		String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/images/" , "");
		
		Path path = Paths.get(realFilePath);
		
		if (Files.exists(path)) {
			
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("FileMangerService-removeFile : 파일 삭제 실패");
				e.printStackTrace();
				return false;
			}
		}
		
		path = path.getParent();
		
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("FileManagerService-removeFile : 디렉토리 삭제 실패");
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
	
}
