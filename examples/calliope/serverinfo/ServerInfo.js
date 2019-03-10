

// Create a static object for the plugin.
ServerInfo = {};

// Define the callback function after the request to the handler is made
// status: The HTTP-status of the response
// response: The content of the response
ServerInfo.exec = function(status, response) {
	alert(response);
}


// Now register the tool
Abbozza.registerPluginTool("serverinfo", "serverinfotool", "/abbozza/plugins/serverinfo/info.png", ServerInfo.exec);
