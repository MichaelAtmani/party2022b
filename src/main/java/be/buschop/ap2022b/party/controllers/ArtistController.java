package be.buschop.ap2022b.party.controllers;


import be.buschop.ap2022b.party.model.Artist;
import be.buschop.ap2022b.party.model.Venue;
import be.buschop.ap2022b.party.repositories.ArtistRepository;
import be.buschop.ap2022b.party.repositories.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Controller
public class ArtistController {
    private final int mySpecialNumber = 35;




    @Autowired
    private ArtistRepository artistRepository;



    @GetMapping("/artistlist")
    public String artistList(Model model){
        Iterable<Artist> artists = artistRepository.findAll();
        model.addAttribute("artists", artists);
        return "artistlist";
    }

    @GetMapping({"/artistdetailsbyid","/artistdetailsbyid/","/artistdetailsbyid/{artistid}"})
    public String artistdetailsbyid(Model model, @PathVariable(required = false) String artistid){

        Optional oArtist = null;
        Artist artist = null;
        int artistCount = 0;

        artistCount = (int) artistRepository.count();

        oArtist = artistRepository.findById(Integer.parseInt(artistid));
        if(oArtist.isPresent()){
            artist = (Artist) oArtist.get();
        }

        int prevId = Integer.parseInt(artistid)-1;
        if(prevId<1){
            prevId = artistCount;
        }

        int nextId = Integer.parseInt(artistid)+1;
        if(nextId > artistCount)
        {
            nextId = 1;
        }

        model.addAttribute("artist", artist);
        model.addAttribute("prevIndex", prevId);
        model.addAttribute("nextIndex", nextId);
        return "artistdetailsbyid";
    }



}
