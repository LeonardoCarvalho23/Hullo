package com.hullo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

@Controller
public class ErrorController {
 
    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public String renderErrorPage(HttpServletRequest httpRequest, Model model) {
         System.out.println("Chegou no error controler");
        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);
        System.out.println("o httperrorcode � "+httpErrorCode);
        switch (httpErrorCode) {
            case 400: {
                errorMsg = "C�digo de erro: 400. Requisi��o inv�lida.";
                break;
            }
            case 401: {
                errorMsg = "C�digo de erro: 401. Voc� n�o tem permiss�o.";
                break;
            }
            case 404: {
                errorMsg = "C�digo de erro: 404. P�gina n�o encontrada.";
                break;
            }
            case 500: {
                errorMsg = "C�digo de erro: 500. Erro interno do servidor.";
                break;
            }
        }
        model.addAttribute("errorMsg", errorMsg);
        return "errorPage";
    }
     
    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
          .getAttribute("javax.servlet.error.status_code");
    }
}
