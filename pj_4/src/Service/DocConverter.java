package Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class DocConverter {
	private static final int environment = 1;
	private static String fileString;
	private static String outputPath = "";
	private static String fileName;
	private File pdfFile;
	private File swfFile;
	private File docFile;

	public DocConverter(String fileString) {
		ini(fileString);
	}

	public void setFile(String fileString) {
		ini(fileString);
	}

	private void ini(String fileString) {
		this.fileString = fileString;
		fileName = fileString.substring(0, fileString.lastIndexOf("."));
		System.out.println("fileName:" + fileName);
		System.out.println("fileString" + fileString);
		docFile = new File(fileString);
		pdfFile = new File(fileName + ".pdf");
		swfFile = new File(fileName + ".swf");
	}

	private void doc2pdf() throws Exception {
		if (docFile.exists()) {
			if (!pdfFile.exists()) {
				OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
				try {
					connection.connect();
					DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
					converter.convert(docFile, pdfFile);
					// close the connection
					connection.disconnect();
				} catch (java.net.ConnectException e) {
					// ToDo Auto-generated catch block
					e.printStackTrace();
					throw e;
				} catch (com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException e) {
					e.printStackTrace();
					throw e;
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				}
			} else {
			}
		} else {
		}
	}

	@SuppressWarnings("unused")
	private void pdf2swf() throws Exception {
		Runtime r = Runtime.getRuntime();
		if (!swfFile.exists()) {
			if (pdfFile.exists()) {
				if (environment == 1)
				{
					try {
			
						String fileOne = "C:\\Users\\qiu yongting\\java_openSource\\SWFTools\\pdf2swf.exe ";
						
						String pdfFileString = "\""+pdfFile.getPath()+"\"";
						String swfFileString = "\""+swfFile.getPath()+"\"";
						String command = fileOne+ pdfFileString
						+ " -o " + swfFileString + " -T 9";
						
						Process p = r.exec(command);
						System.out.println(loadStream(p.getInputStream()));
						System.err.println(loadStream(p.getErrorStream()));
						System.out.println(loadStream(p.getInputStream()));
						System.err.println(swfFile.getPath() + "****");
//                        if (pdfFile.exists()) {  
//                            pdfFile.delete();  
//                        }  
					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}
				} else if (environment == 2)
				{
					try {
						Process p = r.exec("pdf2swf " + pdfFile.getPath() + " -o " + swfFile.getPath() + " -T 9");
						System.out.print(loadStream(p.getInputStream()));
						System.err.print(loadStream(p.getErrorStream()));
						if (pdfFile.exists()) {
							pdfFile.delete();
						}
					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}
				}
			} else {
			}
		} else {
		}
	}

	static String loadStream(InputStream in) throws IOException {
		int ptr = 0;
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder buffer = new StringBuilder();
		while ((ptr = reader.read()) != -1) {
			buffer.append((char) ptr);
		}
		return buffer.toString();
	}

	@SuppressWarnings("unused")
	public boolean conver() {
		if (swfFile.exists()) {
			return true;
		}

		if (environment == 1) {
		} else {
		}

		try {
			doc2pdf();
			pdf2swf();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		if (swfFile.exists()) {
			return true;
		} else {
			return false;
		}
	}

	public String getswfPath() {
		if (swfFile.exists()) {
			String tempString = swfFile.getPath();
			tempString = tempString.replaceAll("\\\\", "/");
			return tempString;
		} else {
			return "";
		}
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
		if (!outputPath.equals("")) {
			String realName = fileName.substring(fileName.lastIndexOf("/"), fileName.lastIndexOf("."));
			if (outputPath.charAt(outputPath.length()) == '/') {
				swfFile = new File(outputPath + realName + ".swf");
			} else {
				swfFile = new File(outputPath + realName + ".swf");
			}
		}
	}

	public static void main(String s[]) {
		DocConverter d = new DocConverter("C:\\Users\\qiu yongting\\Desktop\\PPT\\05-Loops\\05slide.ppt");
		d.conver();
		System.out.println(fileString);
		System.out.println(fileName);
		System.out.println(outputPath);
		System.out.println("end");

	}
}
