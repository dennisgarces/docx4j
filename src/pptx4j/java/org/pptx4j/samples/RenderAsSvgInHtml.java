/*
 *  Copyright 2007-2008, Plutext Pty Ltd.
 *   
 *  This file is part of docx4j.

    docx4j is licensed under the Apache License, Version 2.0 (the "License"); 
    you may not use this file except in compliance with the License. 

    You may obtain a copy of the License at 

        http://www.apache.org/licenses/LICENSE-2.0 

    Unless required by applicable law or agreed to in writing, software 
    distributed under the License is distributed on an "AS IS" BASIS, 
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
    See the License for the specific language governing permissions and 
    limitations under the License.

 */

package org.pptx4j.samples;


import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.docx4j.XmlUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.PresentationMLPackage;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.PresentationML.SlidePart;
import org.pptx4j.convert.out.svginhtml.SvgExporter;
import org.pptx4j.model.ResolvedLayout;
import org.pptx4j.pml.GroupShape;



/**
 * @author jharrop
 *
 */
public class RenderAsSvgInHtml  {
	
	protected static Logger log = Logger.getLogger(RenderAsSvgInHtml.class);

	public static void main(String[] args) throws Exception {
	
		String inputfilepath = System.getProperty("user.dir") + "/sample-docs/pptx-otherparts.xml";
		
		PresentationMLPackage presentationMLPackage = 
			(PresentationMLPackage)PresentationMLPackage.load(new java.io.File(inputfilepath));		
		
		Iterator partIterator = presentationMLPackage.getParts().getParts().entrySet().iterator();
	    while (partIterator.hasNext()) {
	    	
	        Map.Entry pairs = (Map.Entry)partIterator.next();
	        
	        Part p = (Part)pairs.getValue();
	        if (p instanceof SlidePart) {
	        	ResolvedLayout rl = ((SlidePart)p).getResolvedLayout();	
	        	
	        	System.out.println( XmlUtils.marshaltoString(rl.getShapeTree(), false, true, Context.jcPML,
	        			"http://schemas.openxmlformats.org/presentationml/2006/main", "spTree", GroupShape.class) );
	        	
	        	SvgExporter.svg(presentationMLPackage, rl);
	        }
	    }
		
	}	
	
	
	
}