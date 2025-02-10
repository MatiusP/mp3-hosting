package com.epam.pratsko.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "song_metadata")
public class SongEntity {

    @Id
    private Long id;
    private String name;
    private String artist;
    private String album;
    private String duration;
    private String year;
}
