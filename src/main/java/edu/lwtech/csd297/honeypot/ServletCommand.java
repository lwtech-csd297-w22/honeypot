package edu.lwtech.csd297.honeypot;

import javax.servlet.http.*;

public interface ServletCommand {
    String handle(HttpServletRequest request);    
}
