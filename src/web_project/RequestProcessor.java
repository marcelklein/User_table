package web_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RequestProcessor {
	public static String serverRoot;
	public static String baseUrl;
	
	enum Operation{
		save, remove, update, query
	}
	
	public static JSONObject saveData(JSONObject ji) throws Exception {

		String b64img = ji.getString("image");
		System.out.println(b64img);
		String fileName = ji.getString("filename"); //programm bricht hier ab
		String filePath = serverRoot + File.separator + "files" + File.separator + fileName;
		JSONObject jo = new JSONObject();
		
		File f = new File(filePath);
		if(!f.exists()) {
			FileUtils.forceMkdir(new File(f.getParentFile().getAbsolutePath()));
		}

		// Decode a previously encoded string using decodeBase64 method and
		// passing the byte[] of the encoded string.
		byte[] data = Base64.getDecoder().decode(b64img.getBytes()); // whats wrong?
		

		try{
			OutputStream stream = new FileOutputStream(f);
		    stream.write(data);
		    stream.close();
		    jo.put("ServerSidePath", f.getAbsolutePath());
		    jo.put("ImageUrl", baseUrl + "/files/" + fileName);
		    try {
		    	Process p = Runtime.getRuntime().exec("chmod 777 \"" + filePath + "\"");
		    	p.waitFor();
		    }catch(Exception e) {
		    	// do nothing
		    }
			
		    return jo;
		}catch(Exception e){
			throw(e);
		}
		
	}

	public static void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String op = request.getHeader("operation");
		if(op == null)
			throw new Exception("Operation not set in headers");
		
		JSONObject ji = getJSON(request);
		JSONObject jo = new JSONObject();
		Operation o = Operation.valueOf(op);
		switch(o) {
		case save:
				baseUrl = getBaseUrl(request);
				jo = saveData(ji);
			break;
		default:
				
			break;
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jo.toString());
	}
	
	public static JSONObject getJSON(HttpServletRequest request) throws IOException, JSONException {
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
		JSONObject jo = null;
		String rtext="";
		String rline;
		while((rline = br.readLine())!=null) {
			rtext+=rline;
		}
		
		if(!rtext.isEmpty()) { 
    		jo = new JSONObject(rtext);
    	}
		
		return jo;
	}
	
	public static String getBaseUrl(HttpServletRequest request) {
	    String scheme = request.getScheme() + "://";
	    String serverName = request.getServerName();
	    String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
	    String contextPath = request.getContextPath();
	    return scheme + serverName + serverPort + contextPath;
	  }
}
