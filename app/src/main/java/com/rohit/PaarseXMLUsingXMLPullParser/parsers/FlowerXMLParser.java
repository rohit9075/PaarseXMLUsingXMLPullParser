package com.rohit.PaarseXMLUsingXMLPullParser.parsers;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.rohit.PaarseXMLUsingXMLPullParser.model.Flower;

public class FlowerXMLParser {

	public static List<Flower> parseFeed(String content) {
		
		try {
			
		    boolean inDataItemTag = false;
		    String currentTagName = "";
		    Flower flower = null;
		    List<Flower> flowerList = new ArrayList<Flower>();

		    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		    XmlPullParser parser = factory.newPullParser();
		    parser.setInput(new StringReader(content));

		    int eventType = parser.getEventType();

		    while (eventType != XmlPullParser.END_DOCUMENT) {

		        switch (eventType) {
		            case XmlPullParser.START_TAG:
		                currentTagName = parser.getName();
		                if (currentTagName.equals("product")) {
		                    inDataItemTag = true;
		                    flower = new Flower();
		                    flowerList.add(flower);
		                }
		                break;

		            case XmlPullParser.END_TAG:
		                if (parser.getName().equals("product")) {
		                    inDataItemTag = false;
		                }
		                currentTagName = "";
		                break;

		            case XmlPullParser.TEXT:
		                if (inDataItemTag && flower != null) {
							if ("productId".equals(currentTagName)) {
								flower.setProductId(Integer.parseInt(parser.getText()));

							} else if ("name".equals(currentTagName)) {
								flower.setName(parser.getText());

							} else if ("instructions".equals(currentTagName)) {
								flower.setInstructions(parser.getText());

							} else if ("category".equals(currentTagName)) {
								flower.setCategory(parser.getText());

							} else if ("price".equals(currentTagName)) {
								flower.setPrice(Double.parseDouble(parser.getText()));

							} else if ("photo".equals(currentTagName)) {
								flower.setPhoto(parser.getText());


							} else {
							}
		                }
		                break;
		        }

		       eventType = parser.next();

		    }

		    return flowerList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 

		
	}
	
}
