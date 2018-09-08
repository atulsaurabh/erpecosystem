package com.dlinkddns.atulsaurabh.erpecosystem.loader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import com.dlinkddns.atulsaurabh.erpecosystem.util.ErpUtility;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.dlinkddns.atulsaurabh.hasselfreelogger.api.Logger;

public class GraphicsSupplier 
{
	
	@Autowired
	private ErpUtility erpUtility;
	
	@Autowired
	private Logger logger;

	public final ImageView iconGraphics(String iconKey)
	{
		String fileName = erpUtility.resolvKey(iconKey);
		URL url =this.getClass().getResource(GUIInfo.IMAGE_HOME+fileName);
		ImageView imageView = new ImageView(getGraphics(url));
    	imageView.setFitWidth(20);
    	imageView.setFitHeight(20);
		return imageView;
	}
	
	public final ImageView iconGraphicsWithFileName(String iconFileName)
	{
		URL url =this.getClass().getResource(GUIInfo.IMAGE_HOME+iconFileName);
		ImageView imageView = new ImageView(getGraphics(url));
    	imageView.setFitWidth(20);
    	imageView.setFitHeight(20);
		return imageView;
	}
	
	public final ImageView iconGraphics(String iconHome,String iconKey)
	{
		String fileName = erpUtility.resolvKey(iconKey);
		File file = new File(iconHome+"/"+fileName);
		try {
			URL url = file.toURI().toURL();
			ImageView imageView = new ImageView(getGraphics(url));
	    	imageView.setFitWidth(20);
	    	imageView.setFitHeight(20);
			return imageView;
		}
		catch(MalformedURLException malformedURLException)
		{
			logger.logWarning(malformedURLException.getMessage());
		}
		return null;
	}
	
	public final ImageView iconGraphicsWithFileName(String iconHome,String iconFileName)
	{
		File file = new File(iconHome+"/"+iconFileName);
		try {
			URL url = file.toURI().toURL();
			ImageView imageView = new ImageView(getGraphics(url));
	    	imageView.setFitWidth(20);
	    	imageView.setFitHeight(20);
			return imageView;
		}
		catch(MalformedURLException malformedURLException)
		{
			logger.logWarning(malformedURLException.getMessage());
		}
		return null;
	}
	
	private final Image getGraphics(URL url)
	{
		return new Image(url.toExternalForm());
	}
}
