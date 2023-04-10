package org.itstep.thymeleaf_forms.smart;

import org.itstep.thymeleaf_forms.firm.Firm;
import org.itstep.thymeleaf_forms.firm.FirmService;
import org.itstep.thymeleaf_forms.os.OsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
public class SmartController {
    @Autowired
    private SmartService smartService;
    @Autowired
    private FirmService firmService;
    @Autowired
    private OsService osService;


    @GetMapping(value = "/smarts")
    public String smarts(Model model){
        model.addAttribute("smarts", smartService.findAll());
        return "smarts";
    }

    @GetMapping(value = "/smart_add")
    public String smartAdd(Model model){
        model.addAttribute("smart", new Smart());
        model.addAttribute("firms", firmService.findAll());
        model.addAttribute("firms", osService.findAll());
        return "smart_add";
    }
    @PostMapping(value = "/smart_add")
    public String firmSave(Firm firm, Model model, HttpServletResponse response){
       Firm newFirm = firmService.save(firm);
       long id = newFirm.getId();
       response.addHeader("id",String.valueOf(id));
       model.addAttribute("firms", firmService.findAll());
       return "redirect:/firms";
    }

    @DeleteMapping(value = "/firm_delete")
    public String firmDelete(@RequestParam(name="id") Long id){
        firmService.deleteById(id);
        return "redirect:/firms";
    }
    @PutMapping(value = "/firm_update")
    public String firmUpdate(Firm firm, Model model){
        Firm oldFirm = firmService.findById(firm.getId());
        oldFirm.setName(firm.getName());
        firmService.save(oldFirm);
        model.addAttribute("firms", firmService.findAll());
        return "redirect:/firms";
    }
    @GetMapping(value = "/firm_update")
    public String firmGetUpdate(Model model, @RequestParam(name="id") Long id){
        Firm oldFirm = firmService.findById(id);
        model.addAttribute("firm", oldFirm);
        return "firm_update";
    }
}
