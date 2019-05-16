package com.example.springboot_306;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("/")
    public String index(Model model) {
        //First let's create director
        Director director = new Director();
        director.setName("Stephen Bullock");
        director.setGenre("Sci fi");

        //Now let's create a movie
        Movie movie = new Movie();
        movie.setTitle("Star Movie");
        movie.setYear(2017);
        movie.setDescription("About space and future");

        //Add the movie to an empty list
        Set<Movie> movies = new HashSet<Movie>();
        movies.add(movie);

        movie = new Movie();
        movie.setTitle("DeathStar Works");
        movie.setYear(2019);
        movie.setDescription("About Ewoks and the Deathstar");
        movies.add(movie);

        //Add the list of movies to the director's movie list
        director.setMovies(movies);

        //Save the director to the database
        directorRepository.save(director);

        //Grab all the directors from the DBase and send to template
        model.addAttribute("directors", directorRepository.findAll());

        return "index";


    }
}
