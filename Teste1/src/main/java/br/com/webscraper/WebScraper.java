package br.com.webscraper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class WebScraper {
	

	public void filesDownload(String url) {


		
		String baseUrl = url ;
		WebClient client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		try{
		HtmlPage page = client.getPage(baseUrl);
		List<HtmlAnchor> anchors = (List<HtmlAnchor>) page.getAnchors();


            File folder = new File("C:\\Anexos");
			folder.mkdirs();
			List<HtmlAnchor> anchorsImportants = new ArrayList<>();

			for ( HtmlAnchor element : anchors) {
				if(element.getHrefAttribute().endsWith(".pdf")){
					anchorsImportants.add(element);
				}
			}
			anchorsImportants.remove(0);
			anchorsImportants.remove(4);
			anchorsImportants.remove(4);


			System.out.println(anchorsImportants.size());

			String zipFolder = folder.getPath()+"\\"+"AnexosZipados.zip";
			String[] paths = {"Anexo1.pdf","Anexo2.pdf","Anexo3.pdf","Anexo4.pdf"};
			int countString = 0;
			URL url1 ;

			for (HtmlAnchor element: anchorsImportants) {
				url1 = new URL(element.getHrefAttribute());
				try (InputStream in = url1.openStream();
					 BufferedInputStream bis = new BufferedInputStream(in);
					 FileOutputStream fos = new FileOutputStream(folder.getPath()+"\\"+paths[countString])) {

					byte[] data = new byte[1024];
					int count;
					while ((count = bis.read(data, 0, 1024)) != -1) {
						fos.write(data, 0, count);
					}

					countString += 1;
				} catch(Exception e) {
					System.out.println(e);
				}
			}
			byte[] buffer = new byte[1024];

			FileOutputStream fos = new FileOutputStream(zipFolder);
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (int i = 0 ; i < paths.length; i++) {
				File srcFile = new File(folder.getPath()+"\\"+paths[i]);

				FileInputStream fis = new FileInputStream(srcFile);


				zos.putNextEntry(new ZipEntry(srcFile.getName()));

				int length;

				while ((length = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, length);
				}

				zos.closeEntry();


				fis.close();

			}

			zos.close();
		}
		catch(Exception e){
		e.printStackTrace();
		}
		
		client.close();

}
	
}
