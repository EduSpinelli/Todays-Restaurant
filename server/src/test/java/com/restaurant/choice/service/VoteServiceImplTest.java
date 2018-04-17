package com.restaurant.choice.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.restaurant.choice.domain.vo.Vote;
import com.restaurant.choice.service.VoteService;

@RunWith(SpringRunner.class)
public class VoteServiceImplTest {

  @Test
  public void markVoteInUserTestInvoke() {

    VoteService voteService = mock(VoteService.class);

    Vote vote = Vote.createNewVote("Eduardo", "Eduardo123", "Padaria123");

    voteService.vote(vote);

    verify(voteService, times(1)).vote(vote);

  }

}
