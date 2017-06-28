package com.jhickman.webscriptingconsole.core.impl

import com.jhickman.webscriptingconsole.core.WebScriptingConsoleService
import groovy.text.SimpleTemplateEngine

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class WebScriptingConsoleServiceImpl implements WebScriptingConsoleService {

  private static
  final String SCRIPT_PATH = System.getProperty("user.home").concat("/webScriptingConsole.groovy");

  @Override
  void editor(HttpServletRequest request, HttpServletResponse response) {


    def script = new File(SCRIPT_PATH)
    def scriptText = script.text.replaceAll('(?s)IGNORE_START.*IGNORE_END', '')

    def groovyShell = new GroovyShell(new Binding([request: request, response: response]))
    def hasError = false
    def output = ''
    try {
      output = groovyShell.evaluate(scriptText)
    } catch (Exception e) {
      def sw = new StringWriter()
      e.printStackTrace(new PrintWriter(sw))
      output = sw.toString()
      hasError = true
    }

    response.setContentType("text/html")

    def engine = new SimpleTemplateEngine()
    def template = engine.createTemplate(getClass().getResource('editor.tmpl')).make([output: output, hasError: hasError])

    response.writer.println(template.toString())
    response.flushBuffer()
  }
}
