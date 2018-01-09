import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class UsersService {

  private url: string = "http://localhost:8080/winners";


  constructor(private http: Http) { }

  getUsers(){
    console.log(this.url + 1);
    var users = this.http.get(this.url)
      .map(res => res.json());
      console.dir(users);
      return users;
  }

  getUser(id){
    console.log(this.url + 2);
    return this.http.get(this.getUserUrl(id))
      .map(res => res.json());
  }

  addVote(vote){
    console.dir(vote);
    return this.http.post("http://localhost:8080/votes", JSON.stringify(vote))
      .map(res => res.json());
  }

  updateUser(user){
    return this.http.put(this.getUserUrl(user.id), JSON.stringify(user))
      .map(res => res.json());
  }

  deleteUser(id){
    console.log(this.url + 4);
    return this.http.delete(this.getUserUrl(id))
      .map(res => res.json());
  }

  private getUserUrl(id){
    console.log(this.url + 3);
    return this.url + "/" + id;
  }
}
