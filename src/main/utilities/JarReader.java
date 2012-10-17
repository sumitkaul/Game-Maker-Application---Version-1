package main.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import main.view.ImageFrame;

import org.apache.log4j.Logger;

public class JarReader {
	private String jarFile;
	private String fileName;
	private ArrayList<String> fileList;
	private ZipInputStream zipStream;
	private Logger log = Logger.getLogger(ImageFrame.class);
	public JarReader()
	{
		this.fileList = new ArrayList<String>();
	}
	
	public void setZipStream(String jarName)
	{
		this.zipStream = new ZipInputStream((getClass().getClassLoader().getResourceAsStream(jarName)));
		try {
			log.info(zipStream.getNextEntry().getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getAllEntries()
	{
		try {
			
			ZipEntry ze = null;

		    while((ze = this.zipStream.getNextEntry()) != null) {
		        String entryName = ze.getName();
		        if(entryName.endsWith(".png") || entryName.endsWith(".jpg") || entryName.endsWith(".gif")) {
		            log.info("Entry Name is " + entryName);
		        	fileList.add(entryName);
		        }
		    }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return fileList;

	}
	
	public ArrayList<String> getAllEntriesSounds()
	{
		try {
			
			ZipEntry ze = null;

		    while((ze = this.zipStream.getNextEntry()) != null) {
		        String entryName = ze.getName();
		        if(entryName.endsWith(".wav") || entryName.endsWith(".au") || entryName.endsWith(".mp3")) {
		            log.info("Entry Name is " + entryName);
		        	fileList.add(entryName);
		        }
		    }
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return fileList;

	}
	
	public String getFileEntry(String fileName)
	{
			try {
			
			ZipEntry ze = null;
		    while((ze = this.zipStream.getNextEntry()) != null) {
		        String entryName = ze.getName();
		        if (entryName.split("/")[1].equals(fileName.split("/")[1]))
		        {
		        	log.info("Entry Name is single file " + entryName);
		        	return entryName;
		        }
		    }
			}catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
	}
	public String getJarFile() {
		return jarFile;
	}
	public void setJarFile(String jarFile) {
		this.jarFile = jarFile;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public ArrayList<String> getFileList() {
		return fileList;
	}
	public void setFileList(ArrayList<String> fileList) {
		this.fileList = fileList;
	}

	public ZipInputStream getZipStream() {
		return zipStream;
	}

	public void setZipStream(ZipInputStream zipStream) {
		this.zipStream = zipStream;
	}
	

}
