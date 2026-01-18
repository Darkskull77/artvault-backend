package com.example.backend;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleryService {
    // Sample Data with local image paths and tags
    private final List<Artist> artists = List.of(
            new Artist(1L, "Vincent van Gogh", "A Dutch Post-Impressionist painter.", "/images/vangogh.jpg"),
            new Artist(2L, "Leonardo da Vinci", "An Italian polymath of the High Renaissance.", "/images/vinci.jpg"),
            new Artist(3L, "Michelangelo", "An Italian sculptor, painter, architect and poet of the High Renaissance.", "/images/michelangelo.jpg"),
            new Artist(4L, "Pablo Picasso", "A Spanish painter, sculptor, printmaker, ceramicist and theatre designer.", "/images/picasso.jpg")
    );

    private final List<Artwork> artworks = List.of(
            // Van Gogh's Art
            new Artwork(101L, 1L, "The Starry Night", "/images/starry.jpg", 100000000, true, "Museum of Modern Art, New York", List.of("Post-Impressionism", "Modern Art")),
            new Artwork(102L, 1L, "Sunflowers", "/images/flower.jpg", 82000000, false, "National Gallery, London", List.of("Post-Impressionism", "Still Life")),
            // Da Vinci's Art
            new Artwork(201L, 2L, "Mona Lisa", "/images/lisa.jpg", 860000000, true, "Louvre Museum, Paris", List.of("Renaissance", "Portrait")),
            new Artwork(202L, 2L, "The Last Supper", "/images/supper.jpg", 450000000, true, "Santa Maria delle Grazie, Milan", List.of("Renaissance", "Religious")),
            // Michelangelo's Art
            new Artwork(301L, 3L, "David", "/images/david.jpg", 1000000000, true, "Galleria dell'Accademia, Florence", List.of("Renaissance", "Sculpture")),
            new Artwork(302L, 3L, "The Creation of Adam", "/images/adam.jpg", 500000000, true, "Sistine Chapel, Vatican City", List.of("Renaissance", "Fresco")),
            // Picasso's Art
            new Artwork(401L, 4L, "Guernica", "/images/guernica.jpg", 200000000, true, "Museo Reina Sofía, Madrid", List.of("Cubism", "Surrealism")),
            new Artwork(402L, 4L, "Les Demoiselles d'Avignon", "/images/les.jpg", 120000000, true, "Museum of Modern Art, New York", List.of("Cubism", "Modern Art"))
    );

    public List<Artist> getAllArtists() {
        return artists;
    }

    public List<Artwork> getArtworksByArtistId(long artistId) {
        return artworks.stream()
                .filter(artwork -> artwork.getArtistId() == artistId)
                .collect(Collectors.toList());
    }

    public Artwork getArtworkById(long artworkId) {
        return artworks.stream()
                .filter(artwork -> artwork.getId() == artworkId)
                .findFirst()
                .orElse(null);
    }
}