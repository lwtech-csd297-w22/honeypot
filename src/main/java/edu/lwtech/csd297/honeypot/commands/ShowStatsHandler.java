package edu.lwtech.csd297.honeypot.commands;

import java.io.*;
import java.util.*;
import javax.servlet.http.*;

import freemarker.template.*;
import org.apache.logging.log4j.*;

import edu.lwtech.csd297.honeypot.*;

public class ShowStatsHandler implements ServletCommand {

    private static final Logger logger = LogManager.getLogger(ShowStatsHandler.class);
    
    // Handle the "showStats" command
    @Override
    public String handle(HttpServletRequest request) {

        String templateFile = "stats.ftl";
        Map<String, Object> templateFields = new HashMap<>();

        // ADD THE IP HIT COUNTERS MAP TO templateFields

        // Merge the template file with the data in templateFields to create our HTML output
        StringWriter processedTemplate = new StringWriter();
        try {
            Template view = HoneypotServlet.getFreeMarkerConfig().getTemplate(templateFile);
            view.process(templateFields, processedTemplate);
        } catch (IOException | TemplateException e) {
            logger.error("Unexpected error processing Freemarker template: {}", templateFile, e);
        }

        // Return the HTML output as a String
        return processedTemplate.toString();
    }

}
