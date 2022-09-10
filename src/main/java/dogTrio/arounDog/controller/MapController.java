package dogTrio.arounDog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;

@Controller

public class MapController {

    //https://navermaps.github.io/maps.js.ncp/docs/tutorial-tile-index.example.html
    //https://navermaps.github.io/maps.js.ncp/docs/tutorial-2-Getting-Started.html
    @GetMapping("/map")
    public String getTileIndex(@Valid @RequestParam Double latitude, @Valid @RequestParam Double longitude, Model model) throws IOException {
        model.addAttribute("latitude", latitude);
        model.addAttribute("longitude", longitude);
        return "location";
    }


}
