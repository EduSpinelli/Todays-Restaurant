package com.restaurant.choice.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "winner")
public class Winner implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate voteDate;

  @ManyToOne
  private Restaurant restaurant;

  private Winner() {

  }

  private Winner(Restaurant restaurant) {
    this.voteDate = LocalDate.now();
    this.restaurant = restaurant;
  }


  public static Winner createNewWinner(Restaurant restaurant) {
    return new Winner(restaurant);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getVoteDate() {
    return voteDate;
  }

  public void setVoteDate(LocalDate voteDate) {
    this.voteDate = voteDate;
  }

  public Restaurant getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(Restaurant restaurant) {
    this.restaurant = restaurant;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((restaurant == null) ? 0 : restaurant.hashCode());
    result = prime * result + ((voteDate == null) ? 0 : voteDate.hashCode());
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
    Winner other = (Winner) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (restaurant == null) {
      if (other.restaurant != null)
        return false;
    } else if (!restaurant.equals(other.restaurant))
      return false;
    if (voteDate == null) {
      if (other.voteDate != null)
        return false;
    } else if (!voteDate.equals(other.voteDate))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Winner [id=" + id + ", voteDate=" + voteDate + ", restaurant=" + restaurant + "]";
  }

}


