package com.northeastern.edu.controllers;

import com.northeastern.edu.models.*;
import com.northeastern.edu.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;


@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CriticRepository criticRepository;

    @Autowired
    ResidentRepository residentRepository;

    @Autowired
    CriticRatingRepository criticRatingRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    WatchlistRepository watchlistRepository;


    @GetMapping("/api/user/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userRepository.findById(id).get();
    }

    @PostMapping("/api/resident")
    public Resident createResident (@RequestBody Resident resident) {
        resident.setRole(Role.RESIDENT);
        return residentRepository.save(resident);
    }

    @PostMapping("/api/critic")
    public Critic createCritic (@RequestBody Critic critic) {
        critic.setRole(Role.CRITIC);
        return criticRepository.save(critic);
    }

    @PostMapping("/api/admin")
    public Resident createAdmin(@RequestBody Resident resident) {
        resident.setRole(Role.ADMIN);
        return residentRepository.save(resident);
    }

    @PutMapping("/api/user/profile/update")
    public User updateUserProfile(@RequestBody User updatedUser) {
        User user = userRepository.findById(updatedUser.getId()).get();
        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setDob(updatedUser.getDob());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        return userRepository.save(user);
    }

    @RequestMapping(value="/api/user/loggedin", method = RequestMethod.GET)
    public User checkLoggedIn(HttpSession session){
        return (User) session.getAttribute("session");
    }

    @PostMapping("/api/login")
    public User findUserByCredentials(HttpSession session, @RequestBody User user) {

        List<User> users = userRepository.
                findUserByCredentials(user.getUsername(), user.getPassword());

        if(users.size() == 0) {
            return  new User();
        } else {
            User u = users.get(0);
            session.setAttribute("session", u);
            return u;
        }
    }

    @GetMapping("/api/user")
    public User findUserByUserName(@RequestParam("username")  String username) {
        return userRepository.findUserByUsername(username);
    }

    @GetMapping("/api/users")
    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/api/critics")
    public List<Critic> findAllCritics() {
        return (List<Critic>) criticRepository.findAll();
    }

    @GetMapping("/api/user/{userId}/watchlists")
    public Set<Watchlist> getAllWatchlistsForResident(@PathVariable("userId") int userId) {
        Resident resident =  residentRepository.findById(userId).get();
        return resident.getWatchlists();
    }

    @GetMapping("/api/resident/{residentId}/following/critics")
    public Set<Critic> getAllCriticsForResident(@PathVariable("residentId") int residentId) {
        return residentRepository.findById(residentId).get().getFollowsCritics();
    }

    @GetMapping("/api/critic/{criticId}/follower/residents")
    public List<Resident> getAllResidentsForCritic(@PathVariable("criticId") int criticId) {
        return criticRepository.findById(criticId).get().getFollowedByResidents();
    }


    @PutMapping("/api/resident/{residentId}/follow/critic/{criticId}")
    public Resident updateResidentToFollowCritic(@PathVariable("residentId") int residentId,
                                                 @PathVariable("criticId") int criticId) {
        Resident resident = residentRepository.findById(residentId).get();
        Critic critic = criticRepository.findById(criticId).get();
        resident.followCritic(critic);
        return residentRepository.save(resident);

    }

    @DeleteMapping("/api/critic/{criticId}/delete")
    public void deleteCriticByCriticId(@PathVariable("criticId") int criticId) {
        criticRepository.deleteFollowersByCriticId(criticId);
        Critic critic = criticRepository.findById(criticId).get();

        for(CriticRating cr : critic.getCriticRatings()) {
            criticRatingRepository.delete(cr);
        }

        for(Review review : critic.getReviews()) {
            reviewRepository.delete(review);
        }

        criticRepository.delete(critic);
    }

    @DeleteMapping("/api/resident/{residentId}/delete")
    public void deleteResidentById(@PathVariable("residentId") int residentId) {
        residentRepository.deleteFollowingByResidentId(residentId);
        Resident resident = residentRepository.findById(residentId).get();

        for(Review review : resident.getReviews()) {
            reviewRepository.delete(review);
        }

        for(Watchlist watchlist : resident.getWatchlists()) {
            watchlistRepository.delete(watchlist);
        }

        residentRepository.delete(resident);
    }
}
