package org.jxkh.util.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ExportToExcelController {
   
	
    @RequestMapping(value = "/admin/exportExcelGrid")  
    public String exportExcelGrid(ModelMap model,HttpServletRequest request,HttpServletResponse response) {  
    	 
        PrintWriter out;
        try {
            out = response.getWriter();
            response.setHeader("Content-Type","application/force-download");
            response.setHeader("Content-Type","application/vnd.ms-excel");
            response.setHeader("Content-Disposition","attachment;filename=export.xls");
            out.print(request.getParameter("exportContent"));
 
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
        return null;
 
    }  
   
}
