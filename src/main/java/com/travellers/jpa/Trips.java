package com.travellers.jpa;

import com.travellers.Levels;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name="trips")
public class Trips {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "trips_id")
        private Long id;

        @NotBlank
        private String name;
        @Enumerated(EnumType.ORDINAL)
        Levels level;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public Levels getLevel() {
                return level;
        }

        public void setLevel(Levels level) {
                this.level = level;
        }

    @Override
    public String toString() {
        return "Trips{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
