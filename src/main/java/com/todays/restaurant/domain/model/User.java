package com.todays.restaurant.domain.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.todays.restaurant.domain.builder.UserBuilder;

@Entity
@Table(name = "user")
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String userName;

  @Column
  private String password;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private String email;

  @Column
  private Boolean vote;

  private User() {}

  private User(Long id, String userName, String password, String firstName, String lastName,
      String email, Boolean vote) {
    super();
    this.id = id;
    this.userName = userName;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.vote = vote;
  }

  private User(String userName, String password, String firstName, String lastName, String email,
      Boolean vote) {
    super();
    this.userName = userName;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.vote = vote;
  }

  public static User createExistingUserWithVote(Long id, String userName, String password,
      String firstName, String lastName, String email) {

    return new User(id, userName, password, firstName, lastName, email, true);

  }


  public static User createNew(String userName, String password, String firstName, String lastName,
      String email) {
    return new User(userName, password, firstName, lastName, email, false);
  }

  public static User createByBuilder(UserBuilder userBuilder) {
    return new User(userBuilder.getId(), userBuilder.getUserName(), userBuilder.getPassword(),
        userBuilder.getFirstName(), userBuilder.getLastName(), userBuilder.getEmail(),
        userBuilder.getVote());
  }

  public void clearVote() {
    this.vote = false;
  }

  public void vote() {
    this.vote = true;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getVote() {
    return vote;
  }

  public void setVote(Boolean vote) {
    this.vote = vote;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((userName == null) ? 0 : userName.hashCode());
    result = prime * result + ((vote == null) ? 0 : vote.hashCode());
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
    User other = (User) obj;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (firstName == null) {
      if (other.firstName != null)
        return false;
    } else if (!firstName.equals(other.firstName))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (lastName == null) {
      if (other.lastName != null)
        return false;
    } else if (!lastName.equals(other.lastName))
      return false;
    if (password == null) {
      if (other.password != null)
        return false;
    } else if (!password.equals(other.password))
      return false;
    if (userName == null) {
      if (other.userName != null)
        return false;
    } else if (!userName.equals(other.userName))
      return false;
    if (vote == null) {
      if (other.vote != null)
        return false;
    } else if (!vote.equals(other.vote))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName="
        + firstName + ", lastName=" + lastName + ", email=" + email + ", vote=" + vote + "]";
  }

}
