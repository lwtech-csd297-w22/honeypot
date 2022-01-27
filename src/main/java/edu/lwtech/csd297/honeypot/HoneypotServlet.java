package edu.lwtech.csd297.honeypot;

import java.io.*;
import java.util.*;
import java.nio.file.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import freemarker.template.*;
import org.apache.logging.log4j.*;

import edu.lwtech.csd297.honeypot.commands.*;

// Honeypot Servlet -
//      http://<server>:8080/honeypot/servlet
//
// <Your Name>
// LWTech CSD297

@WebServlet(name = "honeypot", urlPatterns = {"/servlet"}, loadOnStartup = 0)
public class HoneypotServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(HoneypotServlet.class);

    private static Configuration freeMarkerConfig = null;
    private static final Map<String, ServletCommand> supportedCommands = new HashMap<>();
    private static final Map<String, Integer> ipHitCounters = new HashMap<>();
    private static String apacheIndexHtml = "";

    @Override
    public void init(ServletConfig config) throws UnavailableException {
        logger.warn("=======  Honeypot SERVLET INIT ========");

        // INITIALIZE FREEMARKER

        // USING Files.readAllBytes(), READ THE CONTENTS OF apache-index.html INTO THE apacheIndex STRING

        // Load the supported commands into the Handler map
        supportedCommands.put("showStats", new ShowStatsHandler());
    }

    @Override
    public void destroy() {
        logger.warn("------  Honeypot SERVLET DESTROY ------");
        logger.warn("");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        // START THE TIMER AND LOG THE "IN" MESSAGE

        String htmlPage = "";

        // IF A SUPPORTED cmd PARAMETER WAS USED, GET THE HTML PAGE FROM ITS handle() METHOD

            // OTHERWISE, GET THE IP ADDRESS, METHOD, URI, USER-AGENT, AND QUERY FROM THE REQUEST OBJECT
    
            // LOG THAT INFORMATION
    
            // INCREMENT THE HIT COUNTER FOR THE IP ADDRESS
    
            // USE THE CONTENTS OF THE APACHE INDEX PAGE AS THE HTML PAGE TO SEND BACK

        // Send the HTML response to the user 
        try (ServletOutputStream out = response.getOutputStream()) {
            out.println(htmlPage);
        } catch (IOException e) {
            logger.error("Unexpected IO Error sending response.", e);
        }

        // STOP THE TIMER AND LOG THE "OUT" MESSAGE
    }

    // Allows ServletCommand handlers to access Freemarker
    public static Configuration getFreeMarkerConfig() {
        return freeMarkerConfig;
    }

    // Allows ServletCommand handlers to access the IP Hit Counters
    public static Map<String, Integer> getIpHitCounters() {
        return ipHitCounters;
    }

}
