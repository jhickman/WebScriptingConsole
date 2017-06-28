package com.jhickman.webscriptingconsole.core

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


interface WebScriptingConsoleService {
  void editor(HttpServletRequest request, HttpServletResponse response)
}