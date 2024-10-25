package hvl.dat108.controller;

import hvl.dat108.model.Deltager; // Import av Deltager-modellen

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/fest")
public class FestController{

    private List<Deltager> deltagerListe = new ArrayList<>();

    // Hardkodede deltagere
    public FestController() {
        deltagerListe.add(new Deltager("12345678", "Test123", "Ola", "Nordmann", "mann"));
        deltagerListe.add(new Deltager("87654321", "Test123", "Kari", "Nordmann", "kvinne"));
    }

    @GetMapping("/paamelding")
    public String paamelding_med_melding(Model model) {
        model.addAttribute("deltager", new Deltager());
        return "paamelding_med_melding"; 
    }

    @PostMapping("/paamelding_med_melding")
    public String registrerDeltager(@ModelAttribute Deltager deltager, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "paamelding_med_melding";
        }

        for (Deltager d : deltagerListe) {
            if (d.getMobil().equals(deltager.getMobil())) {
                model.addAttribute("error", "Deltager med dette mobilnummeret er allerede påmeldt.");
                return "paamelding_med_melding";
            }
        }

        deltagerListe.add(deltager);
        return "redirect:/deltagerliste"; 
    }

    @GetMapping("/deltagerliste")
    public String visDeltagerliste(Model model) {
        model.addAttribute("deltagerListe", deltagerListe);
        return "deltagerliste"; 
    }
}

