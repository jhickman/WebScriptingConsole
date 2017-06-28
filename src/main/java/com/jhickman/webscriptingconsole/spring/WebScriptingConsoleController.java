package com.jhickman.webscriptingconsole.spring;

import com.jhickman.webscriptingconsole.core.WebScriptingConsoleService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Adapts Spring controller to the groovy service
 */
@Controller("webScriptingConsoleController")
@RequestMapping("/web-scripting-console")
public class WebScriptingConsoleController {

  @Autowired
  private WebScriptingConsoleService service;

  @RequestMapping(value = "/editor", produces = "text/html")
  public void editor(HttpServletRequest request, HttpServletResponse response) {
    service.editor(request, response);
  }
}
