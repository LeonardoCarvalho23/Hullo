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
        System.out.println("o httperrorcode é "+httpErrorCode);
        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Código de erro: 400. Requisição inválida.";
                break;
            }
            case 401: {
                errorMsg = "Código de erro: 401. Você não tem permissão.";
                break;
            }
            case 404: {
                errorMsg = "Código de erro: 404. Página não encontrada.";
                break;
            }
            case 500: {
                errorMsg = "Código de erro: 500. Erro interno do servidor.";
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
