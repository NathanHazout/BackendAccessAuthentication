/*
 *  Licensed Materials - Property of IBM
 *  5725-I43 (C) Copyright IBM Corp. 2011, 2013. All Rights Reserved.
 *  US Government Users Restricted Rights - Use, duplication or
 *  disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */

/*
 * Your backend can call the prodecure using: 
 * http://www-01.ibm.com/support/knowledgecenter/SSHS8R_6.3.0/com.ibm.worklight.dev.doc/devref/c_adapter_invocation_service.html
 */

function doAction(data){
	//Here do something with the data received by the backend
	//A classic example is Push Notifications
	
	WL.Logger.info("doAction was called with data = " + data);
	//Don't forget to set the correct console log level in the server properties
}