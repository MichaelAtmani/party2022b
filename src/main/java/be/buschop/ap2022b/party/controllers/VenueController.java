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
public class VenueController {
    private final int mySpecialNumber = 35;

    private final Venue [] venues = {
            new Venue(1,"Bobbejaanland", "http://www.bobbejaanland.be", true, false, false, true),
            new Venue(2,"Plopsa", "http://www.plopsa.be", false, true, true, true),
            new Venue(3,"Walibi", "http://www.walibi.be", false, false, true, true),
            new Venue(4,"Legoland", "https://www.legoland.de/en/", false, false, true, true),
            new Venue(5,"Phantasialand", "https://www.phantasialand.de/en/", false, true, true, true),
            new Venue(6,"Efteling", "https://www.efteling.nl", false, true, true, true)
    };

    @Autowired
    private VenueRepository venueRepository;



    @GetMapping("/venuelist")
    public String venuelist (Model model){
        Iterable<Venue> venues = venueRepository.findAll();
        model.addAttribute("venues",venues);
        return "venuelist";
    }

    @GetMapping("/venuelist/hasgoodfood/{foodvalue}")
    public String venuelistHasGoodFoodYes (Model model,@PathVariable Optional<String> foodvalue){
        boolean food = false;
        Integer myFood = 2;
        Iterable<Venue> venueList = venueRepository.findAll();
        String test="test";

        if(foodvalue.isPresent())
        {
            test="ispresent";
            myFood = Integer.parseInt(foodvalue.get());
        }


        switch(myFood) {
            case 0:
                venueList = venueRepository.findByHasGoodFood(false);
                break;
            case 1:
                venueList = venueRepository.findByHasGoodFood(true);
                break;
            default:
                // code block
        }

        model.addAttribute("venues",venueList);
        model.addAttribute("food",myFood);
        return "venuelist";
    }

    @GetMapping("/venuelist/hasfreeparking/{parkingvalue}")
    public String venuelistHasFreeParking (Model model,@PathVariable Optional<String> parkingvalue){
        boolean parking = false;
        Integer myParking = 2;
        Iterable<Venue> venueList = venueRepository.findAll();


        if(parkingvalue.isPresent())
        {
            myParking = Integer.parseInt(parkingvalue.get());
        }


        switch(myParking) {
            case 0:
                venueList = venueRepository.findByHasFreeParking(false);
                break;
            case 1:
                venueList = venueRepository.findByHasFreeParking(true);
                break;
            default:
                // code block
        }

        model.addAttribute("venues",venueList);
        model.addAttribute("parking",myParking);
        return "venuelist";
    }

    @GetMapping("/venuelist/toddlerfriendly/{toddlervalue}")
    public String venuelistToddlerFriendly (Model model,@PathVariable Optional<String> toddlervalue){
        boolean toddler = false;
        Integer myToddler = 2;
        Iterable<Venue> venueList = venueRepository.findAll();


        if(toddlervalue.isPresent())
        {
            myToddler = Integer.parseInt(toddlervalue.get());
        }


        switch(myToddler) {
            case 0:
                venueList = venueRepository.findByAndToddlerFriendly(false);
                break;
            case 1:
                venueList = venueRepository.findByAndToddlerFriendly(true);
                break;
            default:
                // code block
        }

        model.addAttribute("venues",venueList);
        model.addAttribute("toddler",myToddler);
        return "venuelist";
    }



    @GetMapping({"/venuedetails","/venuedetails/","/venuedetails/{venuename}"})
    public String venuedetails(Model model, @PathVariable(required = false) String venuename){
        model.addAttribute("venuename",venuename);
        return "venuedetails";
    }

    @GetMapping({"/venuedetailsbyid","/venuedetailsbyid/","/venuedetailsbyid/{venueid}"})
    public String venuedetailsbyid(Model model, @PathVariable(required = false) String venueid){

        Optional oVenue = null;
        Venue venue = null;
        int venueCount = 0;

        venueCount = (int) venueRepository.count();

        try {
            oVenue = venueRepository.findById(Integer.parseInt(venueid));
        } catch (NumberFormatException e) {
            return null;
        }

        //oVenue = venueRepository.findById(Integer.parseInt(venueid));
        if(oVenue.isPresent()){
            venue = (Venue) oVenue.get();
        }

        int prevId = Integer.parseInt(venueid)-1;
        if(prevId<1){
            prevId = venueCount;
        }

        int nextId = Integer.parseInt(venueid)+1;
        if(nextId > venueCount)
        {
            nextId = 1;
        }

        model.addAttribute("venue", venue);
        model.addAttribute("prevIndex", prevId);
        model.addAttribute("nextIndex", nextId);
        return "venuedetailsbyid";
    }

    @GetMapping({"/venuedetailsbyindex","/venuedetailsbyindex/","/venuedetailsbyindex/{venueindex}"})
    public String venuedetailsbyindex(Model model, @PathVariable(required = false) String venueindex){

        Venue venue = null;

        if(venueindex !=null && Integer.parseInt(venueindex)%1 == 0 && Integer.parseInt(venueindex)>= 0 && Integer.parseInt(venueindex)< venues.length )
        {
            //get venue object
            venue = venues[Integer.parseInt(venueindex)];
        }

        int prevIndex = Integer.parseInt(venueindex)-1;
        if(prevIndex<0){
            prevIndex = venues.length - 1;
        }

        int nextIndex = Integer.parseInt(venueindex)+1;
        if(nextIndex >venues.length-1)
        {
            nextIndex = 0;
        }

        model.addAttribute("venue",venue);
        model.addAttribute("prevIndex", prevIndex);
        model.addAttribute("nextIndex", nextIndex);
        return "venuedetailsbyindex";
    }

}
