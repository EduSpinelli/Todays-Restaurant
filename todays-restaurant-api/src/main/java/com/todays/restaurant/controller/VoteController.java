package com.todays.restaurant.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todays.restaurant.domain.vo.Vote;
import com.todays.restaurant.service.VoteService;
import com.todays.restaurant.validator.VoteValidator;
import com.todays.restaurant.validator.Helper.Result;

@RestController
public class VoteController {

  private final VoteService voteService;

  private final VoteValidator voteValidator;

  @Autowired
  public VoteController(VoteService voteService, VoteValidator voteValidator) {
    this.voteService = voteService;
    this.voteValidator = voteValidator;
  }

  @RequestMapping(path = "/votes", method = RequestMethod.POST)
  public ResponseEntity<Result> vote(@Valid @RequestBody Vote vote, Errors errors) {

    Result result = voteValidator.validate(vote, errors);
    if (!result.getMsg().isEmpty()) {
      return ResponseEntity.badRequest().body(result);
    }

    voteService.vote(vote);
    result.setMsg("success");
    return ResponseEntity.ok(result);
  }

}
