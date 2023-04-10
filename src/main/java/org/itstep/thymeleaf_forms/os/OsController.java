package org.itstep.thymeleaf_forms.os;

import org.itstep.thymeleaf_forms.firm.Firm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class OsController {
    @Autowired
    private OsService OsService;

    @GetMapping(value = "/oss")
    public String oss(Model model){
        model.addAttribute("oss", OsService.findAll());
        return "oss";
    }

    @GetMapping(value = "/os_add")
    public String osAdd(Model model){
        model.addAttribute("os", new Os());
        return "os_add";
    }
    @PostMapping(value = "/os_add")
    public String osSave(Os os, Model model, HttpServletResponse response){
       Os newOs = OsService.save(os);
       long id = newOs.getId();
       response.addHeader("id",String.valueOf(id));
       model.addAttribute("oss", OsService.findAll());
       return "redirect:/oss";
    }

    @DeleteMapping(value = "/os_delete")
    public String osDelete(@RequestParam(name="id") Long id){
        OsService.deleteById(id);
        return "redirect:/oss";
    }
    @PutMapping(value = "/os_update")
    public String osUpdate(Os os, Model model){
        Os oldOs = OsService.findById(os.getId());
        oldOs.setName(os.getName());
        oldOs.setDeveloper(os.getDeveloper());
        OsService.save(oldOs);
        model.addAttribute("oss", OsService.findAll());
        return "redirect:/oss";
    }
    @GetMapping(value = "/os_update")
    public String osGetUpdate(Model model, @RequestParam(name="id") Long id){
        Os oldOs = OsService.findById(id);
        model.addAttribute("os", oldOs);
        return "os_update";
    }
}
