
import model.*;
import repository.*;
import service.*;
import strategy.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookMyShowApplication {

    public static void main(String[] args) {
        CityRepository cityRepo = new CityRepository();
        TheatreRepository theatreRepo = new TheatreRepository();
        MovieRepository movieRepo = new MovieRepository();
        ShowRepository showRepo = new ShowRepository();
        BookingRepository bookingRepo = new BookingRepository();

        ShowService showService = new ShowService(showRepo);
        TheatreService theatreService = new TheatreService(theatreRepo);
        MovieService movieService = new MovieService(movieRepo, showService);
        PricingStrategy pricingStrategy = new BasePricingStrategy();
        BookingService bookingService = new BookingService(bookingRepo, pricingStrategy);

        City cityBangalore = new City("C1", "Bangalore", "Karnataka");
        cityRepo.addCity(cityBangalore);

        Theatre theatre1 = new Theatre("T1", "PVR Koramangala", cityBangalore);
        theatreService.addTheatre(theatre1);

        Screen screen1 = new Screen("S1", "Audi-1", theatre1);
        for (int i = 1; i <= 5; i++) {
            screen1.addSeat(new Seat("Seat-" + i, 1, i, SeatType.SILVER));
        }
        for (int i = 6; i <= 10; i++) {
            screen1.addSeat(new Seat("Seat-" + i, 2, i - 5, SeatType.PLATINUM));
        }
        theatreService.addScreenToTheatre("T1", screen1);

        Movie movie1 = new Movie("M1", "Inception", "English", "Sci-Fi", 148);
        Movie movie2 = new Movie("M2", "Interstellar", "English", "Sci-Fi", 169);
        movieService.addMovie(movie1);
        movieService.addMovie(movie2);

        Show show1 = new Show("Show-1", movie1, screen1, LocalDateTime.now(), LocalDateTime.now().plusHours(3));
        showService.addShow(show1);
        System.out.println("Admin added Show 1");
        
        Show show2 = new Show("Show-2", movie2, screen1, LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(7));
        showService.addShow(show2);
        System.out.println("Admin added Show 2");

        System.out.println("\n--- Query APIs ---");
        List<Theatre> bglrTheatres = theatreService.showTheatres(cityBangalore);
        System.out.println("Theatres in Bangalore: ");
        bglrTheatres.forEach(t -> System.out.println(t.getName()));

        Set<Movie> moviesBglr = movieService.showMoviesInCity(cityBangalore);
        System.out.println("Movies in Bangalore: ");
        moviesBglr.forEach(m -> System.out.println(m.getTitle()));

        Set<Theatre> inceptionTheatres = showService.getTheatresShowingMovie(movie1, cityBangalore);
        System.out.println("Theatres showing Inception in Bangalore: ");
        inceptionTheatres.forEach(t -> System.out.println(t.getName()));

        System.out.println("\n--- Booking API Demo ---");
        User user1 = new User("U1", "Alice", "alice@example.com");
        User user2 = new User("U2", "Bob", "bob@example.com");

        Show targetShow = show1;
        List<ShowSeat> availableSeats = showService.getShowSeats(targetShow.getId());
        List<ShowSeat> seatsToBook = new ArrayList<>();
        seatsToBook.add(availableSeats.get(0));
        seatsToBook.add(availableSeats.get(5));

        Runnable bookTask1 = () -> {
            Booking b = bookingService.bookTickets(user1, targetShow, seatsToBook);
            if (b != null) {
                System.out.println("User 1 Successfully booked! Booking ID: " + b.getId() + " Price: " + b.getTotalPrice());
            } else {
                System.out.println("User 1 Failed to book seats.");
            }
        };

        Runnable bookTask2 = () -> {
            Booking b = bookingService.bookTickets(user2, targetShow, seatsToBook);
            if (b != null) {
                System.out.println("User 2 Successfully booked! Booking ID: " + b.getId());
            } else {
                System.out.println("User 2 Failed to book seats.");
            }
        };

        Thread b1 = new Thread(bookTask1);
        Thread b2 = new Thread(bookTask2);

        b1.start();
        b2.start();

        try {
            b1.join();
            b2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Removal API Demo ---");
        System.out.println("Total shows before removal: " + showService.getAllShows().size());
        showService.removeShow("Show-2");
        System.out.println("Total shows after removing Show-2: " + showService.getAllShows().size());

        System.out.println("Total movies in repo before removal: " + (movieService.getMovie("M2") != null ? 1 : 0));
        movieService.removeMovie("M2");
        System.out.println("Total movies in repo after removing M2: " + (movieService.getMovie("M2") != null ? 1 : 0));
    }
}
