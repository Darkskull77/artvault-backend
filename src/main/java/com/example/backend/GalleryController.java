package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @PostMapping("/api/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if ("user".equals(username) && "Artvault@123".equals(password)) {
            return ResponseEntity.ok(Map.of("status", "success"));
        } else {
            return ResponseEntity.status(401).body(Map.of("status", "failure"));
        }
    }

    @GetMapping("/api/artists")
    public List<Artist> getAllArtists() {
        return galleryService.getAllArtists();
    }

    @GetMapping("/api/artists/{artistId}/artworks")
    public List<Artwork> getArtworksByArtist(@PathVariable long artistId) {
        return galleryService.getArtworksByArtistId(artistId);
    }

    @GetMapping("/api/artworks/{artworkId}")
    public Artwork getArtworkDetails(@PathVariable long artworkId) {
        return galleryService.getArtworkById(artworkId);
    }
}