/**
 * @author surayya
 *
 */

package com.travellers.jpa;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Iterator;
import java.util.Set;

/**
 * We usually don't want to do validation as late as in the persistence layer because
 * it means that the business code above has worked with potentially invalid objects which may lead to unforeseen errors.
 */
@Entity
@Table(name="customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="customers_id")
    private Long id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "customers_address",
            joinColumns =
                    { @JoinColumn(name = "customers_id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "address_id") })
    private Address address;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "customers_trips",
            joinColumns =
                    { @JoinColumn(name = "customers_id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "trips_id") })
    private Set<Trips> trips;

    public Customers() {}

    public Set<Trips> getTrips() {
        return trips;
    }

    public void setTrips(Set<Trips> games) {
        this.trips = games;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        String  s = "Customers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address;
        String gamesstring = "";
        Iterator <Trips>it = trips.iterator();
        while(it.hasNext()) {
            Trips game = it.next();
            gamesstring += game + " , ";
        }
        return s + gamesstring + '}';
    }
}
