/**
 * @license
 * abbozza!
 *
 * Copyright ???? <AUTOR>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import de.uos.inf.did.abbozza.plugin.*;
import de.uos.inf.did.abbozza.core.*;
import de.uos.inf.did.abbozza.handler.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

/**
 * This PluginHandler answers any request with basic informations about the server it is running on.
 */

public class ServerInfoHandler extends PluginHandler {

    public ServerInfoHandler() {
        super();
    }

   /**
    * Here the actual request is handled.
    */
   protected void handleRequest(HttpExchange exchg) throws IOException {
   	  // Get the requested path
        String path = exchg.getRequestURI().getPath();

        // Get the local addresses
        InetAddress inet = InetAddress.getLocalHost();
        InetSocketAddress socket = exchg.getLocalAddress();

		  // Build the response
        String response = "Name: " + inet.getHostName() + "\n";
        response = response + "Address: " + inet.getHostAddress() + "\n";
        response = response + "Port: " + socket.getPort() + "\n";
        response = response + "Requested path: " + path;

        // Send the response
        AbbozzaLogger.err(response);
        sendResponse(exchg,200, "text/plain",response);
    }

}
