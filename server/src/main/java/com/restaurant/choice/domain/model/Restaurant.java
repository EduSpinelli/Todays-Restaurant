package com.restaurant.choice.domain.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.restaurant.choice.domain.builder.RestaurantBuilder;

@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private Integer numberVotes;

  @OneToMany(mappedBy = "restaurant")
  private List<Winner> winners;


  private Restaurant() {}

  private Restaurant(Long id, String name, Integer numberVotes, List<Winner> winners) {
    super();
    this.id = id;
    this.name = name;
    this.numberVotes = numberVotes;
    this.winners = winners;
  }

  private Restaurant(String name) {
    super();
    this.name = name;
    this.numberVotes = 0;
  }

  public static Restaurant createExistingRestaurant(Long id, String name, Integer numberVotes,
      List<Winner> winners) {
    return new Restaurant(id, name, numberVotes, winners);
  }

  public static Restaurant createNew(String name) {
    return new Restaurant(name);
  }

  public static Restaurant createByBuilder(RestaurantBuilder builder) {
    return new Restaurant(builder.getId(), builder.getName(), builder.getNumberVotes(),
        builder.getWinners());
  }

  public void clearVotes() {
    this.numberVotes = 0;
  }

  public void vote() {
    this.numberVotes = this.numberVotes + 1;
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

  public Integer getNumberVotes() {
    return numberVotes;
  }

  public void setNumberVotes(Integer numberVotes) {
    this.numberVotes = numberVotes;
  }

  public List<Winner> getWinners() {
    return winners;
  }

  public void setWinners(List<Winner> winners) {
    this.winners = winners;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((numberVotes == null) ? 0 : numberVotes.hashCode());
    result = prime * result + ((winners == null) ? 0 : winners.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Restaurant other = (Restaurant) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (numberVotes == null) {
      if (other.numberVotes != null)
        return false;
    } else if (!numberVotes.equals(other.numberVotes))
      return false;
    if (winners == null) {
        return other.winners == null;
    } else return winners.equals(other.winners);
  }

  @Override
  public String toString() {
    return "Restaurant [id=" + id + ", name=" + name + ", numberVotes=" + numberVotes + "]";
  }



}
