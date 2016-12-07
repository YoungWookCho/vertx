package com.hanbit.vertx;



import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class Server extends AbstractVerticle {
	
	public static void main(String[] args) {
		Vertx vertx =Vertx.vertx();
		vertx.deployVerticle(new Server());
		
	}
	
	/* (non-Javadoc)
	 * @see io.vertx.core.AbstractVerticle#start()
	 */
	@Override
	public void start() throws Exception {
		vertx.createHttpServer().requestHandler(req->{
			
			String uri = req.uri();
			
			System.out.println(uri);
			String[] uris = StringUtils.split(uri,"/");
			String result = "Hello!";
			
			
			
			if (uris.length == 3 && "plus".equals(uris[0]) ){

				int x = Integer.parseInt(uris[1]);
				int y = Integer.parseInt(uris[2]);
				
				
				
				result = "<b>Sum: </b><span style = 'color: 66ff66;'>"+(x+y)+"</span>";
				
			}
			
			
			
			/*
			 * Get방식
			 * URL : http://www.naver.com/hello/index.nhn?id=hanbit&no=123
			 * Protocal : http
			 * Port : 80
			 * URI : /hello/index.nhn
			 * QueryString : id=hanbit&no=123
			 * Request Parameter Name : id,no
			 * Request Parameter Value : hanbit, 123
			 * 			 */

			else if (uris.length == 2 && "image".equals(uris[0]) ){
				String imgName=uris[1];
				String path = "C:/Users/Public/Pictures/Sample Pictures/"+imgName+".jpg";
	
					req.response()
					.putHeader("content-type", "image/jpeg")
					.sendFile(path);
				return;
				
			}

			
			else if ("/html".equals(uri)){
				String[] imgNames = {"Chrysanthemum","Desert","Hydrangeas","jellyfish"};
				
				result = "<div>";
				for (String imgName : imgNames){
					result+= "<img src= '/image/"+imgName +"' style= 'width:200px'><br>'";
				}
				result += "</div>";
				
			}
			
			
			req.response()
			.putHeader("content-type", "text/html")
			.end(result);
			
		}).listen(8080);
		
		
	}
	
	
}
